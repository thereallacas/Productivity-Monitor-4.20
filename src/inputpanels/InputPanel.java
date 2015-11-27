/*
 * 
 */
package inputpanels;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.apache.poi.sl.draw.geom.Guide;


/**
 * The Interface InputPanel. Used to make things easier at the {@link gui.ULTIMATE_USER_INTERFACE}
 * @see gui.ULTIMATE_USER_INTERFACE#addFunctiontoPanelButton(InputPanel, java.awt.event.ActionListener, java.awt.event.ActionListener)
 */
public interface InputPanel {
	
	/**
	 * Gets the felvesz button.
	 *
	 * @return the felvesz button
	 */
	public JButton getfelveszButton();
	
	/**
	 * Gets the torol button.
	 *
	 * @return the torol button
	 */
	public JButton gettorolButton();
	
	/**
	 * Gets the {@link JComboBox}
	 * @return the JComboBox
	 */
	public JComboBox<String> getJComboBox();
}