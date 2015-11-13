package tables;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.TermekTablaModell;

public class Berlet extends JTable {
	private static TermekTablaModell berletmodell;
	public Berlet(){
		super(berletmodell);
		this.setFillsViewportHeight(true);
	}
	
}
