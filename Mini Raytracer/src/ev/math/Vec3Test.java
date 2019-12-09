package ev.math;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * compare Vec3's methods output with Maples
 */
class Vec3Test {

	public static final Vec3 A = new Vec3(3, 4, 5);
	public static final Vec3 B = new Vec3(6, 7, 8);
	
	@Test
	void addTest() {
		
		final Vec3 expected = new Vec3(9, 11, 13);
		final Vec3 actual = A.add(B);
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void subTest() {
		
		final Vec3 expected = new Vec3(-3, -3, -3);
		final Vec3 actual = A.sub(B);
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void mul1Test() {
		
		final Vec3 expected = new Vec3(6, 8, 10);
		final Vec3 actual = A.mul(2);
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void mul2test() {
		
		final Vec3 expected = new Vec3(18, 28, 40);
		final Vec3 actual = A.mul(B);
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void mul3test() {
		
		final Vec3 expected = new Vec3(3, -4, 5);
		final Vec3 actual = A.mul(new Matrix33(1, 0, 0, 0, -1, 0, 0, 0, 1)); // flip y coordinate
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void divTest() {
		
		final Vec3 expected = new Vec3(1.5f, 2, 2.5f);
		final Vec3 actual = A.div(2);
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void dotTest() {
		
		final float expected = 86;
		final float actual = A.dot(B);
		
		assertEquals(expected, actual, expected * 0.001f);
	}
	
	@Test
	void crossTest() {
		
		final Vec3 expected = new Vec3(-3, 6, -3);
		final Vec3 actual = A.cross(B);
			
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void normalizedTest() {
		
		final Vec3 expected = new Vec3(0.4242640687119285f, 0.565685424949238f, 0.7071067811865475f);
		final Vec3 actual = A.normalized();
		
		assertVecEquals(expected, actual, 0.1f);
		
	}
	
	@Test
	void saturatedTest() {
		
		final Vec3 expected = new Vec3(0.4f, 0f, 1f);
		final Vec3 actual = new Vec3(0.4f, -0.3f, 7f).saturated();
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void negatedTest() {
		
		final Vec3 expected = new Vec3(-3, -4, -5);
		final Vec3 actual = A.negated();
		
		assertVecEquals(expected, actual, 0.1f);
	}

	@Test
	void lengthTest() {
		
		final float expected = 7.07106781187f;
		final float actual = A.length();
		
		assertEquals(expected, actual, expected * 0.001f);
	}
	
	@Test
	void reflectTest() {
		
		final Vec3 expected = new Vec3(3, 4, -5);
		final Vec3 actual = A.reflect(new Vec3(0, 0, 1));
		
		assertVecEquals(expected, actual, 0.1f);
	}
	
	@Test
	void toStringTest() {
		
		final String expected = "(3.0, 4.0, 5.0)";
		final String actual = A.toString();
		
		assertEquals(expected, actual);
	}
	
	private static void assertVecEquals(Vec3 expected, Vec3 actual, float percentageError) {
		assertEquals(expected.x, actual.x, (float)Math.abs(expected.x) * 0.01f * percentageError);
		assertEquals(expected.y, actual.y, (float)Math.abs(expected.y) * 0.01f * percentageError);
		assertEquals(expected.z, actual.z, (float)Math.abs(expected.z) * 0.01f * percentageError);
	}

}
