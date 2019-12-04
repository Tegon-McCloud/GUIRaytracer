package ev.math;

import java.lang.Math;

public final class MathUtil {
	private MathUtil() {}
	
	public static float PI = (float) Math.PI;
	public static float E = (float) Math.E;
	public static float EPSILON = (float) 0.0001f;
	
	
	public static float saturate(float x) {
		return x > 0 ? (x < 1 ? x : 1) : 0;
	}
	
	public static Vec3 saturate(Vec3 v) {
		return new Vec3(saturate(v.x), saturate(v.y), saturate(v.z));
	}
	
	public static float abs(float x) {
		return (float)Math.abs(x);
	}
	
	public static float sin(float x) {
		return (float)Math.sin(x);
	}
	
	public static float asin(float x) {
		return (float)Math.asin(x);
	}
	
	public static float cos(float x) {
		return (float)Math.cos(x);
	}
	
	public static float acos(float x) {
		return (float)Math.acos(x);
	}
	
	public static float tan(float x) {
		return (float)Math.tan(x);
	}
	
	public static float atan(float x) {
		return (float)Math.atan(x);
	}
	
	public static float atan2(float y, float x) {
		return (float)Math.atan2(y, x);
	}

	public static float sqrt(float x) {
		return (float)Math.sqrt(x);
	}
	
	public static float exp(float x) {
		return (float)Math.exp(x);
	}
	
	public static float pow(float a, float b) {
		return (float)Math.pow(a, b);
	}
	
	public static float max(float a, float b) {
		return b < a ? a : b;
	}
	
	public static float min(float a, float b) {
		return b > a ? a : b;
	}
	
	public static int max(int... is) {
		int max = Integer.MIN_VALUE;
		for(int i : is) if(max < i) max = i;
		return max;
	}
	
	public static int min(int a, int b) {
		return a > b ? b : a;
	}
	
	public static float floor(float x) {
		return (float)Math.floor(x);
	}
	
	public static float ceil(float x) {
		return (float)Math.ceil(x);
	}
	
}
