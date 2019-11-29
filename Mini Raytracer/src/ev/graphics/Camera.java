package ev.graphics;

import ev.math.Matrix33;
import ev.math.Ray;
import ev.math.Vec3;

/**
 * A camera is used to describe to an IRenderer from where to "take the picture".
 *  It also specifies direction, zoom (in the form of fov) and the resolution of the image taken. 
 */
public class Camera {
	
	private Vec3 pos;
	private int width, height;
	private float fov, yaw, pitch, roll;
	
	private float tanHalfHFov, tanHalfVFov;
	private Matrix33 rotation;
	
	
	/**
	 * Construct a camera and set position, direction, image width and height and fov.
	 * 
	 * @param pos the initial position
	 * @param yaw the initial yaw
	 * @param pitch the initial pitch
	 * @param roll the initial roll
	 * @param width the initial image width
	 * @param height the initial image height
	 * @param fov the initial fov
	 */
	public Camera(Vec3 pos, float yaw, float pitch, float roll, int width, int height, float fov) {
		moveTo(pos);
		resize(width, height);
		setFov(fov);
		setYawPitchRoll(yaw, pitch, roll);
	}

	/**
	 * Sets the cameras position.
	 * 
	 * @param pos a Vec3 pointing to the new position of the camera
	 */
	public void moveTo(Vec3 pos) {
		this.pos = pos;
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
		
		float hfov = fov * width/(float)height;
		tanHalfHFov = (float)Math.tan(hfov/2);
		tanHalfVFov = (float)Math.tan(fov/2);
	}
	
	/**
	 * @param yaw the new yaw
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
		rotation = Matrix33.getYRotationMatrix(yaw).mul(Matrix33.getXRotationMatrix(pitch).mul(Matrix33.getZRotationMatrix(roll)));
	}
	
	/**
	 * @param pitch the new pitch
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
		rotation = Matrix33.getYRotationMatrix(yaw).mul(Matrix33.getXRotationMatrix(pitch).mul(Matrix33.getZRotationMatrix(roll)));
	}
	
	/**
	 * @param roll the new roll
	 */
	public void setRoll(float roll) {
		this.roll = roll;
		rotation = Matrix33.getYRotationMatrix(yaw).mul(Matrix33.getXRotationMatrix(pitch).mul(Matrix33.getZRotationMatrix(roll)));
	}
	
	/**
	 * Sets yaw, pitch and roll at the same time. Only updates the rotation matrix once.
	 * 
	 * @param yaw the new yaw
	 * @param pitch the new pitch
	 * @param roll the new roll
	 */
	public void setYawPitchRoll(float yaw, float pitch, float roll) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.roll = roll;		
		
		rotation = Matrix33.getZRotationMatrix(roll)
				.mul(Matrix33.getXRotationMatrix(pitch))
				.mul(Matrix33.getYRotationMatrix(yaw));
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
		
		Ray r = new Ray(new Vec3(pos), new Vec3(0, 0, 1));
		
		r.dir.x = 2 * tanHalfHFov * xFrac - tanHalfHFov;
		r.dir.y = 2 * tanHalfVFov * yFrac - tanHalfVFov;
		//r.dir = r.dir.normalized();
		r.dir = r.dir.mul(rotation);
		
		return r;
		
	}
	
	public static void main(String[] args) {
		
		Camera c = new Camera(new Vec3(0f, 0f, 0f), 1f, 1f, 0f, 12, 10, (float)Math.PI/2);
		
		System.out.println(Matrix33.getZRotationMatrix(0).toGGB());
		System.out.println(Matrix33.getXRotationMatrix(1).toGGB());
		System.out.println(Matrix33.getYRotationMatrix(1).toGGB());
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.println(c.generateRay(i, j).dir.toGGB() + ", ");
			}
		}
	}
	
}
