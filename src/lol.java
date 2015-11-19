import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class lol extends JPanel {
	private JTextField textField;
	private final JPanel szoliinput = new JPanel();
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public lol() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel input = new JPanel();
		add(input);
		input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));
		
		JPanel termekinput = new JPanel();
		input.add(termekinput);
		termekinput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		termekinput.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		textField_1 = new JTextField();
		textField_1.setColumns(3);
		panel.add(textField_1);
		
		JButton button = new JButton("New button");
		panel.add(button);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		termekinput.add(rdbtnNewRadioButton_1);
		input.add(szoliinput);
		szoliinput.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField = new JTextField();
		szoliinput.add(textField);
		textField.setColumns(3);
		
		JButton btnNewButton = new JButton("New button");
		szoliinput.add(btnNewButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		szoliinput.add(rdbtnNewRadioButton);
		
		JComboBox comboBox = new JComboBox();
		szoliinput.add(comboBox);

	}
}
