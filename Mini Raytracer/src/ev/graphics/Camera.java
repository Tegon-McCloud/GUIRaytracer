package ev.graphics;

import ev.math.Ray;
import ev.math.Vec3;

/**
 * A Camera holds information about position, direction, width and height of image etc.
 * It also implements methods for generating rays.
 */
public class Camera {
	
	private float fov;
	private int width, height;
	private Vec3 pos, dir;
	
	/**
	 * Sets the cameras position and direction.
	 * 
	 * @param pos a Vec3 pointing to the new position of the camera
	 * @param dir a Vec3 that points in new direction of the camera
	 * @return void
	 */
	public void moveTo(Vec3 pos, Vec3 dir) {
		this.pos = pos;
		this.dir = dir;
	}
	
	/**
	 * Sets the new width and height of the image that this camera will take.
	 * 
	 * @param width the new width
	 * @param height the new height
	 * @return void
	 */
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the new fov of this camera.
	 * 
	 * @param fov the new fov
	 * @return void
	 */
	public void setFov(float fov) {
		this.fov = fov;
	}
	
	/**
	 * Generates a Ray with the position and direction for the specified pixel.
	 * 
	 * @param x the horizontal position of the pixel the ray is for
	 * @param y the vertical position of the pixel the ray is for
	 * @return a ray with the correct position and direction for the pixel.
	 */
	public Ray generateRay(int x, int y) {
		return null;
	}
	
}
