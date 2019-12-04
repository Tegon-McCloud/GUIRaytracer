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
	
	private Matrix33 toXY; // transform this plane to the xy plane, assuming pos is <0, 0, 0>
	
	/**
	 * @param normal
	 * @param pos
	 */
	public Plane(Vec3 pos, Vec3 normal) {
		this.normal = normal.normalized();
		this.pos = pos;
		
		if(abs(normal.x) < EPSILON && abs(normal.y) < EPSILON) { // plane is almost flat with xy-plane
			toXY = Matrix33.getIdentityMatrix();
			return;
		}
		
		
		Vec3 normPerp1 = normal.cross(new Vec3(0, 0, 1));
		Vec3 normPerp2 = normPerp1.cross(normal);
		
		toXY = Matrix33.getMatrix(normPerp1, normPerp2, normal);
	}

	@Override
	public float intersect(Ray r) {
		Vec3 oriToPos = r.ori.sub(pos);
		
		float dirDotN = r.dir.dot(normal);
		if(dirDotN < EPSILON) return Float.POSITIVE_INFINITY;
		
		float t = oriToPos.dot(normal) / dirDotN;
		return t > 0 ? t : Float.POSITIVE_INFINITY;
		
	}

	@Override
	public Vec2 texCoord(Vec3 surfPos) {
		Vec3 diff = surfPos.sub(pos);
		diff = diff.mul(toXY);
		
		return new Vec2(diff.x, diff.y);
	}

	@Override
	public Vec3 normal(Vec3 surfPos) {
		return new Vec3(normal);
	}

}
