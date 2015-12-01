/*
 * 
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import database.Exportxlsx;
import database.Serializer;
import datatypes.recordtypes.*;
import inputpanels.InputPanel;
import inputpanels.SzoliInputPanel;
import inputpanels.TermekInputPanel;
import tablemodels.SzoliTablaModell;
import tablemodels.TablaModell;
import tablemodels.TermekTablaModell;

/**
 * Creates the soul of the application: The Ultimate User Interface
 * Extends from JFrame, and provides the necessary functions to input 
 * data to the database. It has various attributes including {@link tablemodels.TablaModell}
 * , {@link inputpanels.InputPanel}, JTables, and much more!
 * It has functions to create itself and add the various elements to the Frame. 
 */
public class ULTIMATE_USER_INTERFACE extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The berletmodell. */
	private TermekTablaModell berletmodell;
	
	/** The kavemodell. */
	private TermekTablaModell kavemodell;
	
	/** The uditomodell. */
	private TermekTablaModell uditomodell;
	
	/** The kremmodell. */
	private TermekTablaModell kremmodell;
	
	/** The szolitablamodell. */
	private SzoliTablaModell szolitablamodell;
	
	/** The fordaszatmodell */
	private TermekTablaModell fodraszatmodell;

	/** The pane. */
	private JTabbedPane pane = new JTabbedPane();

	/** The szoli input panel. */
	private SzoliInputPanel szoliInputPanel;
	
	/** The kave input panel. */
	private TermekInputPanel kaveInputPanel;
	
	/** The berlet input panel. */
	private TermekInputPanel berletInputPanel;
	
	/** The krem input panel. */
	private TermekInputPanel kremInputPanel;
	
	/** The udito input panel. */
	private TermekInputPanel uditoInputPanel;
	
	/** The fodraszat input panel. */
	private TermekInputPanel fordaszatInputPanel;

	/** The table for the database */
	private JTable berlet, krem, kave, udito, szoli, fodraszat;
	
	/**
	 * Instantiates a new ultimate user interface.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ULTIMATE_USER_INTERFACE() throws IOException{
		super("Productivity Monitor " + MANDARIN.VERSION);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(MANDARIN.APPSIZE);
		setBackground(MANDARIN.ORANGE);
		
		szolitablamodell = new SzoliTablaModell();
		berletmodell = new TermekTablaModell("berletarlista.txt");
		kavemodell = new TermekTablaModell("kavearlista.txt");
		uditomodell = new TermekTablaModell("uditoarlista.txt");
		kremmodell = new TermekTablaModell("kremarlista.txt");
		fodraszatmodell = new TermekTablaModell("fodraszarlista.txt");

		szoliInputPanel = new SzoliInputPanel();
		kaveInputPanel = new TermekInputPanel(kavemodell.getArlista());
		berletInputPanel = new TermekInputPanel(berletmodell.getArlista());
		uditoInputPanel = new TermekInputPanel(uditomodell.getArlista());
		kremInputPanel = new TermekInputPanel(kremmodell.getArlista());
		fordaszatInputPanel = new TermekInputPanel(fodraszatmodell.getArlista());

		add(makeMenuBar(),BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		pane.add("Szolárium", makeTab(szoliInputPanel, szoli, szolitablamodell));
		pane.add("Kávé", makeTab(kaveInputPanel, kave, kavemodell));
		pane.add("Bérlet", makeTab(berletInputPanel, berlet, berletmodell));
		pane.add("Krém", makeTab(kremInputPanel, krem, kremmodell));
		pane.add("Üdítő", makeTab(uditoInputPanel, udito, uditomodell));
		pane.add("Fodrászat", makeTab(fordaszatInputPanel, fodraszat, fodraszatmodell));
		addFunctiontoPanelButton(szoliInputPanel, new szoliFelveszActionListener(), new DeleteActionListener(szolitablamodell));
		addFunctiontoPanelButton(berletInputPanel, new termekFelveszActionListener(berletmodell,berletInputPanel), new DeleteActionListener(berletmodell));
		addFunctiontoPanelButton(kaveInputPanel, new termekFelveszActionListener(kavemodell,kaveInputPanel), new DeleteActionListener(kavemodell));
		addFunctiontoPanelButton(kremInputPanel, new termekFelveszActionListener(kremmodell, kremInputPanel), new DeleteActionListener(kremmodell));
		addFunctiontoPanelButton(uditoInputPanel, new termekFelveszActionListener(uditomodell, uditoInputPanel), new DeleteActionListener(uditomodell));
		addFunctiontoPanelButton(fordaszatInputPanel, new termekFelveszActionListener(fodraszatmodell, fordaszatInputPanel), new DeleteActionListener(fodraszatmodell));
	}

	/**
	 * Adds functions to the InputPanel buttons.
	 * Every InputPanel has two buttons which can be used 
	 * to either add data or delete data from the database.
	 * Just need to add them functions.
	 * @param inputpanel the inputpanel
	 * @param felvesz the add function (ActionListener)
	 * @param torol the delete function (ActonListener)
	 */
	public void addFunctiontoPanelButton(InputPanel inputpanel,ActionListener felvesz, ActionListener torol){
		inputpanel.getfelveszButton().addActionListener(felvesz);
		inputpanel.gettorolButton().addActionListener(torol);
	}

	/**
	 * The UI is strongly tab based due to space issues.
	 *
	 * @param inputpanel the panel used to input data to the database.
	 * @param table the table which contains the datas.
	 * @param tablemodell the tablemodell which we can use to interact with the table
	 * @return a JComponent that can be added to the JTabbedPane
	 */
	protected JComponent makeTab(JPanel inputpanel, JTable table, AbstractTableModel tablemodell) {
		JPanel panel = new JPanel(true);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(MANDARIN.DARKGREEN);
		table = new JTable(tablemodell);
		table.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		table.setRowHeight(25);
		table.setBackground(MANDARIN.ORANGE);
		table.setFillsViewportHeight(true);
		JScrollPane s = new JScrollPane(table); 
		panel.add(inputpanel);
		panel.add(s);
		panel.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		return panel;
	}

	/**
	 * Creates the menubar of the application. 
	 * It also adds functions to the menu items, so the code is a little dense.
	 * The Menu has 3 options: Open a save file ({@link database.Serializer}), 
	 * Save the current state of the database  ({@link database.Serializer})
	 * and export the database in Excel ({@link database.Exportxlsx}). 
	 * Each menu items is supported with an ActionListener which provides 
	 * the above mentioned functionalities.
	 * @return the jComponent which can be added to the User Interface (JFrame)
	 * @see database.Serializer
	 * @see database.Exportxlsx
	 */
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
						if (!file.getName().contains(".mandarin"))
							throw new FileFormatException();
						List<List<? extends Record>> openedrecords = serializer.read(file);
						if (openedrecords.size()!=6)
							throw new ArrayIndexOutOfBoundsException();
						szolitablamodell.setSzoliRekordok(openedrecords.get(0));
						kavemodell.setTermekRekordok(openedrecords.get(1));
						berletmodell.setTermekRekordok(openedrecords.get(2));
						uditomodell.setTermekRekordok(openedrecords.get(3));
						kremmodell.setTermekRekordok(openedrecords.get(4));
						fodraszatmodell.setTermekRekordok(openedrecords.get(5));
					}catch (ClassNotFoundException e1) {
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Hiba a megnyitás során", 
								"Ejnye", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}catch (FileFormatException e2){
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Hibás fájlformátum", 
								"Ejnye", JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					} catch (IOException e1) {
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Hiba a megnyitás során", 
								"Ejnye", JOptionPane.ERROR_MESSAGE);
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
						sv.add(fodraszatmodell.getTermekRekordok());
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
					Exportxlsx exporter = new Exportxlsx();
					File place = fc.getCurrentDirectory();
					filename=fc.getSelectedFile().getName();
						List<List<? extends Record>> sv = new ArrayList<List<? extends Record>>();
						sv.add(szolitablamodell.getSzoliRekordok());
						sv.add(kavemodell.getTermekRekordok());
						sv.add(berletmodell.getTermekRekordok());
						sv.add(uditomodell.getTermekRekordok());
						sv.add(kremmodell.getTermekRekordok());
						sv.add(fodraszatmodell.getTermekRekordok());
						exporter.write(sv, place,filename );
				}
			}
		});
		JMenuItem total = new JMenuItem("Total");
		total.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<List<? extends Record>> sv = new ArrayList<List<? extends Record>>();
				sv.add(szolitablamodell.getSzoliRekordok());
				sv.add(kavemodell.getTermekRekordok());
				sv.add(berletmodell.getTermekRekordok());
				sv.add(uditomodell.getTermekRekordok());
				sv.add(kremmodell.getTermekRekordok());
				sv.add(fodraszatmodell.getTermekRekordok());
				Total t = new Total();
				t.addlists(sv);
				t.createandShowGui();
			}
		});
		fileMenu.add(save);
		fileMenu.add(exportToExcel);
		fileMenu.add(open);
		menuBar.add(fileMenu);
		menuBar.add(total);
		return menuBar;
	}

	/**
	 * The listener interface for receiving szoliFelveszAction events.
	 * The class that is interested in processing a szoliFelveszAction
	 * event implements this interface.
	 */
	public class szoliFelveszActionListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GregorianCalendar gcalendar = new GregorianCalendar();
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

	/**
	 * The listener interface for receiving termekFelveszAction events.
	 * The class that is interested in processing a termekFelveszAction
	 * event implements this interface.
	 */
	public class termekFelveszActionListener implements ActionListener{
		
		/** The panel. */
		private TermekInputPanel panel;
		
		/** The model. */
		private TermekTablaModell model;
		
		/**
		 * Instantiates a new termek felvesz action listener.
		 *
		 * @param model the model
		 * @param p the p
		 */
		public termekFelveszActionListener(TermekTablaModell model, TermekInputPanel p) {
			panel = p;
			this.model = model;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			GregorianCalendar gcalendar = new GregorianCalendar();
			String am = (gcalendar.get(Calendar.AM_PM)==0) ? "AM" : "PM";
			String timeString = String.valueOf(gcalendar.get(Calendar.HOUR)+":"
					+gcalendar.get(Calendar.MINUTE) +" "+ am);
			model.addTermekRekord(timeString,
					panel.gettermek(),
					panel.getfizetendo(),
					panel.getfizetett());
		}
	}

	/**
	 * The listener interface for receiving deleteAction events.
	 * The class that is interested in processing a deleteAction
	 * event implements this interface.When
	 * the actionPerformed event occurs, that object's appropriate
	 * method is invoked.
	 */
	public class DeleteActionListener implements ActionListener{
		
		/** The model. */
		private TablaModell model;
		
		/**
		 * Instantiates a new delete action listener.
		 *
		 * @param model the model
		 */
		public DeleteActionListener(TablaModell model) {
			this.model=model;
		}
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			model.deleteRekord();
		}
	}

	/**
	 * Creates and shows gui. Call this to display the complete JFrame
	 */
	@SuppressWarnings("deprecation")
	public void createandShowGui(){
		this.show();
	}

}
