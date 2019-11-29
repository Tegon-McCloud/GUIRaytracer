package ev.graphics;

import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

/**
 * A shape is anything with a 3D representation that could part of a scene.
 * Scene forces objects to implement necessary methods for rendering.
 */
public interface Shape {
	
	/**
	 * @param r the Ray that we will check if intersects the Shape
	 * @return the distance from the origin of the Ray to the Shape, or NAN if it doesn't intersect
	 */
	public float intersect(Ray r);
	public Vec2 texCoord(Vec3 surfPos);
	public Vec3 normal(Vec3 surfPos);
	
}
