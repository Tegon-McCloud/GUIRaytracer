package ev.graphics;

import ev.math.Vec3;

public class DistantLight {
	
	private Vec3 dir, col;
	private float intensity;

	/**
	 * @param dir
	 * @param col
	 * @param intensity
	 */
	public DistantLight(Vec3 dir, Vec3 col, float intensity) {
		this.dir = dir.normalized();
		this.col = col;
		this.intensity = intensity;
	}

	/**
	 * @return the direction
	 */
	public Vec3 getDir() {
		return dir;
	}

	/**
	 * @param dir the direction to set
	 */
	public void setDir(Vec3 dir) {
		this.dir = dir;
	}

	/**
	 * @return the color
	 */
	public Vec3 getCol() {
		return col;
	}

	/**
	 * @param col the color to set
	 */
	public void setCol(Vec3 col) {
		this.col = col;
	}

	/**
	 * @return the intensity
	 */
	public float getIntensity() {
		return intensity;
	}

	/**
	 * @param intensity the intensity to set
	 */
	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	

}