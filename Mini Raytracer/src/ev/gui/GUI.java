package ev.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The GUI class is responsible for handling all GUI activity, including handling of input.
 * 
 * @since 1.0
 */
public class GUI {
	
	private static JFrame frame;
	
	public static void main(String[] args) throws Throwable {
		
		frame = new JFrame("Render");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// for testing
		ImagePanel imgp = new ImagePanel();
		imgp.set(ImageIO.read(new File("C:\\test\\raytraced.png")));
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
 * 
 * @since 1.0
 */
@SuppressWarnings("serial")
class ImagePanel extends JPanel {
	
	/**
	 * Creates a ImagePanel displaying img.
	 * 
	 * @since 1.0
	 * @param img the BufferedImage to display
	 */
	public ImagePanel(BufferedImage img) {
		set(img);
	}
	
	/**
	 * Creates a ImagePanel displaying nothing.
	 * 
	 * @since 1.0
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
		
		if(thisAspect <= imgAspect) { // if image is relatively wider than panel
			
			int drawWidth = getWidth();							// width is just width
			int drawHeight = (int) (getWidth() / imgAspect);	// height is panel-width scaled by 1/imgAspect
			int x = 0;											// at horizontal edge
			int y = (int) ((getHeight() - drawHeight) / 2f);	// vertically centered
			g.drawImage(img, x, y, drawWidth, drawHeight, null);
			
		}else {
			
			int drawWidth = (int) (getHeight() * imgAspect);	// width is height scaled by imgAspect
			int drawHeight = getHeight(); 						// height is same as panel
			int x = (int) ((getWidth() - drawWidth) / 2f);		// Horizontally centered
			int y = 0;											// vertically at edge
			g.drawImage(img, x, y, drawWidth, drawHeight, null);
			
		}
		
	}
	
	/**
	 * Makes the panel display another image.
	 * 
	 * @since 1.0
	 * @param img the BufferedImage to display. If null the panel won't display anything.
	 * @return void
	 */
	public void set(BufferedImage img) {
		this.img = img;
		repaint();
	}
	
}
