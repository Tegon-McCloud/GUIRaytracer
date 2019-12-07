package ev.math;

import static ev.math.MathUtil.*;

/**
 * Vec2 represents a float-based 2D vector.
 */
public class Vec2 {
	
	public float x, y;

	/**
	 * @param x the x value
	 * @param y the y value
	 */
	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2() {}

	public Vec2 mul(float f) {
		return new Vec2( f* x, f * y);
	}
	
	public float dot(Vec2 v) {
		return x * v.x + y * v.y;
	}
	
	public float length() {
		return sqrt(x*x + y*y);
	}
	
	public Vec2 normalized() {
		return mul(1/length());
	}

	
}
