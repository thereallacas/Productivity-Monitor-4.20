package gui;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class SzoliInputData extends JPanel{
	//Integer[] gepek = new Integer[]{1,2,3,4,5};
	public boolean feminist;
	public JComboBox<Integer> gepek = new JComboBox<>(new Integer[]{1,2,3,4,5});
	
	public JRadioButtonMenuItem f = new JRadioButtonMenuItem("Férfi", feminist);
	public JRadioButtonMenuItem n = new JRadioButtonMenuItem("Nő", !feminist);
	public ButtonGroup f_n = new ButtonGroup();
	
	
	
	
	public SzoliInputData() {
		super();
		f_n.add(f);
		f_n.add(n);
		add(f);
		add(n);
		add(gepek);
		setBackground(MANDARIN.LIGHTGREEN);
		
		
	}
}
