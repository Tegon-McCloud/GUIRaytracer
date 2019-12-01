package ev.graphics.textures;

import ev.graphics.Texture2D;
import ev.math.Vec2;
import ev.math.Vec3;

/**
 * A procedural checkerboard pattern texture, meant for testing shapes.
 */
public class Checkerboard implements Texture2D<Vec3> {
	
	private Vec3 col1, col2;
	private int squaresInASide;

	/**
	 * @param col1 the color at the top-left corner
	 * @param col2 the other color
	 * @param sideSquares the number of squares desired for each side.
	 */
	public Checkerboard(Vec3 col1, Vec3 col2, int sideSquares) {
		super();
		this.col1 = col1;
		this.col2 = col2;
		this.squaresInASide = sideSquares;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param orig the original Checkerboard to be copied
	 */
	public Checkerboard(Checkerboard orig) {
		col1 = new Vec3(orig.col1);
		col2 = new Vec3(orig.col2);
		squaresInASide = orig.squaresInASide;
	}

	@Override
	public Vec3 get(Vec2 uv) {
		return (int) (uv.x * squaresInASide) % 2 == 0 ^ (int) (uv.y * squaresInASide) % 2 == 0 ? new Vec3(col1) : new Vec3(col2);
	}

	/**
	 * @return the first color
	 */
	public Vec3 getCol1() {
		return col1;
	}

	/**
	 * @return the second color
	 */
	public Vec3 getCol2() {
		return col2;
	}

	/**
	 * @return the number of squares in a side
	 */
	public int getSquareNum() {
		return squaresInASide;
	}

	/**
	 * @param col1 the new first color
	 */
	public void setCol1(Vec3 col1) {
		this.col1 = col1;
	}

	/**
	 * @param col2 the the new second color
	 */
	public void setCol2(Vec3 col2) {
		this.col2 = col2;
	}

	/**
	 * @param sideSquares the new number of squares in a side
	 */
	public void setSideSquares(int sideSquares) {
		this.squaresInASide = sideSquares;
	}
	
	
	
}
