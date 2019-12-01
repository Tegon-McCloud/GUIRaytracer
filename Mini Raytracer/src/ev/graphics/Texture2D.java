package ev.graphics;

import ev.math.Vec2;

/**
 * A Texture2D is an interface implemented by anything that has some notion of continuous 2D data that can be returned.
 * It only requires 1 function, <code>get()</code>, which takes a 2D vector and returns some data of type T.
 * 
 * @param <T> the datatype this texture will return upon query.
 */
public interface Texture2D<T> {
	/**
	 * Returns whatever is at <code>uv</code>
	 * 
	 * @param uv the coordinates of the wanted data.
	 * @return the data at <code>uv</code>
	 */
	public T get(Vec2 uv);
	
}
