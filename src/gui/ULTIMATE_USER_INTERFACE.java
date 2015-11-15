package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ULTIMATE_USER_INTERFACE extends JFrame{
	public ULTIMATE_USER_INTERFACE(){
		super("Productivity Monitor " + MANDARIN.VERSION);
		setName("Mandarin Szalon");
		getContentPane().setBackground(MANDARIN.ORANGE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(420, 360));
		getContentPane().add(new JLabel("Hi m8"));
		SzoliInputData inp = new SzoliInputData();
		getContentPane().add(inp,BorderLayout.WEST);
		getContentPane().add(createMenuBar(), BorderLayout.NORTH);
	}
	
	public JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Main");
		JMenu fileMenu = new JMenu("File");
		JMenuItem startTheDay = new JMenuItem("Start the day");
		mainMenu.add(startTheDay);
		menuBar.add(mainMenu);
		menuBar.add(fileMenu);
		
		return menuBar;
	}
	
	public void createandShowGui(){
		this.show();
	}

}
