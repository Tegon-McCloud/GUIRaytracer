package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import ev.graphics.DistantLight;
import ev.math.Vec3;

/**
 * LightPanel gives users the ability to edit the lighting of a scene.
 * It is build of a table with all lights currently in the scene.
 * It also includes a AddEditPanel for, well, adding and editing lights.
 * Lastly it also allows users to adjust the background.
 */
@SuppressWarnings("serial")
public class LightPanel extends JPanel {

	private HashMap<String, DistantLight> lights;
	
	private DefaultTableModel tableModel;
	private Vec3Panel bgPanel;

	/**
	 * Constructs a LightPanel
	 */
	public LightPanel() {

		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// background
		bgPanel = new Vec3Panel("r", "g", "b", 0.0f, 0.7f, 1.0f);
		bgPanel.setBorder(BorderFactory.createTitledBorder("Background"));
		bgPanel.setPreferredSize(bgPanel.getPreferredSize());
		layout.putConstraint(NORTH, bgPanel, 5, NORTH, this);
		layout.putConstraint(WEST, bgPanel, 5, WEST, this);
		add(bgPanel);
		// !backgrounds

		// ligths
		lights = new HashMap<String, DistantLight>(){
			@Override
			public DistantLight put(String key, DistantLight value) {
				DistantLight l = super.put(key, value);
				rebuildTableModel();	// add this line to the put function
				return l;
			}
			
			@Override
			public DistantLight remove(Object key) {
				DistantLight l = super.remove(key);
				rebuildTableModel();
				return l;
			}
		};


		tableModel = new DefaultTableModel() { // create a default table model except where every cell is not editable
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(tableModel);

		JScrollPane sp = new JScrollPane(table); // wrap the table in a JScrollPane
		sp.setBorder(BorderFactory.createTitledBorder("Lights"));

		layout.putConstraint(NORTH, sp, 5, SOUTH, bgPanel);
		layout.putConstraint(WEST, sp, 5, WEST, this);
		layout.putConstraint(EAST, sp, -5, EAST, this);
		layout.putConstraint(SOUTH, sp, 200, NORTH, sp);
		add(sp);

		rebuildTableModel();

		AddEditPanel editPanel = new AddEditPanel(lights);
		editPanel.setBorder(BorderFactory.createTitledBorder("Add/Edit"));
		layout.putConstraint(NORTH, editPanel, 5, SOUTH, sp);
		layout.putConstraint(WEST, editPanel, 5, WEST, this);
		layout.putConstraint(EAST, editPanel, -5, EAST, this);
		layout.putConstraint(SOUTH, editPanel, -5, SOUTH, this);
		add(editPanel);


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					String key = (String) tableModel.getValueAt(table.rowAtPoint(e.getPoint()), 0); // get the name of the clicked light
					editPanel.select(key);
				}
			}
		});
		// !lights
		
	}

	/**
	 * updates the data displayed in the table of this LightPanel. 
	 */
	private void rebuildTableModel() {

		String[] colNames = {"Name", "Direction", "Color", "Intensity"};

		Object[][] data = new Object[lights.size()][4];

		Iterator<Entry<String, DistantLight>> it = lights.entrySet().iterator();

		for(int i = 0; i < lights.size(); i++) {
			Entry<String, DistantLight> entry = it.next();
			data[i][0] = entry.getKey();
			data[i][1] = entry.getValue().getDir();
			data[i][2] = entry.getValue().getCol();
			data[i][3] = entry.getValue().getIntensity();
		}

		tableModel.setDataVector(data, colNames);

	}
	
	/**
	 * @return the color of the background
	 */
	public Vec3 getBGColor() {
		return bgPanel.getVec();
	}
	
	/**
	 * @return a HashMap containing the current light configuration
	 */
	public HashMap<String, DistantLight> getLights() {
		return lights;
	}
}

/**
 * AddEditPanel is a panel that allows users to add and/or edit lights 
 */
@SuppressWarnings("serial")
class AddEditPanel extends JPanel {
	private HashMap<String, DistantLight> target;

	private Vec3Panel dir;
	private JPanel colPanel;
	private LabeledField red, green, blue, intensity, name;

	public AddEditPanel(HashMap<String, DistantLight> target) {
		this.target = target;
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		name = new LabeledField("name", "");
		name.setPreferredSize(name.getPreferredSize());
		layout.putConstraint(NORTH, name, 5, NORTH, this);
		layout.putConstraint(WEST, name, 5, WEST, this);
		add(name);

		dir = new Vec3Panel();
		dir.setBorder(BorderFactory.createTitledBorder("Direction"));
		dir.setPreferredSize(dir.getPreferredSize());
		layout.putConstraint(NORTH, dir, 5, SOUTH, name);
		layout.putConstraint(WEST, dir, 5, WEST, this);
		add(dir);

		colPanel = new JPanel();
		colPanel.setLayout(new GridLayout(4, 1));

		colPanel.add(red = new LabeledField("r", ""));
		colPanel.add(green = new LabeledField("g", ""));
		colPanel.add(blue = new LabeledField("b", ""));
		colPanel.add(intensity = new LabeledField("intensity", ""));

		colPanel.setBorder(BorderFactory.createTitledBorder("Color"));
		colPanel.setPreferredSize(colPanel.getPreferredSize());

		layout.putConstraint(NORTH, colPanel, 5, NORTH, this);
		layout.putConstraint(WEST, colPanel, 5, EAST, name);
		add(colPanel);

		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, saveButton, 5, SOUTH, dir);
		layout.putConstraint(WEST, saveButton, 5, WEST, this);
		add(saveButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, deleteButton, 5, SOUTH, dir);
		layout.putConstraint(WEST, deleteButton, 5, EAST, saveButton);
		add(deleteButton);
		
		saveButton.addActionListener(e -> {
			try {
				
				target.put(name.getString(),
						new DistantLight(
								dir.getVec(),
								new Vec3(red.getFloat(), green.getFloat(), blue.getFloat()),
								intensity.getFloat()));
				
			}catch (NumberFormatException e1) {
				
				JOptionPane.showMessageDialog(
						GUI.getFrame(),
						"Please enter exclusively numbers.",
						"Number format error",
						JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		deleteButton.addActionListener(e -> {
			target.remove(name.getString());
		});
		
		select(null);
	}
	
	/**
	 * @param key
	 */
	public void select(String key) {

		DistantLight l = target.get(key);

		if(l != null) {
			name.setString(key);
			dir.setVec(l.getDir());
			Vec3 col = l.getCol();
			red.setString(col.x + "");
			green.setString(col.y + "");
			blue.setString(col.z + "");
			intensity.setString(l.getIntensity() + "");
		}else {
			dir.empty();
			red.setString("");
			green.setString("");
			blue.setString("");
			intensity.setString("");
		}

	}

}


