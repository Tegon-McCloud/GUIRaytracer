package ev.gui;

import static ev.math.MathUtil.*;
import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ev.math.Vec3;

@SuppressWarnings("serial")
public class Vec3Panel extends JPanel {
	
	private LabeledField fx, fy, fz;
	
	public Vec3Panel(String xName, String yName, String zName, float x, float y, float z) {
		
		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);
		
		fx = new LabeledField(xName, x);
		fy = new LabeledField(yName, y);
		fz = new LabeledField(zName, z);
		
		add(fx);
		add(fy);
		add(fz);
		
	}
	
	public Vec3Panel(String xName, String yName, String zName) {
		this(xName, yName, zName, 0, 0, 0);
	}
	
	public Vec3Panel(float x, float y, float z) {
		this("x", "y", "z", x, y, z);
	}
	
	public Vec3Panel() {
		this("x", "y", "z", 0, 0, 0);
	}
		
	public Vec3 getVec() throws NumberFormatException {
		return new Vec3(fx.getFloat(), fy.getFloat(), fz.getFloat());
	}
	
}

