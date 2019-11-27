package ev.graphics;

import java.util.List;

import ev.math.Vec3;

/**
 * A Scene object aggregates lights and shapes for easy passing in rendering.
 * It also holds the background to render with.
 * 
 * @since 1.0
 */
public class Scene {
	
	public Vec3 background;
	public List<Shape> shapes;	
	
}
