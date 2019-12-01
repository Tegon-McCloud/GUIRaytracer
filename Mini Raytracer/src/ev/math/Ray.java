package ev.math;

/**
 * Ray describes a line in 3D on parametric form.
 */
public class Ray {
	public Vec3 ori, dir;

	public Ray(Vec3 ori, Vec3 dir) {
		this.ori = ori;
		this.dir = dir;
	}
	
	public Vec3 insert(float t) {
		return ori.add(dir.mul(t));
	}
	
}
