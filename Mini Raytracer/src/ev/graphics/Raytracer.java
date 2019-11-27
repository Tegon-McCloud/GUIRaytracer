package ev.graphics;

import java.awt.image.BufferedImage;

import ev.math.Ray;
import ev.math.Vec3;

/**
 * Raytracer is the most vanilla implementation of the IRenderer interface.
 * It renders scenes using a basic raytracing technique.
 * 
 * @since 1.0
 */
public class Raytracer implements IRenderer {

	@Override
	public BufferedImage render(Scene s, Camera c) {
		return null;
		
	}
	
	public Intersection trace(Ray r, Scene s) {
		return null;
	}
	
	public Vec3 phong(Ray r, Intersection i) {
		return null;
	}

}

class Intersection{
	public Shape s;
	public float dist;
}
