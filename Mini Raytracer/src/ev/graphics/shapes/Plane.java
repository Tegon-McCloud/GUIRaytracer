package ev.graphics.shapes;

import static ev.math.MathUtil.*;

import ev.graphics.Shape;
import ev.math.Matrix33;
import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

public class Plane extends Shape {
	
	private Vec3 normal;
	private Vec3 pos;
	
	private Matrix33 toXY; // transform this plane to the xy plane, assuming pos is <0, 0, 0>.
	
	/**
	 * @param normal the normal of the plane. Does not need pre-normalization.
	 * @param pos any point in the plane
	 */
	public Plane(Vec3 pos, Vec3 normal) {
		this.normal = normal.normalized();
		this.pos = pos;
		
		if(abs(normal.x) < EPSILON && abs(normal.y) < EPSILON) { // plane is almost flat with xy-plane
			toXY = Matrix33.getIdentityMatrix(); // assume it to be xy plane (no transformation needed)
			return;
		}
		
		Vec3 normPerp1 = normal.cross(new Vec3(0, 0, 1)); // a vector that should be perpendicular to the normal
		Vec3 normPerp2 = normPerp1.cross(normal); // a vector thats both perpendicular to both normPerp1 and normal
		
		toXY = Matrix33.getMatrix(normPerp1, normPerp2, normal); // 3 perpendicular vectors with length 1 should give a rotation
	}
	
	public Vec3 getPos() {
		return pos;
	}
	
	public Vec3 getNormal() {
		return normal;
	}

	@Override
	public float intersect(Ray r) {
		Vec3 oriToPos = r.ori.sub(pos);
		
		float dirDotN = r.dir.dot(normal);
		if(-dirDotN < EPSILON) return Float.POSITIVE_INFINITY; // if dir is pointing in the same direction as normal, we shouldn't be seeing the plane
		
		float t = oriToPos.dot(normal) / dirDotN; // refer to equation of the plane and line
		return t > 0 ? t : Float.POSITIVE_INFINITY;
		
	}

	@Override
	public Vec2 texCoord(Vec3 surfPos) {
		Vec3 diff = surfPos.sub(pos); // vector in plane 
		diff = diff.mul(toXY); // flatten it to the xy-plane
		
		return new Vec2(diff.x - floor(diff.x), diff.y - floor(diff.y)); // keep only the 0-1 part of the number
	}

	@Override
	public Vec3 normal(Vec3 surfPos) {
		return new Vec3(normal); // no explanation needed
	}
	
	@Override
	public Shape clone() {
		return new Plane(new Vec3(pos), normal);
	}
}
