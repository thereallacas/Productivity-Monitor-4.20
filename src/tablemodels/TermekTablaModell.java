/*
 * 
 */
package tablemodels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import datatypes.recordtypes.Record;
import datatypes.recordtypes.TermekRekord;
import datatypes.Termek;


/**
 * Manages the table data insert, gets the values from the table, etc.
 * pretty much everything an {@link AbstractTableModel} has to do.
 * It has numerous attributes as well, such as the DEBUG variable, (stolen from Java Tutorials),
 * which sets whether the standard input displays the modifications in the table or not.
 * This particular model has 3 collections: one List of TermekRekord type items, one Map to make the referencing to the
 * columns easier, and a List of {@link Termek} for storing the price list.
 */
public class TermekTablaModell extends AbstractTableModel implements TablaModell{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The debug. */
	boolean DEBUG=true;

	/** The arlistafajl.
	 * the file that stores the product and prices. 
	 * it has to be in a txt format, and begins with "arlista" followed by
	 * a newline character, the list of the products and their prices,
	 * separated with tabs. */
	private File arlistafajl;

	/** The arlista. */
	public List<Termek> arlista = new ArrayList<>();

	/** The termekrekordok. */
	public List<TermekRekord> termekrekordok  = new ArrayList<>();

	/** The columns. */
	public Map<Integer, String > columns = new HashMap<Integer, String>(){/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		{
			put(0,"TIME");
			put(1,"TERMÉKNÉV");
			put(2,"FIZETENDO");
			put(3,"FIZETETT");
		}};

		/**
		 * Gets the termek rekordok.
		 *
		 * @return the termek rekordok
		 */
		public List<? extends Record> getTermekRekordok(){
			List<? extends Record> sv = termekrekordok;
			return sv;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public Class getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}

		/**
		 * Sets the termek rekordok.
		 *
		 * @param termekrekordok the new termek rekordok
		 */
		@SuppressWarnings("unchecked")
		public void setTermekRekordok(List<? extends Record> termekrekordok){
			try{
				if ( (termekrekordok != null) && (termekrekordok.size() > 0) ) {
					if ( (termekrekordok.get(0) != null) && !(termekrekordok.get(0) instanceof TermekRekord) ) {
						throw new ClassCastException("Ebben a listában nem Termékbejegyzések vannak: "
								+ termekrekordok.get(0).getClass().getCanonicalName());	
					}
				}
				this.termekrekordok = (List<TermekRekord>) termekrekordok;
				fireTableDataChanged();
			}catch(ClassCastException e){
				final JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Hibás vagy sérült fájl", 
						"Hiba", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		/**
		 * Instantiates a new termek tabla modell.
		 * Sets the price list ({@link #arlista})
		 *
		 * @param filename the filename
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public TermekTablaModell (String filename) throws IOException{
			super();
			SetArlista(filename);
		}

		/**
		 * Gets the price list.
		 *
		 * @return the arlista
		 */
		public List<Termek> getArlista(){
			return arlista;
		}

		/**
		 * Sets the price list. Checks the 
		 * content of the file to see if it is
		 * a valid price list file
		 * @param filename the name of the file
		 * @throws IOException Signals that an I/O exception has occurred.
		 * @see #arlistafajl
		 */
		public void SetArlista(String filename)throws IOException{
			arlistafajl = new File(filename);
			if (!arlistafajl.exists()){
				final JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Árlista nem található: "+ filename, 
						"Ejnye", JOptionPane.ERROR_MESSAGE);
				throw new FileNotFoundException("Árlista nem található: "+ filename);
			}
			BufferedReader br = new BufferedReader(new FileReader(arlistafajl));
			try {
				String header = br.readLine();
				if (!header.equals("arlista")){
					final JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Ez nem egy valid árlistafájl: "+ filename, 
							"Ejnye", JOptionPane.ERROR_MESSAGE);
					throw new FileNotFoundException("Ez nem egy valid áristafájl: " + filename);
				}
				while (true) {
					String line = br.readLine();
					if (line == null) 
						break;
					String[] datas = line.split("\t");
					if (datas.length!=2){
						final JPanel panel = new JPanel();
						JOptionPane.showMessageDialog(panel, "Ebben az árlistában a bejegyzések hibásak: "
								+ filename, "Ejnye", JOptionPane.ERROR_MESSAGE);
						throw new FileNotFoundException("Ez nem egy valid áristafájl: "+ filename);
					}
					arlista.add(new Termek(datas[0], datas[1]));
				}
				br.close();//Kell-e ez?
			} finally{
				br.close();
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
		 */
		public String getColumnName(int columnIndex) {
			return columns.get(columnIndex);
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public int getRowCount() {
			// TODO SQL 
			return termekrekordok.size();
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			// TODO SQL
			return 4;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			TermekRekord purchase = termekrekordok.get(rowIndex);
			switch(columns.get(columnIndex)) {
			case "TIME": return purchase.getTime();
			case "TERMÉKNÉV": return purchase.getTermeknev();
			case "FIZETENDO": return purchase.getEgysegar();
			default: return purchase.getFizetett();
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columns.get(columnIndex)=="TIME") {
				return false;
			} else {
				return true;
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
		 */
		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex){
			if (DEBUG) {
				System.out.println("Setting value at " + rowIndex + "," + columnIndex
						+ " to " + value
						+ " (an instance of "
						+ value.getClass() + ")");
			}
			TermekRekord purchase = termekrekordok.get(rowIndex);
			switch(columns.get(columnIndex)) {
			case "TIME": purchase.setTime((String)value); break;
			case "TERMÉKNÉV": purchase.setTermeknev((String)value); break;
			case "FIZETENDO": purchase.setEgysegar((String)value); break;
			case "FIZETETT": purchase.setFizetett((String)value); break;
			}
		}

		/**
		 * Adds the termek rekord.
		 *
		 * @param time the time
		 * @param termeknev the product name
		 * @param egysegar the unit price
		 * @param fizetett the the price paid
		 */
		public void addTermekRekord(String time, String termeknev, String egysegar, String fizetett) { 
			TermekRekord newpurchase = new TermekRekord(time,termeknev,egysegar,fizetett);
			termekrekordok.add(newpurchase);
			fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
		}

		/* (non-Javadoc)
		 * @see tablemodels.TablaModell#deleteRekord()
		 */
		@Override
		public void deleteRekord() {
			if (!termekrekordok.isEmpty())
				termekrekordok.remove(getRowCount()-1);
			else{
				final JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Nincs mit törölni", "Ejnye", JOptionPane.WARNING_MESSAGE);
			}
			fireTableDataChanged();
		}

}
