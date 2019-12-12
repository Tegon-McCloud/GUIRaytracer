package ev.graphics.textures;

import ev.graphics.Texture2D;
import ev.math.Vec2;

public class Constant<T> implements Texture2D<T> {

	private T data;
	
	public Constant(T data) {
		this.data = data;
	}
	
	/**
	 * A slightly broken copy-constructor, that does not deep-copy the data. 
	 * This unfortunately isn't possible in Java.
	 * 
	 * @param orig the original to "copy" from
	 */
	public Constant(Constant<T> orig) {
		data = orig.data; // slight error, this should be a deep-copy, but it really shouldn't matter.
	}
	
	@Override
	public T get(Vec2 uv) {
		return data;
	}
	
}
