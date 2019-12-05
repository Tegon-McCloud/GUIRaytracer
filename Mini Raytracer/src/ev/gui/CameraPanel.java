package ev.gui;

import static ev.math.MathUtil.PI;
import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.VERTICAL_CENTER;
import static javax.swing.SpringLayout.WEST;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.border.Border;

import ev.graphics.Camera;
import ev.math.Vec3;

@SuppressWarnings("serial")
public class CameraPanel extends JPanel {

	Vec3Panel posPanel, dirPanel;
	JTextField widthField, heightField, fovField, depthField;

	public CameraPanel() {

		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// position panel
		posPanel = new Vec3Panel();
		posPanel.setBorder(BorderFactory.createTitledBorder("Position"));
		posPanel.setPreferredSize(new Dimension(140, posPanel.getPreferredSize().height));

		layout.putConstraint(NORTH, posPanel, 5, NORTH, this);
		layout.putConstraint(WEST, posPanel, 5, WEST, this);
		add(posPanel);
		// !position panel
		
		// direction panel
		dirPanel = new Vec3Panel("roll", "pitch", "yaw", 0, 0, 0);
		dirPanel.setBorder(BorderFactory.createTitledBorder("Direction"));
		dirPanel.setPreferredSize(new Dimension(140, dirPanel.getPreferredSize().height));

		layout.putConstraint(NORTH, dirPanel, 5, SOUTH, posPanel);
		layout.putConstraint(WEST, dirPanel, 5, WEST, this);
		add(dirPanel);
		// !direction panel
		
		// size panel
		JPanel sizePanel = new JPanel();
		sizePanel.setBorder(BorderFactory.createTitledBorder("Resolution"));
		sizePanel.setLayout(new GridLayout(2, 1));

		JPanel widthPanel = new JPanel();
		JPanel heightPanel = new JPanel();

		((FlowLayout)widthPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		((FlowLayout)heightPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		
		widthPanel.add(new JLabel("width:"));
		heightPanel.add(new JLabel("height"));

		widthField = new JTextField("1024");
		heightField = new JTextField("1024");

		widthField.setPreferredSize(new Dimension(80, 20));
		heightField.setPreferredSize(new Dimension(80, 20));
		
		widthPanel.add(widthField);
		heightPanel.add(heightField);
		
		sizePanel.add(widthPanel);
		sizePanel.add(heightPanel);
		
		sizePanel.setPreferredSize(new Dimension(140, sizePanel.getPreferredSize().height));
		
		layout.putConstraint(NORTH, sizePanel, 5, SOUTH, dirPanel);
		layout.putConstraint(WEST, sizePanel, 5, WEST, this);
		add(sizePanel);
		// !size panel
		
		// misc panel
		JPanel miscPanel = new JPanel();
		miscPanel.setBorder(BorderFactory.createTitledBorder("Misc."));
		miscPanel.setLayout(new GridLayout(2, 1));
		
		JPanel fovPanel = new JPanel();
		JPanel depthPanel = new JPanel();
		
		((FlowLayout)fovPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		((FlowLayout)depthPanel.getLayout()).setAlignment(FlowLayout.RIGHT);
		
		fovPanel.add(new JLabel("Field of View"));
		depthPanel.add(new JLabel("Max. Reflections"));
		
		fovField = new JTextField("90");
		depthField = new JTextField("4");
		
		fovField.setPreferredSize(new Dimension(80, 20));
		depthField.setPreferredSize(new Dimension(80, 20));
		
		fovPanel.add(fovField);
		depthPanel.add(depthField);
		
		miscPanel.add(fovPanel);
		miscPanel.add(depthPanel);
		
		layout.putConstraint(NORTH, miscPanel, 5, SOUTH, sizePanel);
		layout.putConstraint(WEST, miscPanel, 5, WEST, this);
		
		add(miscPanel);
		// !misc panel
		
		
	}
	
	public Camera getCamera() throws NumberFormatException {
		try {
			Vec3 dir = dirPanel.getVec();
			return new Camera(
					posPanel.getVec(),
					dir.z, dir.y, dir.x,
					Integer.parseInt(widthField.getText()),
					Integer.parseInt(heightField.getText()), 
					Float.parseFloat(fovField.getText()),
					Integer.parseInt(depthField.getText()));
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
