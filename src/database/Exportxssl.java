package database;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.xssf.usermodel.*;
import datatypes.recordtypes.Record;
import datatypes.recordtypes.SzoliRekord;
import datatypes.recordtypes.TermekRekord;

public class Exportxssl{
	enum HEADER_INDICES_S {
		TIME(0), GEP(1), F_N(2), PERC(3), BERLET(4), FIZETENDO(5), ADOTT(6); 
		private int value;    
		private HEADER_INDICES_S(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}

	enum HEADER_INDICES_T {
		TIME(0), TERMEK(1), EGYSEGAR(2), ADOTT(3); 
		private int value;    
		private HEADER_INDICES_T(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}

	public void write (List<List<? extends Record>> sv, File place, String filename){
		try
		{
			DateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
			Date date = new Date();
			XSSFWorkbook napiForg = create(sv);
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(place, dtformat.format(date)+filename+".xlsx"));
			napiForg.write(out);
			out.close();
			napiForg.close();
			final JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Sikeres mentés", 
					"Hurrá", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("napiforg.xlsx written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public XSSFWorkbook create(List<List<? extends Record>> sv)
	{
		//Blank workbook
		XSSFWorkbook napiForg = new XSSFWorkbook();

		XSSFSheet szoliSheet = napiForg.createSheet("Szoli");
		XSSFSheet berletSheet = napiForg.createSheet("Bérlet");
		XSSFSheet kremSheet = napiForg.createSheet("Krém");
		XSSFSheet uditoSheet = napiForg.createSheet("Üdítő");
		XSSFSheet kaveSheet = napiForg.createSheet("Kávé");

		List<SzoliRekord> szolik = (List<SzoliRekord>)sv.get(0);
		List<TermekRekord> kavek = (List<TermekRekord>)sv.get(1);
		List<TermekRekord> berletek = (List<TermekRekord>)sv.get(2);
		List<TermekRekord> uditok = (List<TermekRekord>)sv.get(3);
		List<TermekRekord> kremek = (List<TermekRekord>)sv.get(4);

		XSSFRow header = szoliSheet.createRow(0);
		header.createCell(HEADER_INDICES_S.TIME.getValue()).setCellValue("TIME");
		header.createCell(HEADER_INDICES_S.GEP.getValue()).setCellValue("GÉP");
		header.createCell(HEADER_INDICES_S.F_N.getValue()).setCellValue("F_N");
		header.createCell(HEADER_INDICES_S.PERC.getValue()).setCellValue("PERC");
		header.createCell(HEADER_INDICES_S.BERLET.getValue()).setCellValue("BÉRLET");
		header.createCell(HEADER_INDICES_S.FIZETENDO.getValue()).setCellValue("FIZETENDŐ");
		header.createCell(HEADER_INDICES_S.ADOTT.getValue()).setCellValue("ADOTT");

		int r=1;
		for (SzoliRekord sz:szolik){
			XSSFRow row = szoliSheet.createRow(r);
			row.createCell(HEADER_INDICES_S.TIME.getValue()).setCellValue(sz.getTime());
			row.createCell(HEADER_INDICES_S.ADOTT.getValue()).setCellValue(sz.getFizetett());
			row.createCell(HEADER_INDICES_S.BERLET.getValue()).setCellValue(((sz.isBerlet()==false)) ? "IGEN":"NEM");
			row.createCell(HEADER_INDICES_S.F_N.getValue()).setCellValue(sz.getF_n());
			row.createCell(HEADER_INDICES_S.FIZETENDO.getValue()).setCellValue(sz.getFizetendo());
			row.createCell(HEADER_INDICES_S.PERC.getValue()).setCellValue(sz.getPerc());
			row.createCell(HEADER_INDICES_S.GEP.getValue()).setCellValue(sz.getGep());
			r++;
		}
		
		XSSFSheet[] sheets = {berletSheet, kremSheet, kaveSheet, uditoSheet};
		
		for (XSSFSheet s:sheets){
			XSSFRow row = s.createRow(0);
			row.createCell(HEADER_INDICES_T.TIME.getValue()).setCellValue("TIME");
			row.createCell(HEADER_INDICES_T.TERMEK.getValue()).setCellValue("TERMÉK");
			row.createCell(HEADER_INDICES_T.EGYSEGAR.getValue()).setCellValue("EGYSÉGÁR");
			row.createCell(HEADER_INDICES_T.ADOTT.getValue()).setCellValue("ADOTT");
		}
		
		List<List<TermekRekord>> tk = new ArrayList<List<TermekRekord>>();
		tk.add(berletek);tk.add(kremek); tk.add(kavek); tk.add(uditok);
		
		r=1;
		for (int i=0; i<4; i++){
			r=1;
			for (TermekRekord t:tk.get(i)){
				XSSFRow row = sheets[i].createRow(r);
				row.createCell(HEADER_INDICES_T.TIME.getValue()).setCellValue(t.getTime());
				row.createCell(HEADER_INDICES_T.TERMEK.getValue()).setCellValue(t.getTermeknev());
				row.createCell(HEADER_INDICES_T.EGYSEGAR.getValue()).setCellValue(t.getEgysegar());
				row.createCell(HEADER_INDICES_T.ADOTT.getValue()).setCellValue(t.getFizetett());
				r++;
			}
		}
		return napiForg;
	}
}