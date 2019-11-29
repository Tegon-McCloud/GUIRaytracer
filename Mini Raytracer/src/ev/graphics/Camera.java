package ev.graphics;

import ev.math.Ray;
import ev.math.Vec3;

/**
 * A camera is used to describe to an IRenderer from where to "take the picture".
 *  It also specifies direction, zoom (in the form of fov) and the resolution of the image taken. 
 */
public class Camera {
	
	private Vec3 pos, dir;
	private int width, height;
	private float fov;
	
	/**
	 * Construct a camera and set position, direction, image width and height and fov.
	 * 
	 * @param pos the initial position
	 * @param dir the initial direction
	 * @param width the initial image width
	 * @param height the initial image height
	 * @param fov the initial fov
	 */
	public Camera(Vec3 pos, Vec3 dir, int width, int height, float fov) {
		moveTo(pos, dir);
		resize(width, height);
		setFov(fov);
	}

	/**
	 * Sets the cameras position and direction.
	 * 
	 * @param pos a Vec3 pointing to the new position of the camera
	 * @param dir a Vec3 that points in new direction of the camera
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
	 */
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Sets the new fov of this camera.
	 * 
	 * @param fov the new fov
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
		
		float xFrac = (x + 0.5f)/width;
		float yFrac = (y + 0.5f)/height;
		
		float hfov = fov * width/(float)height;
		float tanHalfHFov = (float)Math.tan(hfov/2);
		float tanHalfVFov = (float)Math.tan(fov/2);
		
		Ray r = new Ray(new Vec3(pos), new Vec3(0, 0, 1));
		
		r.dir.x = 2 * tanHalfHFov * xFrac - tanHalfHFov;
		r.dir.y = 2 * tanHalfVFov * yFrac - tanHalfVFov;
		r.dir = r.dir.normalized();
		
		
		
		return r;
		
	}
	
	public static void main(String[] args) {
		
		Camera c = new Camera(new Vec3(0f, 0f, 0f), new Vec3(1f, 4f, 8f), 12, 10, (float)Math.PI/2);
		
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println(c.generateRay(i, j).dir.toGGB() + ", ");
			}
		}
	}
	
}
