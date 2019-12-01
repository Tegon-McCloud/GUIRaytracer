package ev.graphics;

import java.awt.image.BufferedImage;

import ev.math.Ray;
import ev.math.Vec3;

/**
 * Raytracer is the most vanilla implementation of the IRenderer interface.
 * It renders scenes using a basic raytracing technique.
 */
public class Raytracer implements Renderer {
	
	private Scene scene;
	private Camera camera;
	
	@Override
	public BufferedImage render(Scene s, Camera c) {
		scene = s;
		camera = c;
		
		BufferedImage img = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		System.out.println("Rendering: width: " + c.getWidth() + ", height: " + c.getHeight());
		
		for(int i = 0; i < c.getWidth(); i++) {
			for(int j = 0; j < c.getHeight(); j++) {
				
				Vec3 col = trace(c.generateRay(i, j), 0);
				
				int argb = 0;
				argb |= ((int) (col.x * 255)) << 16;
				argb |= ((int) (col.y * 255)) << 8;
				argb |= ((int) (col.z * 255)) << 0;
				
				img.setRGB(i, j, argb);
			}
		}
		
		return img;
	}
	

	private Intersection cast(Ray r) {
		
		Intersection closest = new Intersection(r, null, Float.POSITIVE_INFINITY); // intersect with background (null) at infinity

		for(Shape s : scene.shapes) {
			
			Intersection current = new Intersection(r, s);
			
			if(current.getDist() < closest.getDist()) { // if the current intersection is closer than closest:
				closest = current; // closest is current
			}
			
		}
		
		return closest;
	}
	
	private Vec3 trace(Ray r, int depth) {
		
		Intersection intersection = cast(r); // find whatever r intersects with first.
		if(intersection.getShape() == null) {
			return scene.background;
		}
		
		return intersection.getShape().getDiffuse().get( intersection.getShape().texCoord( intersection.getRay().insert(intersection.getDist()) ) );
		
	}

}
