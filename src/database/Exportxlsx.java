/*
 * 
 */
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


/**
 * Creates and exports the data from the input system.
 * it uses the apache poi library for he various method
 * calls.
 */
public class Exportxlsx{

	/**
	 * Provides easier handling of xlsx table indices by
	 * matching the numbers (columns) to the names of the attributes.
	 * This enumeration is for the record type {@link SzoliRekord}
	 */
	enum HEADER_INDICES_S {

		TIME(0), 
		GEP(1), 
		F_N(2), 
		PERC(3), 
		BERLET(4), 
		FIZETENDO(5), 
		ADOTT(6); 
		private int value;    

		/**
		 * Instantiates a new header indices s.
		 *
		 * @param value the value of the indice.
		 */
		private HEADER_INDICES_S(int value) {
			this.value = value;
		}

		/**
		 * Gets the indice value of the column name.
		 *
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * The Enum HEADER_INDICES_T.
	 */
	enum HEADER_INDICES_T {

		/** The time. */
		TIME(0), 
		/** The termek. */
		TERMEK(1), 
		/** The egysegar. */
		EGYSEGAR(2), 
		/** The adott. */
		ADOTT(3); 

		/** The value. */
		private int value;    

		/**
		 * Instantiates a new header indices t.
		 *
		 * @param value the value
		 */
		private HEADER_INDICES_T(int value) {
			this.value = value;
		}

		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public int getValue() {
			return value;
		}
	}

	/**
	 * Writes the .xlsx file to the filesystem.
	 * It uses the current date and the filename
	 * to create the name of the file. Causes overwrite
	 * of files with the same name. Uses the method {@link #create(List) create}
	 * to compose the xlssx workbook.
	 * @param sv the data to be saved.
	 * @param place the directory parent path of the file to be saved.
	 * @param filename the name of the file
	 */
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

	/**
	 * Creates an xlsx workbook file from the daily production.
	 * @param sv the data collection of collections.
	 * @return the XSSF workbook that can be saved.
	 */
	@SuppressWarnings("unchecked")
	public XSSFWorkbook create(List<List<? extends Record>> sv)
	{
		XSSFWorkbook napiForg = new XSSFWorkbook();

		XSSFSheet szoliSheet = napiForg.createSheet("Szoli");
		XSSFSheet berletSheet = napiForg.createSheet("Bérlet");
		XSSFSheet kremSheet = napiForg.createSheet("Krém");
		XSSFSheet uditoSheet = napiForg.createSheet("Üdítő");
		XSSFSheet kaveSheet = napiForg.createSheet("Kávé");
		XSSFSheet fodraszSheet = napiForg.createSheet("Fodrászat");

		List<SzoliRekord> szolik = (List<SzoliRekord>)sv.get(0);
		List<TermekRekord> kavek = (List<TermekRekord>)sv.get(1);
		List<TermekRekord> berletek = (List<TermekRekord>)sv.get(2);
		List<TermekRekord> uditok = (List<TermekRekord>)sv.get(3);
		List<TermekRekord> kremek = (List<TermekRekord>)sv.get(4);
		List<TermekRekord> fodraszat = (List<TermekRekord>)sv.get(5);

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

		XSSFSheet[] sheets = {berletSheet, kremSheet, kaveSheet, uditoSheet, fodraszSheet};

		for (XSSFSheet s:sheets){
			XSSFRow row = s.createRow(0);
			row.createCell(HEADER_INDICES_T.TIME.getValue()).setCellValue("TIME");
			row.createCell(HEADER_INDICES_T.TERMEK.getValue()).setCellValue("TERMÉK");
			row.createCell(HEADER_INDICES_T.EGYSEGAR.getValue()).setCellValue("EGYSÉGÁR");
			row.createCell(HEADER_INDICES_T.ADOTT.getValue()).setCellValue("ADOTT");
		}

		List<List<TermekRekord>> tk = new ArrayList<List<TermekRekord>>();
		tk.add(berletek);tk.add(kremek); tk.add(kavek); tk.add(uditok); tk.add(fodraszat);

		r=1;
		for (int i=0; i<5; i++){
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