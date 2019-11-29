package ev.graphics;

import ev.math.Ray;

/**
 * Intersection holds data about where a ray and a shape intersects each other.
 */
public class Intersection {
	private Ray r;
	private Shape s;
	private float dist;
	
	/**
	 * Construct an intersection and calculate the distance
	 * 
	 * @param r the Ray that is intersecting some shape
	 * @param s the Shape that the ray is intersecting
	 */
	public Intersection(Ray r, Shape s) {
		this.r = r;
		this.s = s;
		this.dist = s.intersect(r);
	}
	

	/**
	 * Construct an intersection and define the distance
	 * 
	 * @param r the Ray that is intersecting some shape
	 * @param s the Shape that the ray is intersecting
	 */
	public Intersection(Ray r, Shape s, float dist) {
		this.r = r;
		this.s = s;
		this.dist = dist;
	}
	
	/**
	 * @return the Ray this intersection is holding
	 */
	public Ray getRay() {
		return r;
	}
	
	/**
	 * @return the Shape this intersection is holding
	 */
	public Shape getShape() {
		return s;
	}
	
	/**
	 * @return the distance between the rays origin and the point of intersection between the ray and the shape
	 */
	public float getDist() {
		return dist;
	}
	
}
