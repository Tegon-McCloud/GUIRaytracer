package ev.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.util.Locale;

import javax.swing.BorderFactory;

import static ev.math.MathUtil.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import ev.graphics.Camera;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class CameraPanel extends JPanel {
	
	private Camera state;
	
	private JLabel posLabel, dirLabel, sizeLabel, miscLabel;
	
	public CameraPanel() {
		
		state = new Camera(new Vec3(0, 0, 0), 0, 0, 0, 1024, 1024, PI * 0.25f, 4);	// default camera position
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		posLabel = new JLabel();
		posLabel.setBorder(BorderFactory.createTitledBorder("Position:"));
		layout.putConstraint(NORTH, posLabel, 5, NORTH, this);
		layout.putConstraint(WEST, posLabel, 5, WEST, this);
		layout.putConstraint(EAST, posLabel, -5, EAST, this);
		add(posLabel);
		
		dirLabel = new JLabel();
		dirLabel.setBorder(BorderFactory.createTitledBorder("Direction:"));
		layout.putConstraint(NORTH, dirLabel, 5, SOUTH, posLabel);
		layout.putConstraint(WEST, dirLabel, 5, WEST, this);
		layout.putConstraint(EAST, dirLabel, -5, EAST, this);
		add(dirLabel);
		
		sizeLabel = new JLabel();
		sizeLabel.setBorder(BorderFactory.createTitledBorder("Image size:"));
		layout.putConstraint(NORTH, sizeLabel, 5, SOUTH, dirLabel);
		layout.putConstraint(WEST, sizeLabel, 5, WEST, this);
		layout.putConstraint(EAST, sizeLabel, -5, EAST, this);
		add(sizeLabel);
		
		miscLabel = new JLabel();
		miscLabel.setBorder(BorderFactory.createTitledBorder("Misc.:"));
		layout.putConstraint(NORTH, miscLabel, 5, SOUTH, sizeLabel);
		layout.putConstraint(WEST, miscLabel, 5, WEST, this);
		layout.putConstraint(EAST, miscLabel, -5, EAST, this);
		add(miscLabel);
		

		
		updateLabels();
		
	}
	
	private void updateLabels() {
		
		posLabel.setText(String.format(Locale.US, "<html>x: %f<br>y: %f<br>z: %f</html>", state.getPos().x, state.getPos().y, state.getPos().z));
		dirLabel.setText(String.format(Locale.US, "<html>roll: %f&deg<br>pitch: %f&deg<br>yaw: %f&deg</html>", state.getRoll() * 180f/PI, state.getPitch() * 180f/PI, state.getYaw() * 180f/PI));
		sizeLabel.setText(String.format(Locale.US, "<html>width: %dpx<br>height: %dpx</html>", state.getWidth(), state.getHeight()));
		miscLabel.setText(String.format(Locale.US, "<html>fov: %f&deg<br>Light bounces: %d</html>", state.getFov() * 180f/PI, state.getMaxDepth()));
		
	}
	
	
}
