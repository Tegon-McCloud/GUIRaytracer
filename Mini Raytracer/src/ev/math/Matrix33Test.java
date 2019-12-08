package ev.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Matrix33Test {

	public static final Matrix33 A = new Matrix33(1, 2, 3, 4, 5, 6, 7, 8, 9);
	// Matrix A is:
	// |	1	2	3	|
	// |	4	5	6	|
	// |	7	8	9	|
	public static final Matrix33 B = new Matrix33(10, 11, 12, 13, 14, 15, 16, 17, 18);
	// Matrix B is:
	// |	10	11	12	|
	// |	13	14	15	|
	// |	16	17	18	|
	
	@Test
	void mulTest() {
		
		final Matrix33 EXPECTED = new Matrix33(84, 90, 96, 201, 216, 231, 318, 342, 366);
		// |	1	2	3	|		|	10	11	12	|		|	84	90	96	|
		// |	4	5	6	|	*	|	13	14	15	|	=	|	201	216	231	|
		// |	7	8	9	|		|	16	17	18	|		|	318	342	366	|
		final Matrix33 ACTUAL = A.mul(B);
		
		assertMatrixEquals(EXPECTED, ACTUAL, 0.1f);
	}
	
	@Test
	void determiantTest() {
		
		final float EXPECTED = 0;
		final float ACTUAL = B.determinant();
		
		assertEquals(EXPECTED, ACTUAL);	
	}
	
	private static void assertMatrixEquals(Matrix33 expected, Matrix33 actual, float percentageError) {
		assertEquals(expected.m00, actual.m00, (float)Math.abs(expected.m10) * 0.01f * percentageError);
		assertEquals(expected.m01, actual.m01, (float)Math.abs(expected.m10) * 0.01f * percentageError);
		assertEquals(expected.m02, actual.m02, (float)Math.abs(expected.m10) * 0.01f * percentageError);
		
		assertEquals(expected.m10, actual.m10, (float)Math.abs(expected.m10) * 0.01f * percentageError);
		assertEquals(expected.m11, actual.m11, (float)Math.abs(expected.m11) * 0.01f * percentageError);
		assertEquals(expected.m12, actual.m12, (float)Math.abs(expected.m12) * 0.01f * percentageError);
		
		assertEquals(expected.m20, actual.m20, (float)Math.abs(expected.m20) * 0.01f * percentageError);
		assertEquals(expected.m21, actual.m21, (float)Math.abs(expected.m21) * 0.01f * percentageError);
		assertEquals(expected.m22, actual.m22, (float)Math.abs(expected.m22) * 0.01f * percentageError);
		
	}
	
}
