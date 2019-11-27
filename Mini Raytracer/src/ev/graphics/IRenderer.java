package ev.graphics;

import java.awt.image.BufferedImage;

/**
 * An IRenderer promises to have the render function, effectively making it
 * a rendering engine that is usable for the GUI.
 * 
 * @since 1.0
 */
public interface IRenderer {
	/**
	 * Renders a scene to a BufferedImage and returns it.
	 * 
	 * @since 1.0
	 * @param s the Scene to be rendered
	 * @param c the camera to look from
	 * @return An image of the scene from the camera
	 */
	public BufferedImage render(Scene s, Camera c);

}
