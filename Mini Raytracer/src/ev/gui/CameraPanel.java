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
	
	Vec3Panel posPanel, dirPanel;
	
	public CameraPanel() {
		
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		posPanel = new Vec3Panel();
		posPanel.setBorder(BorderFactory.createTitledBorder("Position"));
		posPanel.setPreferredSize(new Dimension(140, 120));
		
		layout.putConstraint(NORTH, posPanel, 5, NORTH, this);
		layout.putConstraint(WEST, posPanel, 5, WEST, this);
		add(posPanel);
		
		
		dirPanel = new Vec3Panel("roll", "pitch", "yaw", 0, 0, 0);
		dirPanel.setBorder(BorderFactory.createTitledBorder("Direction"));
		dirPanel.setPreferredSize(new Dimension(140, 120));
		
		layout.putConstraint(NORTH, dirPanel, 5, SOUTH, posPanel);
		layout.putConstraint(WEST, dirPanel, 5, WEST, this);
		add(dirPanel);
		
		
		
		
	}
	
	public Camera getCamera() {
		
		
		return null;
		
	}
	
}


