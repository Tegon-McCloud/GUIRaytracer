package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;
import static javax.swing.SpringLayout.VERTICAL_CENTER;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import ev.graphics.DistantLight;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class LightPanel extends JSplitPane {
	
	private AddPanel addPanel;
	private EditPanel editPanel;
	
	public LightPanel() {
		super(VERTICAL_SPLIT);
	
		AddPanel addPanel = new AddPanel();
		addPanel.setBorder(BorderFactory.createTitledBorder("Add"));
		setLeftComponent(addPanel);
//		layout.putConstraint(NORTH, addPanel, 5, NORTH, this);
//		layout.putConstraint(WEST, addPanel, 5, WEST, this);
//		layout.putConstraint(EAST, addPanel, -5, EAST, this);
//		layout.putConstraint(SOUTH, addPanel, -5, VERTICAL_CENTER, this);
		
		
		EditPanel editPanel = new EditPanel();
		editPanel.setBorder(BorderFactory.createTitledBorder("Edit"));
		setRightComponent(editPanel);
		
//		layout.putConstraint(NORTH, editPanel, 5, VERTICAL_CENTER, this);
//		layout.putConstraint(WEST, editPanel, 5, WEST, this);
//		layout.putConstraint(EAST, editPanel, -5, EAST, this);
//		layout.putConstraint(SOUTH, editPanel, -5, SOUTH, this);
		
		
		
	}
	
}

@SuppressWarnings("serial")
class AddPanel extends JPanel {
	
	public AddPanel() {
		
		
		
	}
	
}

@SuppressWarnings("serial")
class EditPanel extends JPanel {
	
	private DefaultTableModel tableModel;
	
	public EditPanel() {
		
		for(int i = 0; i < 30; i++) {
			GUI.getControlPanel().getScene().lights.put("testLight" + i, new DistantLight(new Vec3(0, 1, 0), new Vec3(0.5f, 0.5f, 0.5f), 3));
		}
		
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		tableModel = new DefaultTableModel();
		
		JTable table = new JTable(tableModel);
		JScrollPane sp = new JScrollPane(table);
		
		layout.putConstraint(NORTH, sp, 5, NORTH, this);
		layout.putConstraint(WEST, sp, 5, WEST, this);
		layout.putConstraint(EAST, sp, -5, EAST, this);
		layout.putConstraint(SOUTH, sp, 200, NORTH, sp);
		add(sp);
		
		rebuildTableModel();
		
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


