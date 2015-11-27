package inputpanels;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.MANDARIN;
import datatypes.Termek;

public class TermekInputPanel extends JPanel implements InputPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> arlista=new HashMap<>();
	private JComboBox<String> termekComboBox = new JComboBox<>();
	private JTextField fizetett;
	private JButton felvesz = new JButton("Felvesz");
	private JButton torol = new JButton("Töröl");

	public void addListener(ActionListener e){
		felvesz.addActionListener(e);
	}

	public String gettermek(){
		return (String) termekComboBox.getSelectedItem();
	}

	public String getfizetendo(){
		return arlista.get((String)(termekComboBox.getSelectedItem()));
	}

	public String getfizetett(){
		return fizetett.getText();
	}

	public String fizetettDefault(){
		return (getfizetendo());
	}

	public TermekInputPanel(List<Termek> arlista) {
		super();

		for(Termek i:arlista){
			termekComboBox.addItem(i.termeknev);
			this.arlista.put(i.termeknev, i.egysegar);
		}
		fizetett = new JTextField(fizetettDefault(),MANDARIN.PAYMENTCOLUMN);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(MANDARIN.InputPanelColor);
		Component[] tomb = {termekComboBox, fizetett, felvesz, torol};
		setMaximumSize(new Dimension(MANDARIN.APPSIZE.height, MANDARIN.APPSIZE.width));
		termekComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				fizetett.setText(fizetettDefault());

			}
		});
		for(Component i:tomb)
			add(i);
	}

	@Override
	public JButton getfelveszButton() {
		return felvesz;
	}

	@Override
	public JComboBox<String> getJComboBox() {
		return termekComboBox;
	}

	@Override
	public JButton gettorolButton() {
		return torol;
	}



}