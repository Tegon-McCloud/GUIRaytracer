package ev.graphics;

import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

public abstract class Shape {
	
	public abstract float intersect(Ray r);
	public abstract Vec2 texCoord(Vec3 surfPos);
	public abstract Vec3 normal(Vec3 surfPos);
	
}
