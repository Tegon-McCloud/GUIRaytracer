package ev.graphics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import ev.math.Vec3;

/**
 * A Scene object aggregates lights and shapes for easy passing in rendering.
 * It also holds the background to render with.
 */
public class Scene {
	
	public Vec3 background;
	public HashMap<String, Shape> shapes;
	public HashMap<String, DistantLight> lights;
	
	/**
	 * Constructs a Scene with no shapes nor lights
	 * 
	 * @param background the color of the background in the scene.
	 */
	public Scene(Vec3 background) {
		shapes = new HashMap<String, Shape>();
		lights = new HashMap<String, DistantLight>();
		this.background = background;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param s the Scene to copy
	 */
	public Scene(Scene s) {
		this(new Vec3(s.background));
		
		Set<Entry<String, Shape>> shapesSet = s.shapes.entrySet();
		for(Entry<String, Shape> e : shapesSet) {
			shapes.put(e.getKey(), e.getValue().clone());
		}
		
		Set<Entry<String, DistantLight>> lightsSet = s.lights.entrySet();
		for(Entry<String, DistantLight> e : lightsSet) {
			lights.put(e.getKey(), new DistantLight(e.getValue()));
		}
		
	}
	
}
