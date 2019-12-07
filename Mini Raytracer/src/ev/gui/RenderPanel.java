package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;

import ev.graphics.Raytracer;
import ev.graphics.Renderer;

/**
 * A RenderPanel is a container with a JButton for making the program rendering the scene and a ImagePanel for displaying the image
 */
@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
	
	/**
	 * Construct a RenderPanel
	 */
	public RenderPanel() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		JButton renderButton = new JButton("Render");
		layout.putConstraint(WEST, renderButton, 5, WEST, this);
		layout.putConstraint(EAST, renderButton, 80, WEST, renderButton);
		layout.putConstraint(SOUTH, renderButton, -5, SOUTH, this);
		layout.putConstraint(NORTH, renderButton, -20, SOUTH, renderButton);
		add(renderButton);
		
		ImagePanel outputPanel = new ImagePanel();
		outputPanel.setBackground(Color.BLACK);
		layout.putConstraint(WEST, outputPanel, 5, WEST, this);
		layout.putConstraint(EAST, outputPanel, -5, EAST, this);
		layout.putConstraint(SOUTH, outputPanel, -5, NORTH, renderButton);
		layout.putConstraint(NORTH, outputPanel, 5, NORTH, this);
		add(outputPanel);
		
		Renderer renderer = new Raytracer();
		
		renderButton.addActionListener(e -> {
			(new RenderingWorker(outputPanel, renderer)).execute();
		});
		
	}
	
	/**
	 * The RenderingWorker is a SwingWorker that renders the image on a worker thread, to not freeze the GUI
	 */
	class RenderingWorker extends SwingWorker<BufferedImage, Void> {
		
		private ImagePanel target;
		private Renderer renderer;
		
		/**
		 * @param target the ImagePanel to display the image in when finished
		 * @param renderer the Renderer to use
		 */
		public RenderingWorker(ImagePanel target, Renderer renderer) {
			this.target = target;
			this.renderer = renderer;
		}
		
		/**
		 * Renders the image
		 */
		@Override
		protected BufferedImage doInBackground() {
			return renderer.render(GUI.getSceneCpy(), GUI.getCamera());
		}
		
		/**
		 * Displays the image in target
		 */
		@Override
		protected void done() {
			try {
				System.out.println("Finished rendering");
				target.display(get());
			} catch (InterruptedException | ExecutionException e) { // shouldn't happen as doInBackground doesn't throw anything
				e.printStackTrace();
			}
		}
		
	}
	
}
