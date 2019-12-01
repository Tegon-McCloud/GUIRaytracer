package ev.graphics;

import ev.graphics.textures.Checkerboard;
import ev.graphics.textures.Constant;
import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

/**
 * A shape is anything with a 3D representation that could part of a scene.
 * Scene forces objects to implement necessary methods for rendering.
 */
public abstract class Shape {
	
	public static final Texture2D<Vec3> DEFAULT_DIFFUSE = new Checkerboard(new Vec3(0, 0, 0.5f), new Vec3(0, 0.5f, 0), 8);
	public static final Texture2D<Vec2> DEFAULT_SPECULAR = new Constant<Vec2>(new Vec2(0.6f, 24.0f)); // 
	
	private Texture2D<Vec3> diffuse;
	private Texture2D<Vec2> specular;
	
	public Shape() {
		
		diffuse = new Checkerboard((Checkerboard)DEFAULT_DIFFUSE); // copy default, as none were provided
		specular =  new Constant<Vec2>((Constant<Vec2>)DEFAULT_SPECULAR);
	}
	
	/**
	 * Finds the distance to the nearest intersection between r and this, or Float.POSITIVE_INFINITY if none were found.
	 * 
	 * @param r the Ray that we will check if intersects the Shape
	 * @return the distance from the origin of the Ray to the Shape, or NAN if it doesn't intersect
	 */
	public abstract float intersect(Ray r);
	
	/**
	 * Takes a Vec3 that should point to the surface of this which it then maps down to tangent space and return that point.
	 * 
	 * @param surfPos a Vec3 with the position at which, the tangent space coordinates are desired
	 * @return a Vec2 holding the uv coordinates at surfPos
	 */
	public abstract Vec2 texCoord(Vec3 surfPos);
	
	/**
	 * 
	 * @param surfPos a Vec3 with the surface position at which the caller wants the normal
	 * @return a Vec3 containing the normal at surfPos
	 */
	public abstract Vec3 normal(Vec3 surfPos);

	/**
	 * @return the diffuse texture
	 */
	public Texture2D<Vec3> getDiffuse() {
		return diffuse;
	}

	/**
	 * @return the specular texture
	 */
	public Texture2D<Vec2> getSpecular() {
		return specular;
	}

	/**
	 * @param diffuse the diffuse texture to set
	 */
	public void setDiffuse(Texture2D<Vec3> diffuse) {
		this.diffuse = diffuse;
	}

	/**
	 * @param specular the specular texture to set
	 */
	public void setSpecular(Texture2D<Vec2> specular) {
		this.specular = specular;
	}
	
	
	
}
