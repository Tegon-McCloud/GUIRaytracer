package ev.math;

/**
 * Vec3 represents a float-based 3D vector.
 */
public class Vec3 {
	
	public float x, y, z;
	
	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(Vec3 v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}
	
	public Vec3() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Vec3 add(Vec3 v) {
		return new Vec3(x + v.x, y + v.y, z + v.z);
	}
	
	public Vec3 sub(Vec3 v) {
		return new Vec3(x - v.x, y - v.y, z - v.z);
	}
	
	public Vec3 mul(float f) {
		return new Vec3(x*f, y*f, z*f);
	}
	
	public Vec3 mul(Vec3 v) {
		return new Vec3(x * v.x, y * v.y, z * v.z);
	}
	
	public Vec3 mul(Matrix33 m) {
		return new Vec3(m.m00 * x  + m.m01 * y + m.m02 * z, m.m10 * x  + m.m11 * y + m.m12 * z, m.m20 * x  + m.m21 * y + m.m22 * z);
	}
	
	public Vec3 div(float f) {
		return new Vec3(x/f, y/f, z/f);
	}
	
	public float dot(Vec3 v) {
		return x*v.x + y*v.y + z*v.z;
	}
	
	public Vec3 cross(Vec3 v) {
		return new Vec3(y * v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
	}
	
	public Vec3 normalized() {
		float length = length();
		return new Vec3(x/length, y/length, z/length);
	}
	
	public Vec3 saturated() {
		return MathUtil.saturate(this);
	}
	
	public float length() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vec3 reflect(Vec3 normal) {
		return normal.mul(-2*this.dot(normal)).add(this);
	}
	
	@Override
	public String toString() {
		return "<" + x + ", " + y + ", " + z + ">";
	}
	
	public String toGGB() {
		return "Vector((" + x + ", " + y + ", " + z +"))";
	}
	
	
	public String toPointGGB() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	public static void main(String[] args) {
		
	}
	
}
