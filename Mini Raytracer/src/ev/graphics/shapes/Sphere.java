package ev.graphics.shapes;

import ev.graphics.Shape;
import ev.math.Ray;
import ev.math.Vec2;
import ev.math.Vec3;

/**
 * A sphere.
 */
public class Sphere extends Shape {

	public Vec3 pos;
	public float radius;
	
	@Override
	public float intersect(Ray r) {
		// TODO fix comments and make them the same language
		// det ene origin minus det andet
		Vec3 CA = r.ori.sub(pos);
		
		// ting skal ind i kvadratformlen, så vi har kvadratfomelns a, b og c, som er de 3 floats nedenunder
		float a = r.dir.dot(r.dir);
		float b = 2 * r.dir.dot(CA);
		float c = CA.dot(CA) - radius*radius;
		
		// diskriminanten
		float diskriminantInEnglish = b*b - 4*a*c;
		
		if (diskriminantInEnglish <= 0) {
			// do smt
		}
		
		// de 2 intersections
		float intersection1 = (-b + (float)Math.sqrt(diskriminantInEnglish))/(2*a);
		float intersection2 = (-b - (float)Math.sqrt(diskriminantInEnglish))/(2*a);
		
		// logic to find out which intersection to return
		if (intersection1 < 0 && intersection2 < 0) {
			if(intersection1 > intersection2 && intersection1 > 0) {
				return intersection1; //tror jeg
			} else {
				return intersection2; //tror jeg, men det kan være man skal checke noget mere
			}
		} else {
			// do smt
		}
		
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
