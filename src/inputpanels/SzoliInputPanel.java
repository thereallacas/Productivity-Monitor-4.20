package inputpanels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.MANDARIN;

public class SzoliInputPanel extends JPanel implements InputPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean feminist=true;
	private JComboBox<String> gepek = new JComboBox<>(new String[]{"1","2","3","4","5"});
	private ButtonGroup f_n = new ButtonGroup();
	private JRadioButton f = new JRadioButton("Férfi", !feminist);
	private JRadioButton n = new JRadioButton("Nő", feminist);
	private JCheckBox berlet = new JCheckBox("Bérlet");
	private JTextField perc = new JTextField("0",MANDARIN.MINUTECOLUMN);
	@SuppressWarnings("unused")
	private JTextField fizetendo = new JTextField("0", MANDARIN.PAYMENTCOLUMN);
	private JTextField fizetett = new JTextField((getfizetendo()),MANDARIN.PAYMENTCOLUMN);
	private JButton felvesz = new JButton("Felvesz");
	private JButton torol = new JButton("Töröl");
	
	@Override
	public JButton getfelveszButton(){
		return felvesz;
	}
	@Override
	public JButton gettorolButton(){
		return torol;
	}
	
	
	//
	public String gettermek(){
		return (String) gepek.getSelectedItem();
	}
	
	public String getf_n(){
		return f.isSelected() ? "Férfi" : "Nő";
	}
	
	public boolean isberlet(){
		return berlet.isSelected();
	}
	
	public int getperc(){
		if (perc.getText().isEmpty())
			return 0;
		try{
			Integer.parseInt((perc.getText()));
			}
			catch (NumberFormatException e) { 
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Számot üssél be", "Hiba", JOptionPane.ERROR_MESSAGE);
			    perc.setText("0");
			    e.printStackTrace();
			}
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
		Component[] tomb = { gepek, f, n, perc, berlet, fizetett, felvesz, torol};
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
		berlet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (berlet.isContentAreaFilled())
					fizetett.setText("0");
			}
		});
		//setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
	}

	@Override
	public JComboBox<String> getJComboBox() {
		return gepek;
	}
}
