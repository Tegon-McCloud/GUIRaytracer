package ev.gui;

import static ev.math.MathUtil.PI;
import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.VERTICAL_CENTER;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		
		JButton editButton = new JButton("Edit");
		editButton.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(NORTH, editButton, 5, SOUTH, miscLabel);
		layout.putConstraint(WEST, editButton, 5, WEST, this);
		add(editButton);
		
		editButton.addActionListener(e -> {
			
			JDialog dialog = new JDialog(GUI.getFrame(), "Edit camera settings");
			dialog.setResizable(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
			dialog.setContentPane(new CameraEditPanel(this, dialog));
			dialog.pack();
			

			dialog.setVisible(true);;
		});
		
	}
	
	public void updateLabels() {
		
		posLabel.setText(String.format(Locale.US, "<html>x: %f<br>y: %f<br>z: %f</html>", state.getPos().x, state.getPos().y, state.getPos().z));
		dirLabel.setText(String.format(Locale.US, "<html>roll: %f&deg<br>pitch: %f&deg<br>yaw: %f&deg</html>", state.getRoll() * 180f/PI, state.getPitch() * 180f/PI, state.getYaw() * 180f/PI));
		sizeLabel.setText(String.format(Locale.US, "<html>width: %dpx<br>height: %dpx</html>", state.getWidth(), state.getHeight()));
		miscLabel.setText(String.format(Locale.US, "<html>fov: %f&deg<br>max bounces: %d</html>", state.getFov() * 180f/PI, state.getMaxDepth()));
		
	}
	
	public Camera getCamera() {
		return state;
	}
	
}

@SuppressWarnings("serial")
class CameraEditPanel extends JPanel {
	
	public CameraEditPanel(CameraPanel target, Dialog container) {
		
		Camera cam = target.getCamera();
		
		setPreferredSize(new Dimension(180, 360));
		
		GridLayout layout = new GridLayout(0, 1, 12, 12);
		setLayout(layout);
		
		JTextField xField		=	addLabeledField("x:", cam.getPos().x + "");
		JTextField yField		=	addLabeledField("y:", cam.getPos().y + "");
		JTextField zField		=	addLabeledField("z:", cam.getPos().z + "");
		JTextField rollField	=	addLabeledField("roll:", cam.getRoll() * 180f/PI + "");
		JTextField pitchField	=	addLabeledField("pitch:", cam.getPitch() * 180f/PI + "");
		JTextField yawField		=	addLabeledField("yaw:", cam.getYaw() * 180f/PI + "");
		JTextField widthField	=	addLabeledField("width:", cam.getWidth() + "");
		JTextField heightField	=	addLabeledField("height:", cam.getHeight() + "");
		JTextField fovField		=	addLabeledField("fov:", cam.getFov() * 180f/PI + "");
		JTextField bounceField	=	addLabeledField("max bounces:", cam.getMaxDepth() + "");
		
		JPanel buttonPanel = new JPanel();
		SpringLayout panelLayout = new SpringLayout();
		buttonPanel.setLayout(panelLayout);
		
		JButton applyButton = new JButton("Apply");
		applyButton.setPreferredSize(new Dimension(80, 20));
		panelLayout.putConstraint(VERTICAL_CENTER, applyButton, 0, VERTICAL_CENTER, buttonPanel);
		panelLayout.putConstraint(WEST, applyButton, 5, WEST, buttonPanel);
		buttonPanel.add(applyButton);
	
		JButton closeButton = new JButton("Close");
		closeButton.setPreferredSize(new Dimension(80, 20));
		panelLayout.putConstraint(VERTICAL_CENTER, closeButton, 0, VERTICAL_CENTER, buttonPanel);
		panelLayout.putConstraint(WEST, closeButton, 10, EAST, applyButton);
		buttonPanel.add(closeButton);

		add(buttonPanel);
		
		applyButton.addActionListener(e -> {
			float x, y, z, roll, pitch, yaw, fov;
			int width, height, maxDepth;
			
			try {
				
				x 			= Float.parseFloat(xField.getText());
				y 			= Float.parseFloat(yField.getText());
				z 			= Float.parseFloat(zField.getText());
				roll 		= Float.parseFloat(rollField.getText()) * PI/180f;
				pitch 		= Float.parseFloat(pitchField.getText()) * PI/180f;
				yaw 		= Float.parseFloat(yawField.getText()) * PI/180f;
				width 		= Integer.parseInt(widthField.getText());
				height		= Integer.parseInt(heightField.getText());
				fov			= Float.parseFloat(fovField.getText()) * PI/180f;
				maxDepth	= Integer.parseInt(bounceField.getText());
				
				cam.moveTo(new Vec3(x, y, z));
				cam.setYawPitchRoll(yaw, pitch, roll);
				cam.resize(width, height);
				cam.setFov(fov);
				cam.setMaxDepth(maxDepth);
				
				target.updateLabels();
			
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(
						container,
						"Please make sure all entries are numbers and that \"width\", \"height\" and \"bounce\" are integers",
						"Number format error",
						JOptionPane.ERROR_MESSAGE
				);
			}
			
		});
		
		closeButton.addActionListener(e -> {
			container.setVisible(false);
			container.dispose();
		});
		
	}
	
	private JTextField addLabeledField(String labelText, String fieldText) {
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
		JLabel label = new JLabel(labelText);
		layout.putConstraint(VERTICAL_CENTER, label, 0, VERTICAL_CENTER, panel);
		layout.putConstraint(WEST, label, 5, WEST, panel);
		panel.add(label);
		
		JTextField field = new JTextField(fieldText);
		field.setPreferredSize(new Dimension(80, 20));
		layout.putConstraint(VERTICAL_CENTER, field, 0, VERTICAL_CENTER, panel);
		layout.putConstraint(EAST, field, -5, EAST, panel);
		panel.add(field);
		
		add(panel);
		return field;
	}
	
}
