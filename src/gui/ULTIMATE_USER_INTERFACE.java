package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import database.Exportxssl;
import database.Serializer;
import datatypes.recordtypes.*;
import inputpanels.InputPanel;
import inputpanels.SzoliInputPanel;
import inputpanels.TermekInputPanel;
import tablemodels.SzoliTablaModell;
import tablemodels.TablaModell;
import tablemodels.TermekTablaModell;

public class ULTIMATE_USER_INTERFACE extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		addFunctiontoPanelButton(szoliInputPanel, new szoliFelveszActionListener(), new DeleteActionListener(szolitablamodell));
		addFunctiontoPanelButton(berletInputPanel, new termekFelveszActionListener(berletmodell,berletInputPanel), new DeleteActionListener(berletmodell));
		addFunctiontoPanelButton(kaveInputPanel, new termekFelveszActionListener(kavemodell,kaveInputPanel), new DeleteActionListener(kavemodell));
		addFunctiontoPanelButton(kremInputPanel, new termekFelveszActionListener(kremmodell, kremInputPanel), new DeleteActionListener(kremmodell));
		addFunctiontoPanelButton(uditoInputPanel, new termekFelveszActionListener(uditomodell, uditoInputPanel), new DeleteActionListener(uditomodell));
	}

	public void addFunctiontoPanelButton(InputPanel inputpanel,ActionListener felvesz, ActionListener torol){
		inputpanel.getfelveszButton().addActionListener(felvesz);
		inputpanel.gettorolButton().addActionListener(torol);
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
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("What");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int retval = fc.showOpenDialog(open);
				if (retval==JFileChooser.APPROVE_OPTION){
					Serializer serializer = new Serializer();
					File file = fc.getSelectedFile();
					try {
						List<List<? extends Record>> openedrecords = serializer.read(file);
						szolitablamodell.setSzoliRekordok(openedrecords.get(0));
						kavemodell.setTermekRekordok(openedrecords.get(1));
						berletmodell.setTermekRekordok(openedrecords.get(2));
						uditomodell.setTermekRekordok(openedrecords.get(3));
						kremmodell.setTermekRekordok(openedrecords.get(4));
					}catch (IOException | ClassNotFoundException e1) {
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Hiba a megnyitás során!", 
								"Hiba", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener(){
			private String filename;
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Where");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retval = fc.showSaveDialog(save);
				if (retval==JFileChooser.APPROVE_OPTION){
					Serializer serializer = new Serializer();
					File place = fc.getCurrentDirectory();
						filename=fc.getSelectedFile().getName();
					try {
						List<List<? extends Record>> sv = new ArrayList<List<? extends Record>>();
						sv.add(szolitablamodell.getSzoliRekordok());
						sv.add(kavemodell.getTermekRekordok());
						sv.add(berletmodell.getTermekRekordok());
						sv.add(uditomodell.getTermekRekordok());
						sv.add(kremmodell.getTermekRekordok());
						serializer.write(sv, place, filename);
					}catch (IOException e1) {
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Hiba a mentés során", 
								"Hiba", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		JMenuItem exportToExcel = new JMenuItem("Export to Excel");
		exportToExcel.addActionListener(new ActionListener(){
			private String filename;
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Where");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retval = fc.showSaveDialog(save);
				if (retval==JFileChooser.APPROVE_OPTION){
					Exportxssl exporter = new Exportxssl();
					File place = fc.getCurrentDirectory();
					filename=fc.getSelectedFile().getName();
						List<List<? extends Record>> sv = new ArrayList<List<? extends Record>>();
						sv.add(szolitablamodell.getSzoliRekordok());
						sv.add(kavemodell.getTermekRekordok());
						sv.add(berletmodell.getTermekRekordok());
						sv.add(uditomodell.getTermekRekordok());
						sv.add(kremmodell.getTermekRekordok());
						exporter.write(sv, place,filename );
				}
			}
		});
		fileMenu.add(save);
		fileMenu.add(exportToExcel);
		fileMenu.add(open);
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
			szolitablamodell.addSzoliRekord(
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
		private TermekInputPanel panel;
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

	public class DeleteActionListener implements ActionListener{
		private TablaModell model;
		public DeleteActionListener(TablaModell model) {
			this.model=model;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			model.deleteRekord();
		}
	}

	@SuppressWarnings("deprecation")
	public void createandShowGui(){
		this.show();
	}

}
