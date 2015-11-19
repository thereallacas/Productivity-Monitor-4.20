package tablemodels;

import javax.print.DocFlavor.STRING;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;
import gui.Termek;
import gui.TermekRekord;

public class TermekTablaModell extends AbstractTableModel{

	boolean DEBUG=true;
	private File arlistafajl;
	public List<TermekRekord> termekrekordok  = new ArrayList<>();
	public List<Termek> arlista = new ArrayList<>();
	public Map<Integer, String > columns = new HashMap<Integer, String>(){{
		put(0,"TIME");
		put(1,"TERMÉKNÉV");
		put(2,"FIZETENDO");
		put(3,"FIZETETT");
	}};
	
	
	public TermekTablaModell (String filename) throws IOException{
		super();
		SetArlista(filename);
	}
	
	
	public List<Termek> getArlista(){
		return arlista;
	}
	
	public void SetArlista(String filename)throws IOException{
		arlistafajl = new File(filename);
		if (!arlistafajl.exists())
			throw new FileNotFoundException("Nincsen ilyen árlista: "+ filename);
		BufferedReader br = new BufferedReader(new FileReader(arlistafajl));
		try {
			String header = br.readLine();
			if (!header.equals("arlista"))
				throw new FileNotFoundException("Ez nem egy valid áristafájl");
			while (true) {
				String line = br.readLine();
				if (line == null) 
					break;
				String[] datas = line.split("\t");
				if (datas.length!=2)
					throw new FileNotFoundException("Ez nem egy valid áristafájl");
				arlista.add(new Termek(datas[0], Integer.parseInt(datas[1])));
			}
			br.close();//Kell-e ez?
		} finally{
			br.close();
		}
	}

	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex);
	}

	@Override
	public int getRowCount() {
		// TODO SQL 
		return termekrekordok.size();
	}

	@Override
	public int getColumnCount() {
		// TODO SQL
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TermekRekord purchase = termekrekordok.get(rowIndex);
		switch(columns.get(columnIndex)) {
		case "TIME": return purchase.getTime();
		case "TERMEKNEV": return purchase.getTermeknev();
		case "FIZETENDO": return purchase.getEgysegar();
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
		TermekRekord purchase = termekrekordok.get(rowIndex);
		switch(columns.get(columnIndex)) {
		case "TIME": purchase.setTime((String)value); break;
		case "TERMEKNEV": purchase.setTermeknev((String)value); break;
		case "FIZETENDO": purchase.setEgysegar((int)value); break;
		case "FIZETETT": purchase.setFizetett((int)value); break;
		}
	}

	public void addTermekRekord(String time, String termeknev, int egysegar, int fizetett) { 
		TermekRekord newpurchase = new TermekRekord(time,termeknev,egysegar,fizetett);
		termekrekordok.add(newpurchase);
		fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
	}

}
