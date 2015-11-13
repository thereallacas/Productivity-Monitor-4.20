package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ULTIMATE_USER_INTERFACE extends JFrame{
	public ULTIMATE_USER_INTERFACE(){
		super("Productivity Monitor " + MAGIC.VERSION + 0);
		getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(360, 420));
		getContentPane().add(new JLabel("Get rekt"));
	}
	public void createandShowGui(){
		this.show();
	}

}
