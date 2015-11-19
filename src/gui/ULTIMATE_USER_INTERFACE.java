package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import database.Serializer;
import tablemodels.SzoliTablaModell;
import tablemodels.TermekTablaModell;

public class ULTIMATE_USER_INTERFACE extends JFrame {

	private TermekTablaModell berletmodell;
	private TermekTablaModell kavemodell;
	private TermekTablaModell uditomodell;
	private TermekTablaModell kremmodell;
	private SzoliTablaModell szolitablamodell;

	JTabbedPane pane = new JTabbedPane();

	private SzoliInputPanel szoliInputPanel;
	private TermekInputPanel kaveInputPanel;
	private TermekInputPanel berletInputPanel;
	private TermekInputPanel kremInputPanel;
	private TermekInputPanel uditoInputPanel;

	private JTable berlet, krem, kave, udito, szoli;
	private GregorianCalendar gcalendar = new GregorianCalendar();

	public ULTIMATE_USER_INTERFACE() throws IOException{
		super("Productivity Monitor " + MANDARIN.VERSION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(MANDARIN.APPSIZE);
		setBackground(MANDARIN.ORANGE);

		berletmodell = new TermekTablaModell("berletarlista.txt");
		kavemodell = new TermekTablaModell("kavearlista.txt");
		uditomodell = new TermekTablaModell("uditoarlista.txt");
		kremmodell = new TermekTablaModell("kremarlista.txt");
		szolitablamodell = new SzoliTablaModell();

		szoliInputPanel = new SzoliInputPanel();
		kaveInputPanel = new TermekInputPanel(kavemodell.getArlista());
		berletInputPanel = new TermekInputPanel(berletmodell.getArlista());
		uditoInputPanel = new TermekInputPanel(uditomodell.getArlista());
		kremInputPanel = new TermekInputPanel(kremmodell.getArlista());
		
		add(makeMenuBar());
		add(pane, BorderLayout.CENTER);
		pane.add("Szolárium", makeTab(szoliInputPanel, szoli, szolitablamodell));
		pane.add("Kávé", makeTab(kaveInputPanel, kave, kavemodell));
		pane.add("Bérlet", makeTab(kremInputPanel, krem, berletmodell));
		pane.add("Krém", makeTab(kremInputPanel, krem, kremmodell));
		pane.add("Üdítő", makeTab(uditoInputPanel, udito, uditomodell));
		addFunctiontoPanel(szoliInputPanel, new szoliFelveszActionListener());
	}

	public void addFunctiontoPanel(JPanel inputpanel, ActionListener e){
		szoliInputPanel.addListener(e);
	}

	protected JComponent makeTab(JPanel inputpanel, JTable table, AbstractTableModel tablemodell) {
		JPanel panel = new JPanel(true);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(MANDARIN.ORANGE);
		table = new JTable(tablemodell);
		table.setFillsViewportHeight(true);
		JScrollPane s = new JScrollPane(table); 
		panel.add(inputpanel);
		panel.add(s);
		return panel;
	}

	public JComponent makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Main");
		JMenu fileMenu = new JMenu("File");
		JMenuItem startTheDay = new JMenuItem("Start the day");
		startTheDay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Serializer serializer = new Serializer();
				serializer.write(szolitablamodell.szolirekordok);
			}

		});
		JMenuItem finishTheDay = new JMenuItem("Finish the day");
		finishTheDay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Serializer serializer = new Serializer();
				serializer.write(szolitablamodell.szolirekordok);
			}

		});
		mainMenu.add(startTheDay);
		mainMenu.add(finishTheDay);

		menuBar.add(mainMenu);
		menuBar.add(fileMenu);

		return menuBar;
	}

	public class szoliFelveszActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String am = (gcalendar.get(Calendar.AM_PM)==0) ? "AM" : "PM";
			String timeString = String.valueOf(gcalendar.get(Calendar.HOUR)+":"
					+gcalendar.get(Calendar.MINUTE) +" "+ am);
			szolitablamodell.addSzoliRekord(
					timeString,
					szoliInputPanel.getgep(), 
					szoliInputPanel.getf_n(),
					szoliInputPanel.getperc(),
					szoliInputPanel.isberlet(),
					szoliInputPanel.getfizetendo(),
					szoliInputPanel.getfizetett());
		}
	}

	public class kaveFelveszActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String am = (gcalendar.get(Calendar.AM_PM)==0) ? "AM" : "PM";
			String timeString = String.valueOf(gcalendar.get(Calendar.HOUR)+":"
					+gcalendar.get(Calendar.MINUTE) +" "+ am);
			kavemodell.addTermekRekord(timeString,
					kaveInputPanel.getkave(),
					kaveInputPanel.getfizetendo(),
					kaveInputPanel.getfizetett());
		}

	}

	public void createandShowGui(){
		this.show();
	}

}
