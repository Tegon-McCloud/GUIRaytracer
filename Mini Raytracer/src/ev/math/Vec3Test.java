package ev.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * compare Vec3's methods output with Maples
 */
class Vec3Test {

	public static final Vec3 A = new Vec3(3, 4, 5);
	public static final Vec3 B = new Vec3(6, 7, 8);
	
	@Test
	void addTest() {
		
		final Vec3 EXPECTED = new Vec3(9, 11, 13);
		final Vec3 ACTUAL = A.add(B);
		
		assertVecEquals(EXPECTED, ACTUAL, 0.1f);
	}
	
	@Test
	void subTest() {
		
		final Vec3 EXPECTED = new Vec3(-3, -3, -3);
		final Vec3 ACTUAL = A.sub(B);
		
		assertVecEquals(EXPECTED, ACTUAL, 0.1f);
	}
	
	@Test
	void dotTest() {
		
		final float EXPECTED = 86;
		final float ACTUAL = A.dot(B);
		
		assertEquals(EXPECTED, ACTUAL, EXPECTED * 0.001f);
	}
	
	@Test
	void crossTest() {
		
		final Vec3 EXPECTED = new Vec3(-3, 6, -3);
		final Vec3 ACTUAL = A.cross(B);
			
		assertVecEquals(EXPECTED, ACTUAL, 0.1f);
	}
	
	
	
	private static void assertVecEquals(Vec3 expected, Vec3 actual, float percentageError) {
		assertEquals(expected.x, actual.x, (float)Math.abs(expected.x) * 0.01f * percentageError);
		assertEquals(expected.y, actual.y, (float)Math.abs(expected.y) * 0.01f * percentageError);
		assertEquals(expected.z, actual.z, (float)Math.abs(expected.z) * 0.01f * percentageError);
	}

}
