package ev.gui.shapepanels;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import ev.graphics.Shape;
import ev.graphics.shapes.Sphere;
import ev.gui.GUI;
import ev.gui.LabeledField;
import ev.gui.ShapePanel;
import ev.gui.Vec3Panel;

@SuppressWarnings("serial")
public class SpherePanel extends JPanel {
	
	private HashMap<String, Shape> target;
	
	private DefaultTableModel tableModel;
	
	/**
	 * Constructs a SpherePanel
	 */
	public SpherePanel(HashMap<String, Shape> target) {
		this.target = target;
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		tableModel = new DefaultTableModel() { // create a default table model except where every cell is not editable
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table = new JTable(tableModel);
		
		JScrollPane sp = new JScrollPane(table); // wrap the table in a JScrollPane
		
		layout.putConstraint(NORTH, sp, 5, NORTH, this);
		layout.putConstraint(WEST, sp, 5, WEST, this);
		layout.putConstraint(EAST, sp, -5, EAST, this);
		layout.putConstraint(SOUTH, sp, 200, NORTH, sp);
		add(sp);
		
		rebuildTableModel();
		
		AddEditPanel selectedPanel = new AddEditPanel(target);
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
					String key = (String) tableModel.getValueAt(table.rowAtPoint(e.getPoint()), 0); // get the name of the clicked light
					selectedPanel.select(key);
				}
			}
		});
		
	}
	
	public void rebuildTableModel() {
		
		HashMap<String, Sphere> spheres = new HashMap<String, Sphere>();
		
		for(String key : target.keySet()) {
			if(target.get(key) instanceof Sphere) {
				spheres.put(key, (Sphere) target.get(key));
			}
		}
		
		String[] colNames = {"Name", "Position", "Radius"};
		
		Object[][] data = new Object[spheres.size()][3];
		
		Iterator<String> nameIterator = spheres.keySet().iterator();
		
		for(int i = 0; i < spheres.size(); i++) {
			data[i][0] = nameIterator.next();
			Sphere s = spheres.get((String) data[i][0]);
			data[i][1] = s.pos;
			data[i][2] = s.radius;
		}
		
		tableModel.setDataVector(data, colNames);
		
	}
	
	@Override
	public String toString() {
		return "Spheres";
	}

}

@SuppressWarnings("serial")
class AddEditPanel extends JPanel {
	private HashMap<String, Shape> target;
	
	private JButton saveButton;
	private Vec3Panel pos;
	private LabeledField radius, name;
	
	public AddEditPanel(HashMap<String, Shape> target) {
		this.target = target;
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		name = new LabeledField("name", "");
		name.setPreferredSize(name.getPreferredSize());
		layout.putConstraint(NORTH, name, 5, NORTH, this);
		layout.putConstraint(WEST, name, 5, WEST, this);
		add(name);
		
		pos = new Vec3Panel();
		pos.setBorder(BorderFactory.createTitledBorder("Position"));
		pos.setPreferredSize(pos.getPreferredSize());
		layout.putConstraint(NORTH, pos, 5, SOUTH, name);
		layout.putConstraint(WEST, pos, 5, WEST, this);
		add(pos);
		
		radius = new LabeledField("radius", "");
		radius.setPreferredSize(radius.getPreferredSize());
		layout.putConstraint(NORTH, radius, 5, SOUTH, pos);
		layout.putConstraint(WEST, radius, 5, WEST, this);
		add(radius);
		
		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, saveButton, 5, SOUTH, radius);
		layout.putConstraint(WEST, saveButton, 5, WEST, this);
		
		saveButton.addActionListener(e -> {
			
			target.put(name.getString(),
					new Sphere(
							pos.getVec(),
							radius.getFloat()));
			
		});

		add(saveButton);
		
		select(null);
	}
	
	public void select(String key) {
		
		Sphere s = (Sphere) target.get(key);
		
		if(s != null) {
			name.setString(key);
			pos.setVec(s.pos);
			radius.setString(s.radius + "");
		}else {
			pos.empty();
			radius.setString("");
		}
		
	}
	
}