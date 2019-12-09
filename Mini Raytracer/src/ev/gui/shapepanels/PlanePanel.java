package ev.gui.shapepanels;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
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
import ev.graphics.shapes.Plane;
import ev.gui.LabeledField;
import ev.gui.Vec3Panel;

@SuppressWarnings("serial")
public class PlanePanel extends JPanel {

	private HashMap<String, Shape> target;

	private DefaultTableModel tableModel;

	/**
	 * Constructs a PlanePanel
	 */
	public PlanePanel(HashMap<String, Shape> target) {
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

		PlaneAddEditPanel selectedPanel = new PlaneAddEditPanel(target);
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

		HashMap<String, Plane> planes = new HashMap<String, Plane>();

		for(String key : target.keySet()) {
			if(target.get(key) instanceof Plane) {
				planes.put(key, (Plane) target.get(key));
			}
		}

		String[] colNames = {"Name", "Position", "Normal"};

		Object[][] data = new Object[planes.size()][3];

		Iterator<String> nameIterator = planes.keySet().iterator();

		for(int i = 0; i < planes.size(); i++) {
			data[i][0] = nameIterator.next();
			Plane p = planes.get((String) data[i][0]);
			data[i][1] = p.getPos();
			data[i][2] = p.getNormal();
		}

		tableModel.setDataVector(data, colNames);

	}

	@Override
	public String toString() {
		return "Planes";
	}

}

@SuppressWarnings("serial")
class PlaneAddEditPanel extends JPanel {
	private HashMap<String, Shape> target;

	private JButton saveButton;
	private Vec3Panel pos, normal;
	private LabeledField name;

	public PlaneAddEditPanel(HashMap<String, Shape> target) {
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

		normal = new Vec3Panel();
		normal.setBorder(BorderFactory.createTitledBorder("Normal"));
		normal.setPreferredSize(normal.getPreferredSize());
		layout.putConstraint(NORTH, normal, 5, SOUTH, pos);
		layout.putConstraint(WEST, normal, 5, WEST, this);
		add(normal);

		saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, saveButton, 5, SOUTH, normal);
		layout.putConstraint(WEST, saveButton, 5, WEST, this);

		saveButton.addActionListener(e -> {

			target.put(name.getString(),
					new Plane(pos.getVec(), normal.getVec()));
		});

		add(saveButton);

		select(null);
	}

	public void select(String key) {

		Plane p = (Plane) target.get(key);

		if(p != null) {
			name.setString(key);
			pos.setVec(p.getPos());
			normal.setVec(p.getNormal());
		}else {
			pos.empty();
			normal.empty();
		}

	}

}