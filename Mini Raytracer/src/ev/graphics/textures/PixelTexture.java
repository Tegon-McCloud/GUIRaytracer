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
 * It can be used to store images in memory and access the pixel data of the image by mapping the coordinates given 
 * from ([0, 1[, [0, 1[) to ([0, width[, [0, height[) and then using floor to go from continuous uv coordinates to pixel coordinates.
 * @param <T> the data type that the image is storing.
 */
public class PixelTexture<T> implements Texture2D<T> {
	
	private int width, height;
	private T[][] data;
	
	/**
	 * @param width the width of the texture
	 * @param height the height of the texture
	 */
	public PixelTexture(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public T get(Vec2 uv) {
		return data[(int)(uv.x * width)][(int)(uv.y * height)];
	}
	
	/**
	 * @param data set the texture.
	 */
	public void setData(T[][] data) {
		this.data = data;
	}
	
	/**
	 * Read f as a normal image and put the data into a PixelTexture<Vec3>, then return that.
	 * 
	 * @param f the file 
	 * @return a PixelTexture<Vec3> with the colors of the image
	 * @throws IOException if an IOException occurs reading the file
	 */
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
	
	/**
	 * reads an image file f, like it is a specular map where bit 24-31 is the glossiness and 0-23 is the specular intensity
	 * 
	 * @param f
	 * @return a PixelTexture<Vec2> with the specular values from f
	 * @throws IOException if an IOException occurs reading the file
	 */
	public static PixelTexture<Vec2> specTex(File f){
		return null;
	}
	
}
