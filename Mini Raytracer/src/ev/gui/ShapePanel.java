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
		
		add(scb);
		
		spherePanel = new SpherePanel(shapes);
		spherePanel.setBorder(BorderFactory.createTitledBorder("SpherePanel"));
		spherePanel.setPreferredSize(new Dimension(200, 500));
		layout.putConstraint(SpringLayout.NORTH, spherePanel, 5, SpringLayout.SOUTH, scb);
		layout.putConstraint(SpringLayout.WEST, spherePanel, 5, SpringLayout.WEST, this);
		spherePanel.setVisible(true);
		add(spherePanel);
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

