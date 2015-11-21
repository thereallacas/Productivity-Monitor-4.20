package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import database.Exportxssl;
import database.Serializer;
import tablemodels.SzoliTablaModell;
import tablemodels.TermekTablaModell;

public class ULTIMATE_USER_INTERFACE extends JFrame {

	private TermekTablaModell berletmodell;
	private TermekTablaModell kavemodell;
	private TermekTablaModell uditomodell;
	private TermekTablaModell kremmodell;
	private SzoliTablaModell szolitablamodell;

	private JTabbedPane pane = new JTabbedPane();

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
		
		add(makeMenuBar(),BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		pane.add("Szolárium", makeTab(szoliInputPanel, szoli, szolitablamodell));
		pane.add("Kávé", makeTab(kaveInputPanel, kave, kavemodell));
		pane.add("Bérlet", makeTab(berletInputPanel, berlet, berletmodell));
		pane.add("Krém", makeTab(kremInputPanel, krem, kremmodell));
		pane.add("Üdítő", makeTab(uditoInputPanel, udito, uditomodell));
		addFunctiontoPanelButton(szoliInputPanel, new szoliFelveszActionListener());
		addFunctiontoPanelButton(berletInputPanel, new termekFelveszActionListener(berletmodell,berletInputPanel));
		addFunctiontoPanelButton(kaveInputPanel, new termekFelveszActionListener(kavemodell,kaveInputPanel));
		addFunctiontoPanelButton(kremInputPanel, new termekFelveszActionListener(kremmodell, kremInputPanel));
		addFunctiontoPanelButton(uditoInputPanel, new termekFelveszActionListener(uditomodell, uditoInputPanel));	
	}

	public void addFunctiontoPanelButton(InputPanel inputpanel, ActionListener e){
		inputpanel.getButton().addActionListener(e);
	}

	protected JComponent makeTab(JPanel inputpanel, JTable table, AbstractTableModel tablemodell) {
		JPanel panel = new JPanel(true);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(MANDARIN.DARKGREEN);
		table = new JTable(tablemodell);
		table.setBackground(MANDARIN.ORANGE);
		table.setFillsViewportHeight(true);
		JScrollPane s = new JScrollPane(table); 
		panel.add(inputpanel);
		panel.add(s);
		return panel;
	}

	public JComponent makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu open = new JMenu("Open");
		JMenuItem startTheDay = new JMenuItem("Save");
		startTheDay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Serializer serializer = new Serializer();
				try {
					serializer.writeszolirekordok(szolitablamodell.getSzoliRekordok(), "szoli");
				
				serializer.writetermekrekordok(kavemodell.getTermekRekordok(), "kávé");
				serializer.writetermekrekordok(berletmodell.getTermekRekordok(), "bérlet");
				serializer.writetermekrekordok(uditomodell.getTermekRekordok(), "udito");
				serializer.writetermekrekordok(kremmodell.getTermekRekordok(), "krem");
				} catch (IOException e1) {
					System.out.println("Hiba a mentés során");
					e1.printStackTrace();
					
				}
				
				}
		});
		JMenuItem finishTheDay = new JMenuItem("Export to Excel");
		finishTheDay.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Exportxssl exporter = new Exportxssl();
			}
		});
		fileMenu.add(startTheDay);
		fileMenu.add(finishTheDay);
		menuBar.add(fileMenu);
		return menuBar;
	}

	public Object[] getData(){
		 Object[] objs = {szolitablamodell, kavemodell, berletmodell, uditomodell, kremmodell};
		 return objs;
	}
	
	public class szoliFelveszActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String am = (gcalendar.get(Calendar.AM_PM)==0) ? "AM" : "PM";
			String timeString = String.valueOf(gcalendar.get(Calendar.HOUR)+":"
					+gcalendar.get(Calendar.MINUTE) +" "+ am);
			szolitablamodell.addTermekRekord(
					timeString,
					szoliInputPanel.gettermek(), 
					szoliInputPanel.getf_n(),
					szoliInputPanel.getperc(),
					szoliInputPanel.isberlet(),
					szoliInputPanel.getfizetendo(),
					szoliInputPanel.getfizetett());
		}
	}

	public class termekFelveszActionListener implements ActionListener{
		private InputPanel panel;
		private TermekTablaModell model;
		public termekFelveszActionListener(TermekTablaModell model, TermekInputPanel p) {
			panel = p;
			this.model = model;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String am = (gcalendar.get(Calendar.AM_PM)==0) ? "AM" : "PM";
			String timeString = String.valueOf(gcalendar.get(Calendar.HOUR)+":"
					+gcalendar.get(Calendar.MINUTE) +" "+ am);
			model.addTermekRekord(timeString,
					panel.gettermek(),
					panel.getfizetendo(),
					panel.getfizetett());
		}
	}

	public void createandShowGui(){
		this.show();
	}

}
