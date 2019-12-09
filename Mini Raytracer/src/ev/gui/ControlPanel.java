package ev.gui;

import javax.swing.JTabbedPane;

import ev.graphics.Camera;
import ev.graphics.Scene;

@SuppressWarnings("serial")
public class ControlPanel extends JTabbedPane {

	private CameraPanel cameraPanel;
	private ShapePanel shapePanel;
	private LightPanel lightPanel;

	public ControlPanel() {


	}

	public void init() {
		cameraPanel = new CameraPanel();
		addTab("Camera", cameraPanel);

		shapePanel = new ShapePanel();
		addTab("Shapes", shapePanel);

		lightPanel = new LightPanel();
		addTab("Lighting", lightPanel);
	}

	public Camera getCamera() {
		return cameraPanel.getCamera();
	}

	public Scene getScene() {
		Scene s = new Scene(lightPanel.getBGColor());
		s.lights = lightPanel.getLights(); 
		s.shapes = shapePanel.getShapes();
		return new Scene(s); // deep-copy 
	}

}
