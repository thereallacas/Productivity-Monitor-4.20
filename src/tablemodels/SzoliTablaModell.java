/*
 * 
 */
package tablemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import datatypes.recordtypes.*;

/**
 * Manages the table data insert, gets the values from the table, etc.
 * pretty much everything an {@link AbstractTableModel} has to do.
 * It has numerous attributes as well, such as the DEBUG variable, (stolen from Java Tutorials),
 * which sets whether the standard input displays the modifications in the table or not.
 * It has 2 collections: one List of SzoliRekord type items, and a Map to make the referencing to the
 * columns easier for...well, at least for me.
 */
public class SzoliTablaModell extends AbstractTableModel implements TablaModell{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The szolirekordok. */
	public List<SzoliRekord> szolirekordok = new ArrayList<SzoliRekord>();
	
	/** The debug. */
	public boolean DEBUG = true;
	
	/** The columns. */
	public Map<Integer, String > columns = new HashMap<Integer, String>(){
	/**
	 * The double bracing trick (as seen from stackoverflow)
	 */
		private static final long serialVersionUID = 1L;

		{
			put(0,"TIME");
			put(1,"GEP");
			put(2,"F/N");
			put(3,"PERC");
			put(4,"BERLET");
			put(5,"FIZETENDO");
			put(6,"FIZETETT");
		}};

		/**
		 * Gets the solarium records list as a more abstract list.
		 *
		 * @return the szolirekordok
		 */
		public List<? extends Record> getSzoliRekordok(){
			List<? extends Record> sv = szolirekordok;
			return sv;
		}

		/**
		 * Sets the solarium records list.
		 * Gives an error message i the list is invalid for some reason
		 *
		 * @param szolirekordok the new szoli rekordok
		 */
		@SuppressWarnings("unchecked")
		public void setSzoliRekordok(List<? extends Record> szolirekordok)throws ClassCastException{
			if ( (szolirekordok != null) && (szolirekordok.size() > 0) ) {
				if ( (szolirekordok.get(0) != null) && !(szolirekordok.get(0) instanceof SzoliRekord) ) {
					final JPanel panel = new JPanel();
					JOptionPane.showMessageDialog(panel, "Ebben a listában nem Szolibejegyzések vannak!", 
							"Ejnye", JOptionPane.ERROR_MESSAGE);
					throw new ClassCastException("Ebben a listában nem Szolibejegyzések vannak: "
							+ szolirekordok.get(0).getClass().getCanonicalName());
				}
			}
			this.szolirekordok = (List<SzoliRekord>) szolirekordok;
			fireTableDataChanged();
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
			// TODO Auto-generated method stub
			return szolirekordok.size();
		}
		

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getColumnCount()
		 */
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return SzoliRekord.attributumSzam;
		}

		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			SzoliRekord purchase = szolirekordok.get(rowIndex);
			switch(columns.get(columnIndex)) {
			case "TIME": return purchase.getTime();
			case "GEP": return purchase.getGep();
			case "F/N": return purchase.getF_n();
			case "PERC": return purchase.getPerc();
			case "BERLET": return purchase.isBerlet();
			case "FIZETENDO": return purchase.getFizetendo();
			default: return purchase.getFizetett();
			}
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
		@Override
		public Class getColumnClass(int columnIndex) {
			return getValueAt(0, columnIndex).getClass();
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.table.TableModel#getRowCount()
		 */
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
			SzoliRekord purchase = szolirekordok.get(rowIndex);
			switch(columns.get(columnIndex)) {
			case "TIME": purchase.setTime((String)value); break;
			case "GEP": purchase.setGep((String)value); break;
			case "F/N": purchase.setF_n((String)value); break;
			case "PERC": purchase.setPerc((int)value); break;
			case "BERLET": purchase.setBerlet((boolean)value); break;
			case "FIZETENDO": purchase.setFizetendo((String)value); break;
			case "FIZETETT": purchase.setFizetett((String)value); break;
			}
		}

		/**
		 * Adds the solarium service purchase to the szolirekord list.
		 * @see #szolirekordok 
		 * @param time the time
		 * @param gep the gep
		 * @param f_n the f_n
		 * @param perc the perc
		 * @param berlet the berlet
		 * @param fizetendo amout to pay
		 * @param fizetett the paid amount
		 */
		public void addSzoliRekord(String time, String gep, String f_n, 
				int perc, boolean berlet, String fizetendo, String fizetett) { 
			SzoliRekord newpurchase = new SzoliRekord(time,gep,f_n,perc,berlet,fizetendo,fizetett);
			szolirekordok.add(newpurchase);
			fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
		}

		/* (non-Javadoc)
		 * @see tablemodels.TablaModell#deleteRekord()
		 */
		@Override
		public void deleteRekord() {
			if (!szolirekordok.isEmpty())
				szolirekordok.remove(getRowCount()-1);
			else{
				final JPanel panel = new JPanel();
			    JOptionPane.showMessageDialog(panel, "Nincs mit törölni", "Ejnye", JOptionPane.WARNING_MESSAGE);
			}
			fireTableDataChanged();

		}
}
