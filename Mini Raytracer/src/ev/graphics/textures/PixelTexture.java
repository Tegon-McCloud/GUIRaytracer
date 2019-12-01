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
	 * @param data the new data.
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
				texData[i][j] = new Vec3(
						(float)((rgb >> 16) & 0xff) / 0xff,	// shift to bit 16 (red color channel) and mask it with 11111111, then normalize
						(float)((rgb >> 8) & 0xff) / 0xff,	// same for green (bit 8-15)
						(float)((rgb >> 0) & 0xff) / 0xff	// same for blue (0-7)
					);
			}
		}
		
		tex.setData(texData);
		
		return tex;
	}
	
	/**
	 * Reads an image file f as a specular map where bit 24-31 is the glossiness and 0-23 is the specular intensity.
	 * The intensity is mapped to [0, 1]. The glossiness is first mapped to [0, 1] then multiplied by 12 and used as a power of 2.
	 * This give glossiness an interval of [1, 4096]
	 * 
	 * @param f
	 * @return a PixelTexture<Vec2> with the specular values from f
	 * @throws IOException if an IOException occurs reading the file
	 */
	public static PixelTexture<Vec2> specTex(File f) throws IOException {
		BufferedImage img = ImageIO.read(f);
		PixelTexture<Vec2> tex = new PixelTexture<Vec2>(img.getWidth(), img.getHeight());
		Vec2[][] texData = new Vec2[tex.width][tex.height];
		
		for(int i = 0; i < tex.width; i++) {
			for(int j = 0; j < tex.height; j++) {
				int argb = img.getRGB(i, j);
				texData[i][j] = new Vec2(
						(float)(argb & 0xffffff) / 0xffffff,							// intensity is bit 0-23
						(float)Math.pow(2, 12 * ((float)((argb >> 24) & 0xff) / 0xff))	// glossiness is bit 24-31
					);
			}
		}
		
		tex.setData(texData);
		
		return tex;
	}
	
}
