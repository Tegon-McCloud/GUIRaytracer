package ev.graphics;

import java.awt.image.BufferedImage;

import ev.math.Ray;
import ev.math.Vec3;

public class Raytracer implements IRenderer {

	@Override
	public BufferedImage render(Scene s, Camera c) {
		return null;
		
	}
	
	public Intersection trace(Ray r) {
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