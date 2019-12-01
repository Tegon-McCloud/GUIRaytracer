package ev.graphics;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ev.math.Vec3;

class CameraTest {

	@Test
	void generateRayTest() {
		new Camera(new Vec3(0, 1, -1), -(float)PI * 0.25f, 0, 0, 400, 400, (float)PI * 0.5f, 1);
		
		
		
	}

}
