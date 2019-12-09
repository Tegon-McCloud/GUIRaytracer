package ev.gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import ev.graphics.Shape;
import ev.gui.shapepanels.PlanePanel;
import ev.gui.shapepanels.SpherePanel;

@SuppressWarnings("serial")
public class ShapePanel extends JPanel {
	private HashMap<String, Shape> shapes;
	private SpherePanel spherePanel;
	private PlanePanel planePanel;

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
		ShapesComboBox scb = new ShapesComboBox();

		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		spherePanel = new SpherePanel(shapes);
		spherePanel.setBorder(BorderFactory.createTitledBorder("SpherePanel"));
		spherePanel.setPreferredSize(new Dimension(300, 500));
		layout.putConstraint(SpringLayout.NORTH, spherePanel, 5, SpringLayout.SOUTH, scb);
		layout.putConstraint(SpringLayout.WEST, spherePanel, 5, SpringLayout.WEST, this);
		spherePanel.setVisible(false);
		add(spherePanel);

		planePanel = new PlanePanel(shapes);
		planePanel.setBorder(BorderFactory.createTitledBorder("PlanePanel"));
		planePanel.setPreferredSize(new Dimension(300, 600));
		layout.putConstraint(SpringLayout.NORTH, planePanel, 5, SpringLayout.SOUTH, scb);
		layout.putConstraint(SpringLayout.WEST, planePanel, 5, SpringLayout.WEST, this);
		planePanel.setVisible(false);
		add(planePanel);

		scb.addItem(spherePanel);
		scb.addItem(planePanel);

		scb.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				JPanel item;
				for(int i = 0; i < scb.getItemCount(); i++) {
					item = (JPanel) scb.getItemAt(i);
					item.setVisible(false);
				}
				item = (JPanel) e.getItem();
				item.setVisible(true);
			}
		});

		add(scb);
	}

	public HashMap<String, Shape> getShapes(){
		return shapes;
	}

	private void rebuildTables() {
		spherePanel.rebuildTableModel();
		planePanel.rebuildTableModel();
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

