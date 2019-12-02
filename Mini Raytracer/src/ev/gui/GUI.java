package ev.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import ev.graphics.Camera;
import ev.graphics.DistantLight;
import ev.graphics.Raytracer;
import ev.graphics.Renderer;
import ev.graphics.Scene;
import ev.graphics.shapes.Sphere;
import ev.graphics.textures.Checkerboard;
import ev.math.Vec2;
import ev.math.Vec3;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;
import static javax.swing.SpringLayout.EAST;

import static java.lang.Math.PI;


/**
 * The GUI class is responsible for handling all GUI activity, including handling of input.
 */
public class GUI {
	

	
	private static JFrame frame;
	
	public static void main(String[] args) throws Throwable {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		frame = new JFrame("Render");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// for testing
//		ImagePanel imgp = new ImagePanel();
		
//		Scene s = new Scene(new Vec3(0f, 0f, 0.5f));
//		Camera c = new Camera(new Vec3(), 0, 0, 0, 400, 400, (float)Math.PI/2, 4);
//		
//		Raytracer r = new Raytracer();
//		
//		imgp.set(r.render(s, c));
//		
//		imgp.setPreferredSize(new Dimension(400, 400));
//		imgp.setBackground(new Color(0, 0, 0));
//		frame.setContentPane(imgp);
		
		
		RenderPanel rp = new RenderPanel();
		rp.setPreferredSize(new Dimension(500, 400));
		frame.setContentPane(rp);
		
		// !for testing
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static Scene getScene() {
		// test scene
		Scene s = new Scene(new Vec3(0.5f, 0.0f, 0.0f));
		s.lights.add(new DistantLight(new Vec3(0, 1, 0), new Vec3(1,1,1), 1));
		s.lights.add(new DistantLight(new Vec3(-1, 0, 0), new Vec3(1,1,1), 1));
		s.shapes.add(new Sphere(new Vec3(0,0,0), 5));
		return s;
	}
	
	public static Camera getCamera() {
		// test camera
		return new Camera(new Vec3(0, 0, -10), 0, 0, (float) PI*0.25f, 400, 400, (float)PI * 0.5f, 1);
	}
	
}

/**
 * The ImagePanel is a JPanel subclass that simply displays an image.
 */
@SuppressWarnings("serial")
class ImagePanel extends JPanel {
	
	/**
	 * Creates a ImagePanel displaying img.
	 * 
	 * @param img the BufferedImage to display
	 */
	public ImagePanel(BufferedImage img) {
		display(img);
	}
	
	/**
	 * Creates a ImagePanel displaying nothing.
	 */
	public ImagePanel() {
		this(null);
	}
	
	private BufferedImage img;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(img == null) {
			return;
		}
		
		float imgAspect = img.getWidth() / (float)img.getHeight();
		float thisAspect = getWidth() / (float)getHeight();
		int x, y, drawWidth, drawHeight;
		
		if(thisAspect <= imgAspect) { // if image is relatively wider than panel
			drawWidth = getWidth();							// width is just width
			drawHeight = (int) (getWidth() / imgAspect);	// height is panel-width scaled by 1/imgAspect
			x = 0;											// at horizontal edge
			y = (int) ((getHeight() - drawHeight) / 2f);	// vertically centered
		}else {
			drawWidth = (int) (getHeight() * imgAspect);	// width is height scaled by imgAspect
			drawHeight = getHeight(); 						// height is same as panel
			x = (int) ((getWidth() - drawWidth) / 2f);		// Horizontally centered
			y = 0;											// vertically at edge
		}
		
		g.drawImage(img, x, y, drawWidth, drawHeight, null);
		
	}
	
	/**
	 * Makes the panel display a given image.
	 * 
	 * @param img the BufferedImage to display. If null the panel won't display anything.
	 * @return void
	 */
	public void display(BufferedImage img) {
		this.img = img;
		repaint();
	}
	
}

/**
 * A RenderPanel is a container with a JButton for making the program rendering the scene and a ImagePanel for displaying the image
 */
class RenderPanel extends JPanel {
	
	
	public RenderPanel() {
		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		
		@Override
		protected BufferedImage doInBackground() {
			return renderer.render(GUI.getScene(), GUI.getCamera());
		}
		
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
