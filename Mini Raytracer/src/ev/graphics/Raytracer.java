package ev.graphics;

import static ev.math.MathUtil.*;

import java.awt.image.BufferedImage;

import ev.math.Ray;
import ev.math.Vec2;
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
				
				Vec3 col = trace(c.generateRay(i, j), 0).saturated();
				
				int argb = 0;
				argb |= ((int) (col.x * 255)) << 16;
				argb |= ((int) (col.y * 255)) << 8;
				argb |= ((int) (col.z * 255)) << 0;
				
				img.setRGB(i, j, argb);
			}
		}
		
		return img;
	}
	
	/**
	 * Beware 
	 * 
	 * @param r
	 * @return
	 */
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
	
	/**
	 * 
	 * 
	 * @param r
	 * @param depth
	 * @return
	 */
	private Vec3 trace(Ray r, int depth) {
		
		Intersection intersection = cast(r); // find whatever r intersects with first.
		if(intersection.getShape() == null) {
			return scene.background;
		}
		
		// Phong shading:
		
		Vec3 hitPos = intersection.getRay().insert(intersection.getDist());		// the point of intersection
		Vec3 normal = intersection.getShape().normal(hitPos);					// the shapes normal at the intersection point
		Vec2 texCoord = intersection.getShape().texCoord( hitPos);				// the texture coordinates at the intersection point
		Vec3 diffuseCol = intersection.getShape().getDiffuse().get(texCoord);	// the diffuse RGB values at those texture coordinates
		Vec2 specular = intersection.getShape().getSpecular().get(texCoord);	// the specular intensity and glossiness at those texture coordinates
		
		// ambient light component
		Vec3 color = diffuseCol.mul(0.2f);										// ambient is set to 0.2 white light
		
		// diffuse light component
		for(DistantLight l : scene.lights) {
			float LdotN = l.getDir().mul(-1).dot(normal);
			if(LdotN < 0) continue; 
			color = color.add(diffuseCol.mul(LdotN).mul(l.getCol().mul(l.getIntensity())));
		}
		
		// specular light component 
		for(DistantLight l : scene.lights) {
			float RdotV = l.getDir().reflect(normal).dot(intersection.getRay().dir.mul(-1));
			if(RdotV < 0) continue; 
			color = color.add(l.getCol().mul(pow(RdotV, specular.y)*specular.x));
		}
		
		return color;
		
	}

}
