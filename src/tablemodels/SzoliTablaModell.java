package tablemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import datatypes.recordtypes.*;

public class SzoliTablaModell extends AbstractTableModel implements TablaModell{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<SzoliRekord> szolirekordok = new ArrayList<SzoliRekord>();
	public boolean DEBUG = true;
	public Map<Integer, String > columns = new HashMap<Integer, String>(){/**
	 * 
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

		public List<? extends Record> getSzoliRekordok(){
			List<? extends Record> sv = szolirekordok;
			return sv;
		}

		@SuppressWarnings("unchecked")
		public void setSzoliRekordok(List<? extends Record> szolirekordok){
			if ( (szolirekordok != null) && (szolirekordok.size() > 0) ) {
				if ( (szolirekordok.get(0) != null) && !(szolirekordok.get(0) instanceof SzoliRekord) ) {
					throw new ClassCastException("Ebben a listában nem Szolibejegyzések vannak: "
							+ szolirekordok.get(0).getClass().getCanonicalName());
				}
			}
			this.szolirekordok = (List<SzoliRekord>) szolirekordok;
			fireTableDataChanged();
		}

		public String getColumnName(int columnIndex) {
			return columns.get(columnIndex);
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return szolirekordok.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return SzoliRekord.attributumSzam;
		}

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

		public void addSzoliRekord(String time, String gep, String f_n, 
				int perc, boolean berlet, String fizetendo, String string) { 
			SzoliRekord newpurchase = new SzoliRekord(time,gep,f_n,perc,berlet,fizetendo,string);
			szolirekordok.add(newpurchase);
			fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
		}

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
