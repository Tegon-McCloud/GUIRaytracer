package ev.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import ev.math.Vec3;

/**
 * A Scene object aggregates lights and shapes for easy passing in rendering.
 * It also holds the background to render with.
 */
public class Scene {
	
	public Vec3 background;
	public HashMap<String, Shape> shapes;
	public HashMap<String, DistantLight> lights;
	
	public Scene(Vec3 background) {
		shapes = new HashMap<String, Shape>();
		lights = new HashMap<String, DistantLight>();
		this.background = background;
	}
	
}
