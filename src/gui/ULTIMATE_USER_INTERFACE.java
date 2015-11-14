package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ULTIMATE_USER_INTERFACE extends JFrame{
	public ULTIMATE_USER_INTERFACE(){
		super("Productivity Monitor " + MANDARIN.VERSION);
		getContentPane().setBackground(MANDARIN.ORANGE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(360, 420));
		getContentPane().add(new JLabel("Hi m8"));
		InputData inp = new InputData();
		getContentPane().add(inp,BorderLayout.WEST);
		
	}
	public void createandShowGui(){
		this.show();
	}

}
