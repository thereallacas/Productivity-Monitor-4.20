/*
 * 
 */
package inputpanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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


/**
 * Provides gui for input data to the database solarium service.
 */
public class SzoliInputPanel extends JPanel implements InputPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** If its true, the {@link #n} will be set true as default*/
	private boolean feminist=true;
	
	/** The gepek. */
	private JComboBox<String> gepek = new JComboBox<>(new String[]{"1","2","3","4","5"});
	
	/** The f_n. */
	private ButtonGroup f_n = new ButtonGroup();
	
	/** The f. */
	private JRadioButton f = new JRadioButton("Férfi", !feminist);
	
	/** The n. */
	private JRadioButton n = new JRadioButton("Nő", feminist);
	
	/** The berlet. */
	private JCheckBox berlet = new JCheckBox("Bérlet");
	
	/** The perc. */
	private JTextField perc = new JTextField("0",MANDARIN.MINUTECOLUMN);
	
	/** The fizet. */
	private JCheckBox fizet=new JCheckBox("Fizet", true);
	
	/** The fizetett. */
	private JTextField fizetett = new JTextField((getfizetendo()),MANDARIN.PAYMENTCOLUMN);
	
	/** The felvesz. */
	private JButton felvesz = new JButton("Felvesz");
	
	/** The torol. */
	private JButton torol = new JButton("Töröl");
	
	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#getfelveszButton()
	 */
	@Override
	public JButton getfelveszButton(){
		return felvesz;
	}
	
	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#gettorolButton()
	 */
	@Override
	public JButton gettorolButton(){
		return torol;
	}
	
	
	/**
	 * Gets the termek.
	 *
	 * @return the termek
	 */
	//
	public String gettermek(){
		return (String) gepek.getSelectedItem();
	}
	
	/**
	 * Gets the f_n.
	 *
	 * @return the f_n
	 */
	public String getf_n(){
		return f.isSelected() ? "Férfi" : "Nő";
	}
	
	/**
	 * Checks if is berlet.
	 *
	 * @return true, if is berlet
	 */
	public boolean isberlet(){
		return berlet.isSelected();
	}
	
	/**
	 * Gets the perc.
	 * If it got a non numeric value, it gives an error message.
	 *
	 * @return the perc
	 */
	public int getperc(){
		if (perc.getText().isEmpty())
			return 0;
		try{
			Integer.parseInt((perc.getText()));
			}
			catch (NumberFormatException e) { 
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Számot üssél be", "Hé!", JOptionPane.ERROR_MESSAGE);
			    perc.setText("0");
			    e.printStackTrace();
			}
		return Integer.parseInt(perc.getText());
	}
	
	/**
	 * Gets the price to be paid.
	 *
	 * @return the fizetendo
	 */
	public String getfizetendo(){
		if (!fizet.isSelected())
			return "0";
		return (isberlet()) ? "0" : String.valueOf((getperc()*MANDARIN.UNIT));
	}
	
	/**
	 * Gets the paid price. If its not a numeric value, it shows an error message.
	 *
	 * @return the fizetett
	 */
	public String getfizetett(){
		try{
			Integer.parseInt((fizetett.getText()));
			}
			catch (NumberFormatException e) { 
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Számot üssél be", "Hé", JOptionPane.ERROR_MESSAGE);
			    fizetett.setText("0");
			    e.printStackTrace();
			}
		return fizetett.getText();
	}
	
	/**
	 * Instantiates a new szoli input panel, and add the various listeners to the gui elements
	 */
	public SzoliInputPanel() {
		super();
		f_n.add(f);
		f_n.add(n);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.InputPanelColor);
		Component[] tomb = { gepek, f, n, perc, berlet, fizetett,fizet, felvesz, torol};
		for(Component i:tomb){
				add(i);
				i.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
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
		gepek.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				fizet.setSelected(true);
				
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

	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#getJComboBox()
	 */
	public JComboBox<String> getJComboBox() {
		return gepek;
	}
}
