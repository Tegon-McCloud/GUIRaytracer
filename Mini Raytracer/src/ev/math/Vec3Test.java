package ev.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Vec3Test {

	public static final Vec3 A = new Vec3(3, 4, 5);
	public static final Vec3 B = new Vec3(5, 6, 7);
	
	@Test
	void addTest() {
		
		final Vec3 EXPECTED = new Vec3(8, 10, 12);
		final Vec3 ACTUAL = A.add(B);
		
		assertVecEquals(EXPECTED, ACTUAL, 0.1f);
	}
	
	private static void assertVecEquals(Vec3 expected, Vec3 actual, float percentageError) {
		assertEquals(expected.x, actual.x, expected.x * 0.01f * percentageError);
		assertEquals(expected.y, actual.y, expected.y * 0.01f * percentageError);
		assertEquals(expected.z, actual.z, expected.z * 0.01f * percentageError);
	}

}
