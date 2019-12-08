package ev.gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import ev.graphics.Shape;
import ev.gui.shapepanels.SpherePanel;

@SuppressWarnings("serial")
public class ShapePanel extends JPanel {
	private Vec3Panel posPanel;
	private LabeledField radiusPanel;
	private JButton addButton;
	private HashMap<String, Shape> shapes;
	private SpherePanel spherePanel;
	
	public ShapePanel() {
		
		shapes = new HashMap<String, Shape>() {
			@Override
			public Shape put(String key, Shape value) {
				Shape s = super.put(key, value);
				rebuildTables();
				return s;
			}
			
			@Override
			public Shape remove(Object key) {
				Shape s = super.remove(key);
				rebuildTables();
				return s;
			}
			
		};
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		ShapesComboBox scb = new ShapesComboBox();
		scb.addItem(new SpherePanel(shapes));
		
		scb.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
		          JPanel item = (JPanel) e.getItem();
		          item.setVisible(true);
		          }
		});
		
		//add(scb);
		
		spherePanel = new SpherePanel(shapes);
		spherePanel.setBorder(BorderFactory.createTitledBorder("SpherePanel"));
		spherePanel.setPreferredSize(new Dimension(200, 500));
		layout.putConstraint(SpringLayout.NORTH, spherePanel, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, spherePanel, 5, SpringLayout.WEST, this);
		//spherePanel.setVisible(true);
		add(spherePanel);
		
		/*posPanel = new Vec3Panel();
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
		add(addButton);*/
	}
	
	public HashMap<String, Shape> getShapes(){
		return shapes;
	}
	
	private void rebuildTables() {
		spherePanel.rebuildTableModel();
		
	}
	
	private class ShapesComboBox extends JComboBox<JPanel> {

		public ShapesComboBox() {

			addPopupMenuListener(new PopupMenuListener() {

				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					try {
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {}
			});

		}

	}
	
}

