package ev.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FloatPanel extends JPanel {
	
	JTextField field;
	
	public FloatPanel(String name, float value) {
		
		((FlowLayout)getLayout()).setAlignment(FlowLayout.RIGHT);
		
		JLabel label = new JLabel(name + ":", SwingConstants.RIGHT);
		add(label);
		
		field = new JTextField(value + "");
		field.setPreferredSize(new Dimension(80, 20));
		
		add(field);
	}
	
	
	public float getValue() throws NumberFormatException {
		return Float.parseFloat(field.getText());		
	}
	
}
