package gui;

import java.awt.*;
import javax.swing.*;

public class ULTIMATE_USER_INTERFACE extends JFrame{
	public ULTIMATE_USER_INTERFACE(){
		super("Productivity Monitor " + MANDARIN.VERSION);
		setName("Mandarin Szalon");
		getContentPane().setBackground(MANDARIN.ORANGE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		setBackground(MANDARIN.ORANGE);
		createMenuBar();
		createSzoliInputPanel();
		createTables();
	}
	
	public void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Main");
		JMenu fileMenu = new JMenu("File");
		JMenuItem startTheDay = new JMenuItem("Start the day");
		mainMenu.add(startTheDay);
		menuBar.add(mainMenu);
		menuBar.add(fileMenu);
		
		add(menuBar, BorderLayout.NORTH);
	}
	
	public void createSzoliInputPanel(){
		boolean feminist=true;
		JComboBox<Integer> gepek = new JComboBox<>(new Integer[]{1,2,3,4,5});
		ButtonGroup f_n = new ButtonGroup();
		JRadioButtonMenuItem f = new JRadioButtonMenuItem("Férfi", !feminist);
		JRadioButtonMenuItem n = new JRadioButtonMenuItem("Nő", feminist);
		f_n.add(f);
		f_n.add(n);
		JCheckBox berlet = new JCheckBox("Bérlet");
		JTextField perc = new JTextField(MANDARIN.MINUTECOLUMN);
		JLabel fizetendo = new JLabel("0");
		JTextField fizetett = new JTextField(MANDARIN.PAYMENTCOLUMN);
		Component[] tomb = {f, n, gepek, perc, berlet, fizetendo, fizetett};
		
		JPanel inp = new JPanel();
		inp.setMaximumSize(new Dimension(10,20));
		
		for(Component i:tomb)
			inp.add(i);
		
		inp.setBackground(MANDARIN.LIGHTGREEN);
		
		add(inp,BorderLayout.WEST);
	}
	
	public void createTables(){
		JTable berlet = new JTable();
		berlet.setFillsViewportHeight(true);
		JScrollPane b = new JScrollPane();
		b.add(berlet);
		
		JTable caffe = new JTable();
		caffe.setFillsViewportHeight(true);
		JScrollPane c = new JScrollPane();
		c.add(berlet);
		
		JTable krem = new JTable();
		krem.setFillsViewportHeight(true);
		JScrollPane k = new JScrollPane();
		k.add(krem);
		
		JTable udito = new JTable();
		udito.setFillsViewportHeight(true);
		JScrollPane u = new JScrollPane();
		u.add(udito);
		
		
		JPanel tablePanel = new JPanel();
		Component[] tomb = {b,c,k,u};
		for(Component i:tomb)
			tablePanel.add(i);
		
		add(tablePanel, BorderLayout.CENTER);
	}
	
	public void createandShowGui(){
		this.show();
	}

}
