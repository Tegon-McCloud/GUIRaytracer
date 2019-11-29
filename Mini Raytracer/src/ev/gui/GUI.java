package ev.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ev.graphics.Camera;
import ev.graphics.Raytracer;
import ev.graphics.Scene;
import ev.math.Vec3;

/**
 * The GUI class is responsible for handling all GUI activity, including handling of input.
 */
public class GUI {
	
	private static JFrame frame;
	
	public static void main(String[] args) throws Throwable {
		
		frame = new JFrame("Render");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// for testing
		ImagePanel imgp = new ImagePanel();
		
		Scene s = new Scene(new Vec3(0f, 0f, 0.5f));
		Camera c = new Camera(new Vec3(), 0, 0, 0, 400, 400, (float)Math.PI/2, 4);
		
		Raytracer r = new Raytracer();
		
		imgp.set(r.render(s, c));
		imgp.setPreferredSize(new Dimension(400, 400));
		imgp.setBackground(new Color(0, 0, 0));
		frame.setContentPane(imgp);
		// !for testing
		
		frame.pack();
		frame.setVisible(true);
		
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
		set(img);
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
	public void set(BufferedImage img) {
		this.img = img;
		repaint();
	}
	
}
