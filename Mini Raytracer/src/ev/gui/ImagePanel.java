package ev.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * The ImagePanel is a JPanel subclass that simply displays an image.
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	
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
		super.paintComponent(g); // background
		
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
