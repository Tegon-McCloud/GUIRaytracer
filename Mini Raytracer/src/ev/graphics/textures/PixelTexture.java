package ev.graphics.textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ev.graphics.Texture2D;
import ev.math.Vec2;
import ev.math.Vec3;

/**
 * The PixelTexture is an implementation of Texture2D. It is used for storing a discrete 2D array of some data of type T.
 * It can be used to store images in memory and access the pixel data of the image by mapping the u coordinate
 * from <code>[0, 1[</code> to <code>[0, width[</code> and the v coordinate from <code>[0, 1[</code> to <code>[0, height[</code>.
 * 
 * @param <T> the data type that the image is storing.
 */
public class PixelTexture<T> implements Texture2D<T> {
	
	private int width, height;
	private T[][] data;
	
	
	public PixelTexture(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public T get(Vec2 uv) {
		return data[(int)(uv.x * width)][(int)(uv.y * height)];
	}
	
	public void setData(T[][] data) {
		this.data = data;
	}
	
	public static PixelTexture<Vec3> colorTex(File f) throws IOException {
		BufferedImage img = ImageIO.read(f);
		PixelTexture<Vec3> tex = new PixelTexture<Vec3>(img.getWidth(), img.getHeight());
		Vec3[][] texData = new Vec3[tex.width][tex.height];
		
		for(int i = 0; i < tex.width; i++) {
			for(int j = 0; j < tex.height; j++) {
				int rgb = img.getRGB(i, j);
				texData[i][j] = new Vec3(((rgb >> 16) & 0xff)/255f, ((rgb >> 8) & 0xff)/255f, ((rgb >> 0) & 0xff)/255f);
			}
		}
		
		tex.setData(texData);
		
		return tex;
	}
	
}
