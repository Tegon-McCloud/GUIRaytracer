package ev.math;

public final class MathUtil {
	private MathUtil() {}
	
	public static float saturate(float f) {
		return f > 0 ? (f < 1 ? f : 1) : 0;
	}
	
	public static Vec3 saturate(Vec3 v) {
		return new Vec3(saturate(v.x), saturate(v.y), saturate(v.z));
	}
	
	
}
