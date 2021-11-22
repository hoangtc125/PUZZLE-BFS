import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class Item {
	private JButton button = new JButton();
	private String value;
	private String pos;
	
	public Item() {
		super();
	}

	public Item(String value, String pos) {
		super();
		this.value = value;
		this.pos = pos;
		this.button.setText(value);
		this.button.setFont(new Font("Tahoma", Font.BOLD, 30));
		this.button.setBackground(Color.LIGHT_GRAY);
	}
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
}
