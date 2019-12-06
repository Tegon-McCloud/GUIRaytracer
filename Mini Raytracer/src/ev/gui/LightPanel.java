package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import ev.graphics.DistantLight;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class LightPanel extends JPanel {

	
private DefaultTableModel tableModel;
	
	public LightPanel() {
		
		GUI.getControlPanel().getScene().lights = new HashMap<String, DistantLight>(){
			@Override
			public DistantLight put(String key, DistantLight value) {
				DistantLight l = super.put(key, value);
				rebuildTableModel();
				return l;
			}
		};
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table = new JTable(tableModel);
		
		JScrollPane sp = new JScrollPane(table);
		
		layout.putConstraint(NORTH, sp, 5, NORTH, this);
		layout.putConstraint(WEST, sp, 5, WEST, this);
		layout.putConstraint(EAST, sp, -5, EAST, this);
		layout.putConstraint(SOUTH, sp, 200, NORTH, sp);
		add(sp);
		
		rebuildTableModel();
		
		SelectedPanel selectedPanel = new SelectedPanel();
		selectedPanel.setBorder(BorderFactory.createTitledBorder("Add/Edit"));
		layout.putConstraint(NORTH, selectedPanel, 5, SOUTH, sp);
		layout.putConstraint(WEST, selectedPanel, 5, WEST, this);
		layout.putConstraint(EAST, selectedPanel, -5, EAST, this);
		layout.putConstraint(SOUTH, selectedPanel, -5, SOUTH, this);
		add(selectedPanel);
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					String key = (String) tableModel.getValueAt(table.rowAtPoint(e.getPoint()), 0);
					selectedPanel.select(key);
				}
			}
		});
		
	}
	
	public void rebuildTableModel() {
		
		HashMap<String, DistantLight> lights = GUI.getControlPanel().getScene().lights;
		
		String[] colNames = {"Name", "Direction", "Color", "Intensity"};
		
		Object[][] data = new Object[lights.size()][4];
		
		Iterator<String> nameIterator = lights.keySet().iterator();
		
		for(int i = 0; i < lights.size(); i++) {
			data[i][0] = nameIterator.next();
			DistantLight l = lights.get((String) data[i][0]);
			data[i][1] = l.getDir();
			data[i][2] = l.getCol();
			data[i][3] = l.getIntensity();
		}
		
		tableModel.setDataVector(data, colNames);
		
	}
	
}

@SuppressWarnings("serial")
class SelectedPanel extends JPanel {
	
	
	private JButton saveButton;
	private Vec3Panel dir;
	private JPanel colPanel;
	private LabeledField red, green, blue, intensity, name;
	
	public SelectedPanel() {
		
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
		
		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, saveButton, 5, SOUTH, dir);
		layout.putConstraint(WEST, saveButton, 5, WEST, this);
		add(saveButton);
		
		saveButton.addActionListener(e -> {
			
			GUI.getControlPanel().getScene().lights.put(name.getString(),
					new DistantLight(
							dir.getVec(),
							new Vec3(red.getFloat(), green.getFloat(), blue.getFloat()),
							intensity.getFloat()));
			
		});
		
		select(null);
	}
	
	public void select(String key) {
		
		DistantLight l = GUI.getControlPanel().getScene().lights.get(key);
		
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


