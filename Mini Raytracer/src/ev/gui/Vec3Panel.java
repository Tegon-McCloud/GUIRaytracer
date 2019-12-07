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

/**
 * Vec3Panel is convenience swing component for getting a Vec3 as input from the user.
 */
@SuppressWarnings("serial")
public class Vec3Panel extends JPanel {
	
	private LabeledField fx, fy, fz;
	
	/**
	 * @param xName a String with the name to label the first field with
	 * @param yName a String with the name to label the second field with
	 * @param zName a String with the name to label the third field with
	 * @param x the initial value of the first field
	 * @param y the initial value of the second field
	 * @param z the initial value of the third field
	 */
	public Vec3Panel(String xName, String yName, String zName, float x, float y, float z) {
		
		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);
		
		add(fx = new LabeledField(xName, x));
		add(fy = new LabeledField(yName, y));
		add(fz = new LabeledField(zName, z));
		
	}
	
	/**
	 * Initialize all fields with value 0.0
	 * 
	 * @param xName a String with the name to label the first field with
	 * @param yName a String with the name to label the second field with
	 * @param zName a String with the name to label the third field with
	 */
	public Vec3Panel(String xName, String yName, String zName) {
		this(xName, yName, zName, 0, 0, 0);
	}
	
	/**
	 * Label fields with "x", "y" and "z".
	 * 
	 * @param x the initial value of the first field
	 * @param y the initial value of the second field
	 * @param z the initial value of the third field
	 */
	public Vec3Panel(float x, float y, float z) {
		this("x", "y", "z", x, y, z);
	}
	
	/**
	 * Label fields with "x", "y" and "z" and initialize all fields to 0.0
	 */
	public Vec3Panel() {
		this("x", "y", "z", 0, 0, 0);
	}
		
	/**
	 * Gets the Vec3 that is currently typed in.
	 * 
	 * @return a Vec3 with the coordinates that is currently in the fields
	 * @throws NumberFormatException if the user something else than a number
	 */
	public Vec3 getVec() throws NumberFormatException {
		return new Vec3(fx.getFloat(), fy.getFloat(), fz.getFloat());
	}
	
	/**
	 * Sets the numbers in the fields to the values of v.
	 * 
	 * @param v the Vec3 which coords should go into the fields
	 */
	public void setVec(Vec3 v) {
		fx.setString(v.x + "");
		fy.setString(v.y + "");
		fz.setString(v.z + "");
	}
	
	/**
	 * Empties every field completely
	 */
	public void empty() {
		fx.setString("");
		fy.setString("");
		fz.setString("");
	}
}

