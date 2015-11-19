package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

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

import tablemodels.TermekTablaModell;

public class TermekInputPanel extends JPanel{
	//Integer[] gepek = new Integer[]{1,2,3,4,5};
	
	private boolean feminist=true;
	private HashMap<String, Integer> arlista=new HashMap<>();
	private JComboBox<String> kaveComboBox = new JComboBox<>();
	private JTextField fizetendo = new JTextField("0", MANDARIN.PAYMENTCOLUMN);
	private JTextField fizetett;
	private JButton felvesz = new JButton("Felvesz");
	
	public void addListener(ActionListener e){
		felvesz.addActionListener(e);
	}
	
	String getkave(){
		return (String) kaveComboBox.getSelectedItem();
	}
	
	int getfizetendo(){
		return arlista.get((String)(kaveComboBox.getSelectedItem()));
	}
	
	int getfizetett(){
		return new Integer(fizetett.getText());
	}

	public TermekInputPanel(List<Termek> arlista) {
		super();
		for(Termek i:arlista){
			kaveComboBox.addItem(i.termeknev);
			this.arlista.put(i.termeknev, i.egysegar);
		}
		fizetett = new JTextField(Integer.toString(getfizetendo()),MANDARIN.PAYMENTCOLUMN);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.ORANGE);
		Component[] tomb = {kaveComboBox, fizetett, felvesz};
		setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
		for(Component i:tomb)
			add(i);
		setBackground(MANDARIN.ORANGE);
	}
}
