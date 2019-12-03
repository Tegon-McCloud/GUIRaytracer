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
	
	
	
	/**
	 * @param pos
	 * @param radius
	 */
	public Sphere(Vec3 pos, float radius) {
		super();
		this.pos = pos;
		this.radius = radius;
	}

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
			return Float.POSITIVE_INFINITY;
		}
		
		// de 2 intersections
		float t1 = (-b + (float)Math.sqrt(diskriminantInEnglish))/(2*a);
		float t2 = (-b - (float)Math.sqrt(diskriminantInEnglish))/(2*a);
		
		// logic to find out which intersection to return
		if(t1 > 0 || t2 > 0) {
			return t1 < t2 && t1 > 0 ? t1 : t2;
		}
		return Float.POSITIVE_INFINITY;
	}
	
	@Override
	public Vec2 texCoord(Vec3 surfPos) {
		Vec2 uv = new Vec2();
		Vec3 normal = normal(surfPos);
		uv.x = (float) (Math.atan2(normal.x, normal.z) / (2*Math.PI) + 0.5);
		uv.y = normal.y * 0.5f + 0.5f;
		return uv;
	}

	@Override
	public Vec3 normal(Vec3 surfPos) {
		return surfPos.sub(pos).mul(1 / radius);
	}
	
}
