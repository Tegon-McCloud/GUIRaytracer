package ev.gui;

import javax.swing.JTabbedPane;

import ev.graphics.Camera;

@SuppressWarnings("serial")
public class ControlPanel extends JTabbedPane {
	
	private CameraPanel cameraPanel;
	
	
	
	public ControlPanel() {
		
		cameraPanel = new CameraPanel();
		addTab("Camera", cameraPanel);
		
		
	}
	
	public Camera getCamera() {
		return cameraPanel.getCamera();
	}
	
}
