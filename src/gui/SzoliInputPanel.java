package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class SzoliInputPanel extends JPanel{
	//Integer[] gepek = new Integer[]{1,2,3,4,5};
	
	private boolean feminist=true;
	private JComboBox<Integer> gepek = new JComboBox<>(new Integer[]{1,2,3,4,5});
	private ButtonGroup f_n = new ButtonGroup();
	private JRadioButton f = new JRadioButton("Férfi", !feminist);
	private JRadioButton n = new JRadioButton("Nő", feminist);
	private JCheckBox berlet = new JCheckBox("Bérlet");
	private JTextField perc = new JTextField("0",MANDARIN.MINUTECOLUMN);
	private JTextField fizetendo = new JTextField("0", MANDARIN.PAYMENTCOLUMN);
	private JTextField fizetett = new JTextField(Integer.toString(getfizetendo()),MANDARIN.PAYMENTCOLUMN);
	private JButton felvesz = new JButton("Felvesz");
	
	public void addListener(ActionListener e){
		felvesz.addActionListener(e);
	}
	
	int getgep(){
		return (int)gepek.getSelectedItem();
	}
	
	String getf_n(){
		return f.isSelected() ? "Férfi" : "Nő";
	}
	
	boolean isberlet(){
		return berlet.isSelected();
	}
	
	int getperc(){
		if (perc.getText().isEmpty())
			return 0;
		return Integer.parseInt(perc.getText());//INTEGER DOKSI
	}
	
	int getfizetendo(){
		return (isberlet()) ? 0 : (getperc()*MANDARIN.UNIT);
	}
	
	int getfizetett(){
		if(fizetett.getText().isEmpty())
			return 0;
		return new Integer(fizetett.getText());
	}
	
	public SzoliInputPanel() {
		super();
		f_n.add(f);
		f_n.add(n);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.ORANGE);
		Component[] tomb = { gepek, f, n, perc, berlet, fizetett, felvesz};
		for(Component i:tomb){
				add(i);
		}
		setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
		setBackground(MANDARIN.ORANGE);
	
		
	}
}
