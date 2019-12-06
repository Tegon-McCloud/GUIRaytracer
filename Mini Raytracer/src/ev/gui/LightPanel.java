package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;

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
	
	public LightPanel() {
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		HashMap<String, DistantLight> lights = GUI.getControlPanel().getScene().lights;
		
		JButton addButton = new JButton("Add Light");
		addButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, addButton, 5, NORTH, this);
		layout.putConstraint(WEST, addButton, 5, WEST, this);
		add(addButton);
		
		String[] colNames = {"Name", "Direction", "Color", "Intensity"};
		
		
		for(int i = 0; i < 30; i++) {
			lights.put("testLight" + i, new DistantLight(new Vec3(0, 1, 0), new Vec3(0.5f, 0.5f, 0.5f), 3));
		}
		
		Object[][] data = new Object[lights.size()][4];
		
		Iterator<String> nameIterator = lights.keySet().iterator();
		
		for(int i = 0; i < lights.size(); i++) {
			data[i][0] = nameIterator.next();
			DistantLight l = lights.get((String) data[i][0]);
			data[i][1] = l.getDir();
			data[i][2] = l.getCol();
			data[i][3] = l.getIntensity();
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, colNames);
		
		JTable table = new JTable(tableModel);
		JScrollPane sp = new JScrollPane(table);
		
		layout.putConstraint(NORTH, sp, 5, SOUTH, addButton);
		layout.putConstraint(WEST, sp, 5, WEST, this);
		layout.putConstraint(EAST, sp, -5, EAST, this);
		layout.putConstraint(SOUTH, sp, 200, NORTH, sp);
		add(sp);
		
		
		addButton.addActionListener(e -> {
			
			
			
		});
		
	}
	
}

@SuppressWarnings("serial")
class AddPanel extends JPanel {
	
	public AddPanel() {
	
		
	}
	
	
}

@SuppressWarnings("serial")
class EditPanel extends JPanel {
	
	
	
	
}


