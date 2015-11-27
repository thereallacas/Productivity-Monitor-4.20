package inputpanels;

import javax.swing.JButton;
import javax.swing.JComboBox;

public interface InputPanel {
	public JButton getfelveszButton();
	public JButton gettorolButton();
	public JComboBox<String> getJComboBox();
}