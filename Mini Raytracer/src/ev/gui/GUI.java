package ev.gui;

import static java.lang.Math.PI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import ev.graphics.Camera;
import ev.graphics.DistantLight;
import ev.graphics.Scene;
import ev.graphics.shapes.Sphere;
import ev.math.Vec3;


/**
 * The GUI class is responsible for handling all GUI activity, including handling of input.
 */
public class GUI {
	

	
	private static JFrame frame;
	
	public static void main(String[] args) throws Throwable {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		frame = new JFrame("Render");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// for testing
//		ImagePanel imgp = new ImagePanel();
		
//		Scene s = new Scene(new Vec3(0f, 0f, 0.5f));
//		Camera c = new Camera(new Vec3(), 0, 0, 0, 400, 400, (float)Math.PI/2, 4);
//		
//		Raytracer r = new Raytracer();
//		
//		imgp.set(r.render(s, c));
//		
//		imgp.setPreferredSize(new Dimension(400, 400));
//		imgp.setBackground(new Color(0, 0, 0));
//		frame.setContentPane(imgp);
		
		
		RenderPanel rp = new RenderPanel();
		rp.setMinimumSize(new Dimension(400, 400));
		
		ControlPanel cp = new ControlPanel();
		
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cp, rp);
		jsp.setPreferredSize(new Dimension(800, 600));
		jsp.setDividerLocation(400);
		
		frame.setContentPane(jsp);
		
		// !for testing
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
	}
	
	public static Scene getScene() {
		// test scene
		Scene s = new Scene(new Vec3(0.5f, 0.0f, 0.0f));
		s.lights.add(new DistantLight(new Vec3(0, 2, 1), new Vec3(1,1,1), 1));
		s.shapes.add(new Sphere(new Vec3(0,0,0), 5));
		return s;
	}
	
	public static Camera getCamera() {
		// test camera
		return new Camera(new Vec3(0, 0, -10), 0, 0, 0, 20, 20, (float)PI * 0.5f, 1);
	}
	
}
