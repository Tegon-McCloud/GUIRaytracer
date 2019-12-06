package ev.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LabeledField extends JPanel {
	
	private JTextField field;
	
	public LabeledField(String name, String text) {
		
		((FlowLayout)getLayout()).setAlignment(FlowLayout.RIGHT);
		
		add(new JLabel(name + ":", SwingConstants.RIGHT));
		
		field = new JTextField(text);
		field.setPreferredSize(new Dimension(80, 20));
		
		add(field);
	}
	
	public LabeledField(String name, float value) {
		this(name, Float.toString(value));
	}
	
	public LabeledField(String name, int value) {
		this(name, Integer.toString(value));
	}
	
	
	
	public float getFloat() throws NumberFormatException {
		return Float.parseFloat(field.getText());		
	}
	
	public int getInt() throws NumberFormatException {
		return Integer.parseInt(field.getText());
	}
	
	public String getString() {
		return field.getText();
	}
	
	
	public void setString(String s) {
		field.setText(s);
	}
}
