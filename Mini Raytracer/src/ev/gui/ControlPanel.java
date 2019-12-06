package ev.gui;

import javax.swing.JTabbedPane;

import ev.graphics.Camera;

@SuppressWarnings("serial")
public class ControlPanel extends JTabbedPane {
	
	private CameraPanel cameraPanel;
	private ShapePanel shapePanel;
	private LightPanel lightPanel;
	
	public ControlPanel() {
		
		cameraPanel = new CameraPanel();
		addTab("Camera", cameraPanel);
		
		shapePanel = new ShapePanel();
		addTab("Shapes", shapePanel);
		
		lightPanel = new LightPanel();
		addTab("Lights", lightPanel);
		
	}
	
	public Camera getCamera() {
		return cameraPanel.getCamera();
	}
	
}
