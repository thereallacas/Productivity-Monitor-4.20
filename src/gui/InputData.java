package gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class InputData extends JPanel{
	//Integer[] gepek = new Integer[]{1,2,3,4,5};
	public JComboBox<Integer> gepek = new JComboBox<>(new Integer[]{1,2,3,4,5});
	public InputData() {
		super();
		this.add(gepek);
		this.setBackground(MANDARIN.LIGHTGREEN);
		
	}
}
