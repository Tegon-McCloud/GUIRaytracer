package ev.gui;

import static ev.math.MathUtil.*;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ev.graphics.Camera;
import ev.graphics.DistantLight;
import ev.graphics.Scene;
import ev.graphics.shapes.Plane;
import ev.graphics.shapes.Sphere;
import ev.math.Vec3;


/**
 * The GUI class is responsible for handling all GUI activity, including handling of input.
 */
public class GUI {

	private static JFrame frame;
	
	private static ControlPanel cp;
	private static RenderPanel rp;
	
	public static void main(String[] args) throws Throwable {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		SwingUtilities.invokeLater(() -> {
			frame = new JFrame("Render");
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			rp = new RenderPanel();
			rp.setMinimumSize(new Dimension(400, 400));
			
			cp = new ControlPanel();
			
			JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cp, rp);
			jsp.setPreferredSize(new Dimension(800, 600));
			jsp.setDividerLocation(400);
			
			frame.setContentPane(jsp);
			
			frame.pack();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
		});

		
	}
	
	public static Scene getScene() {
		// test scene
		Scene s = new Scene(new Vec3(0.5f, 0.0f, 0.0f));
		s.lights.put("a light", new DistantLight(new Vec3(1, 2, 1), new Vec3(1,1,1), 1));
		s.shapes.put("a sphere", new Sphere(new Vec3(0, 0, 5), 1));
		s.shapes.put("a plane", new Plane(new Vec3(0, -1, 0), new Vec3(0, -1, 0)));
		return s;
	}
	
	public static Camera getCamera() {
		return cp.getCamera(); 
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static ControlPanel getControlPanel() {
		return cp;
	}
	
}
