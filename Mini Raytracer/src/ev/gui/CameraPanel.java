package ev.gui;

import static ev.math.MathUtil.PI;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ev.graphics.Camera;
import ev.math.Vec3;

/**
 * The CameraPanel allows users to control the virtual camera that is currently being rendered from.
 */
@SuppressWarnings("serial")
public class CameraPanel extends JPanel {

	private Vec3Panel posPanel, dirPanel;
	private LabeledField widthField, heightField, fovField, depthField;

	/**
	 * Contructs a CameraPanel. 
	 */
	public CameraPanel() {

		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// position panel
		posPanel = new Vec3Panel();
		posPanel.setBorder(BorderFactory.createTitledBorder("Position"));
		posPanel.setPreferredSize(new Dimension(150, posPanel.getPreferredSize().height));

		layout.putConstraint(NORTH, posPanel, 5, NORTH, this);
		layout.putConstraint(WEST, posPanel, 5, WEST, this);
		add(posPanel);
		// !position panel
		
		// direction panel
		dirPanel = new Vec3Panel("roll", "pitch", "yaw", 0, 0, 0);
		dirPanel.setBorder(BorderFactory.createTitledBorder("Direction"));
		dirPanel.setPreferredSize(new Dimension(150, dirPanel.getPreferredSize().height));

		layout.putConstraint(NORTH, dirPanel, 5, SOUTH, posPanel);
		layout.putConstraint(WEST, dirPanel, 5, WEST, this);
		add(dirPanel);
		// !direction panel
		
		// size panel
		JPanel sizePanel = new JPanel();
		sizePanel.setBorder(BorderFactory.createTitledBorder("Resolution"));
		sizePanel.setLayout(new GridLayout(2, 1));
		
		widthField = new LabeledField("width", 1024);
		heightField = new LabeledField("height", 1024);
		
		sizePanel.add(widthField);
		sizePanel.add(heightField);
		
		sizePanel.setPreferredSize(new Dimension(150, sizePanel.getPreferredSize().height));
		
		layout.putConstraint(NORTH, sizePanel, 5, SOUTH, dirPanel);
		layout.putConstraint(WEST, sizePanel, 5, WEST, this);
		add(sizePanel);
		// !size panel
		
		// misc panel
		JPanel miscPanel = new JPanel();
		miscPanel.setBorder(BorderFactory.createTitledBorder("Misc."));
		miscPanel.setLayout(new GridLayout(2, 1));
		
		fovField = new LabeledField("field of view", 90f);
		depthField = new LabeledField("max. reflections", 4);
		
		miscPanel.add(fovField);
		miscPanel.add(depthField);
		
		layout.putConstraint(NORTH, miscPanel, 5, SOUTH, sizePanel);
		layout.putConstraint(WEST, miscPanel, 5, WEST, this);
		
		add(miscPanel);
		// !misc panel
		
		
	}
	
	/**
	 * Reads all the fields and constructs a camera from the values.
	 * 
	 * @return a Camera with the values specified in the fields
	 * @throws NumberFormatException if one of the fields contains something that doesn't fit.
	 */
	public Camera getCamera() throws NumberFormatException {
		try {
			Vec3 dir = dirPanel.getVec();
			
			return new Camera(
					posPanel.getVec(),
					dir.z * PI/180f, dir.y * PI/180f, dir.x * PI/180f,
					widthField.getInt(),
					heightField.getInt(), 
					fovField.getFloat() * PI/180f,
					depthField.getInt());
			
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(
					GUI.getFrame(),
					"Please enter exclusicely numbers. \"width\", \"height\" and \"Max. Reflections\" also needs to be integers",
					"Number format error",
					JOptionPane.ERROR_MESSAGE);
			throw e;
		}
	}

}
