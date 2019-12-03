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
	public Plane(Vec3 normal, Vec3 pos) {
		this.normal = normal;
		this.pos = pos;
		
		if(abs(normal.x) < EPSILON && abs(normal.y) < EPSILON) { // plane is almost flat with xy-plane
			toXY = Matrix33.getIdentityMatrix();
			return;
		}
		
		
		
	}

	@Override
	public float intersect(Ray r) {
		
		float normalDotDir = normal.dot(r.dir);
		if(normalDotDir < EPSILON) return Float.POSITIVE_INFINITY;
		return pos.sub(r.ori).dot(normal) / normalDotDir;
	}

	@Override
	public Vec2 texCoord(Vec3 surfPos) {
		Vec3 diff = surfPos.sub(pos);
		
		
		return null;
	}

	@Override
	public Vec3 normal(Vec3 surfPos) {
		// TODO Auto-generated method stub
		return null;
	}

}
