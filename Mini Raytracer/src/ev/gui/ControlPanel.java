package ev.gui;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ControlPanel extends JTabbedPane {
	
	private CameraPanel cameraPanel;
	
	
	
	public ControlPanel() {
		
		cameraPanel = new CameraPanel();
		addTab("Camera", cameraPanel);
		
		
	}
	
	public CameraPanel getCameraPanel() {
		return cameraPanel;
	}
	
}
