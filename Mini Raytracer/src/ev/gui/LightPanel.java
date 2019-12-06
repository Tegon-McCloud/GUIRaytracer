package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import ev.graphics.DistantLight;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class LightPanel extends JSplitPane {
	
	private AddPanel addPanel;
	private EditPanel editPanel;
	
	public LightPanel() {
		super(VERTICAL_SPLIT);
	
		addPanel = new AddPanel();
		Border addBorder = BorderFactory.createTitledBorder("Add");
		addPanel.setBorder(addBorder);
		setLeftComponent(addPanel);
		
		
		editPanel = new EditPanel();
		editPanel.setBorder(BorderFactory.createTitledBorder("Edit"));
		setRightComponent(editPanel);
		
		Insets addPanelInsets = addBorder.getBorderInsets(addPanel);
		setDividerLocation(addPanel.getPreferredSize().height + addPanelInsets.top + addPanelInsets.bottom + dividerSize / 2);
	}
	
}

@SuppressWarnings("serial")
class AddPanel extends JPanel {
	
	public AddPanel() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);	
		
		
		setPreferredSize(layout.preferredLayoutSize(this));
		
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
		
		table.getSelectionModel().addListSelectionListener(e -> {
			// TODO
		});
		
		rebuildTableModel();
		
		SelectedPanel selectedPanel = new SelectedPanel();
		selectedPanel.setBorder(BorderFactory.createTitledBorder("Selected"));
		layout.putConstraint(NORTH, selectedPanel, 5, SOUTH, sp);
		layout.putConstraint(WEST, selectedPanel, 5, WEST, this);
		layout.putConstraint(EAST, selectedPanel, -5, EAST, this);
		layout.putConstraint(SOUTH, selectedPanel, -5, SOUTH, this);
		add(selectedPanel);
		
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
	
	private DistantLight selected;
	private JButton saveButton;
	private Vec3Panel dir;
	private JPanel colIntensPanel;
	private LabeledField red, green, blue, intensity;
	
	public SelectedPanel() {
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		dir = new Vec3Panel();
		dir.setBorder(BorderFactory.createTitledBorder("Direction"));
		dir.setPreferredSize(new Dimension(120, dir.getPreferredSize().height));
		layout.putConstraint(NORTH, dir, 5, NORTH, this);
		layout.putConstraint(WEST, dir, 5, WEST, this);
		add(dir);
		
		
		
		//select(null);
	}
	
	public void select(DistantLight l) {
		selected = l;
		
		if(l != null) {
			dir.setVec(l.getDir());
			Vec3 col = l.getCol();
			red.setString(col.x + "");
			green.setString(col.y + "");
			blue.setString(col.z + "");
			intensity.setString(l.getIntensity() + "");
		}else {
			dir.emptyOut();
			red.setString("");
			green.setString("");
			blue.setString("");
			intensity.setString("");
		}
		
	}
	
	public void addSaveListener(ActionListener l) {
		saveButton.addActionListener(l);
	}
	
}


