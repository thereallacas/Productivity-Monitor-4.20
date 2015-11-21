package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SzoliInputPanel extends JPanel implements InputPanel{
	private boolean feminist=true;
	private JComboBox<String> gepek = new JComboBox<>(new String[]{"1","2","3","4","5"});
	private ButtonGroup f_n = new ButtonGroup();
	private JRadioButton f = new JRadioButton("Férfi", !feminist);
	private JRadioButton n = new JRadioButton("Nő", feminist);
	private JCheckBox berlet = new JCheckBox("Bérlet");
	private JTextField perc = new JTextField("0",MANDARIN.MINUTECOLUMN);
	private JTextField fizetendo = new JTextField("0", MANDARIN.PAYMENTCOLUMN);
	private JTextField fizetett = new JTextField((getfizetendo()),MANDARIN.PAYMENTCOLUMN);
	private JButton felvesz = new JButton("Felvesz");
	private char[] validvalues = {'1','2','3','4','5','6','7','8','9','0'};
	//InputPanel interfész 
	public JButton getButton(){
		return felvesz;
	}
	
	//
	public String gettermek(){
		return (String) gepek.getSelectedItem();
	}
	
	String getf_n(){
		return f.isSelected() ? "Férfi" : "Nő";
	}
	
	boolean isberlet(){
		return berlet.isSelected();
	}
	
	public int getperc(){
		if (perc.getText().isEmpty())
			return 0;
		return Integer.parseInt(perc.getText());
	}
	
	public String getfizetendo(){
		return (isberlet()) ? "0" : String.valueOf((getperc()*MANDARIN.UNIT));
	}
	
	public String getfizetett(){
		return fizetett.getText();
	}
	
	public SzoliInputPanel() {
		super();
		f_n.add(f);
		f_n.add(n);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.InputPanelColor);
		Component[] tomb = { gepek, f, n, perc, berlet, fizetett, felvesz};
		for(Component i:tomb){
				add(i);
		}
		perc.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				fizetett.setText(getfizetendo());
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				fizetett.setText(getfizetendo());
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				fizetett.setText(getfizetendo());
				
			}
		});
		setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
	}

	@Override
	public JComboBox<String> getJComboBox() {
		return gepek;
	}
}
