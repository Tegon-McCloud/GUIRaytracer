package ev.graphics;

import java.awt.image.BufferedImage;

public interface IRenderer {
	
	public BufferedImage render(Scene s, Camera c);

}
