package ev.graphics.shapes;

import ev.graphics.Shape;
import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

public class Sphere extends Shape {

	float radius;
	
	@Override
	public float intersect(Ray r) {
		return 0;
	}
	
	@Override
	public Vec2 texCoord(Vec3 surfPos) {
		return null;
	}

	@Override
	public Vec3 normal(Vec3 surfPos) {
		return null;
	}
	
}
