package tablemodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import gui.SzoliRekord;

public class SzoliTablaModell extends AbstractTableModel{

	public List<SzoliRekord> szolirekordok = new ArrayList<SzoliRekord>();
	public boolean DEBUG = true;
	public Map<Integer, String > columns = new HashMap<Integer, String>(){{
		put(0,"TIME");
		put(1,"GEP");
		put(2,"F/N");
		put(3,"PERC");
		put(4,"BERLET");
		put(5,"FIZETENDO");
		put(6,"FIZETETT");
	}};

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
		case "GEP": purchase.setGep((int)value); break;
		case "F/N": purchase.setF_n((String)value); break;
		case "PERC": purchase.setPerc((int)value); break;
		case "BERLET": purchase.setBerlet((boolean)value); break;
		case "FIZETENDO": purchase.setFizetendo((int)value); break;
		case "FIZETETT": purchase.setFizetett((int)value); break;
		}
	}

	public void addSzoliRekord(String time, int gep, String f_n, 
			int perc, boolean berlet, int fizetendo, int fizetett) { 
		SzoliRekord newpurchase = new SzoliRekord(time,gep,f_n,perc,berlet,fizetendo,fizetett);
		szolirekordok.add(newpurchase);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}

}
