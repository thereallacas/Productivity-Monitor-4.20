/*
 * 
 */
package inputpanels;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.MANDARIN;
import datatypes.Termek;

/**
 * Provides gui for input data to the database product purchases.
 * It also has a price list to get the available products. It reads them from a .txt file and stores 
 * them in a {@link HashMap}.
 */
public class TermekInputPanel extends JPanel implements InputPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The arlista. */
	private HashMap<String, String> arlista=new HashMap<>();
	
	/** The termek combo box. */
	private JComboBox<String> termekComboBox = new JComboBox<>();
	
	/** The fizetett. */
	private JTextField fizetett;
	
	/** The felvesz. */
	private JButton felvesz = new JButton("Felvesz");
	
	/** The torol. */
	private JButton torol = new JButton("Töröl");
	
	/**The fizet */
	private JCheckBox fizet=new JCheckBox("Fizet", true);

	/**
	 * Gets the product.
	 *
	 * @return the termek
	 */
	public String gettermek(){
		return (String) termekComboBox.getSelectedItem();
	}

	/**
	 * Gets the price to be paid.
	 *
	 * @return the fizetendo
	 */
	public String getfizetendo(){
		if (!fizet.isSelected())
			return "0";
		return arlista.get((String)(termekComboBox.getSelectedItem()));
	}

	/**
	 * Gets the fizetett.
	 * Checks if the value is numeric. The method is from stackoverflow, and yes, I don´t know
	 * why I didn´t use integer values at the first place.
	 * @return the fizetett
	 */
	public String getfizetett(){
		try{
			Integer.parseInt((fizetett.getText()));
			}
			catch (NumberFormatException e) { 
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Számot üssél be", "Hé!", JOptionPane.ERROR_MESSAGE);
			    fizetett.setText("0");
			    e.printStackTrace();
			}
		return fizetett.getText();
	}

	/**
	 * Fizetett default.
	 *
	 * @return the string
	 */
	public String fizetettDefault(){
		return (getfizetendo());
	}

	/**
	 * Instantiates a new termek input panel.
	 * Also, it adds a listener to the JCombobox, to make the {@link #fizetett} change the default price every time 
	 * the {@link #termekComboBox} value has changed.
	 *
	 * @param arlista the arlista
	 */
	public TermekInputPanel(List<Termek> arlista) {
		super();

		for(Termek i:arlista){
			termekComboBox.addItem(i.termeknev);
			this.arlista.put(i.termeknev, i.egysegar);
		}
		fizetett = new JTextField(fizetettDefault(),MANDARIN.PAYMENTCOLUMN);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.InputPanelColor);
		Component[] tomb = {termekComboBox, fizetett,fizet, felvesz, torol};
		//setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
		termekComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				fizetett.setText(fizetettDefault());

			}
		});
		for(Component i:tomb){
			add(i);
			i.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		}
	}

	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#getfelveszButton()
	 */
	@Override
	public JButton getfelveszButton() {
		return felvesz;
	}

	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#getJComboBox()
	 */
	@Override
	public JComboBox<String> getJComboBox() {
		return termekComboBox;
	}

	/* (non-Javadoc)
	 * @see inputpanels.InputPanel#gettorolButton()
	 */
	@Override
	public JButton gettorolButton() {
		return torol;
	}



}
