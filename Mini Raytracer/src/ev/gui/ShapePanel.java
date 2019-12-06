package ev.gui;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import ev.graphics.shapes.Sphere;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class ShapePanel extends JPanel {
	Vec3Panel posPanel;
	LabeledField radiusPanel;
	JButton addButton;
	
	public ShapePanel() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		posPanel = new Vec3Panel();
		posPanel.setBorder(BorderFactory.createTitledBorder("Position"));
		posPanel.setPreferredSize(new Dimension(150, posPanel.getPreferredSize().height));
		layout.putConstraint(SpringLayout.NORTH, posPanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, posPanel, 5, SpringLayout.WEST, this);
		add(posPanel);
		
		radiusPanel = new LabeledField("Radius", 0f);
		radiusPanel.setBorder(BorderFactory.createTitledBorder("Radius"));
		radiusPanel.setPreferredSize(new Dimension(150, radiusPanel.getPreferredSize().height));
		layout.putConstraint(SpringLayout.NORTH, radiusPanel, 5, SpringLayout.SOUTH, posPanel);
		layout.putConstraint(SpringLayout.WEST, radiusPanel, 0, SpringLayout.WEST, posPanel);
		add(radiusPanel);
		
		addButton = new JButton("Add Shape to Scene");
		addButton.setPreferredSize(new Dimension(150, 30));
		layout.putConstraint(SpringLayout.NORTH, addButton, 5, SpringLayout.SOUTH, radiusPanel);
		layout.putConstraint(SpringLayout.WEST, addButton, 0, SpringLayout.WEST, radiusPanel);
		addButton.addActionListener(e -> {
			try {
				GUI.getControlPanel().getScene().shapes.put("min ting", new Sphere(posPanel.getVec(), radiusPanel.getFloat()));
			} catch (Exception e1) {
				System.err.println("Failed to add the shape to the scene");
			}
		});
		add(addButton);
		
		
		
	}
}
