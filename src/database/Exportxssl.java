package database;

import java.io.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class Exportxssl{
	enum HEADER_INDICES {
		TIME(0), GEP(1), F_N(2), PERC(3), BERLET(4), ADOTT(5);
		private int value;    
		private HEADER_INDICES(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}

	public static void create(String[] args)
	{
		//Blank workbook
		XSSFWorkbook napiForg = new XSSFWorkbook();

		//Create a blank sheet
		XSSFSheet szoliSheet = napiForg.createSheet("Szoli");

		XSSFRow header = szoliSheet.createRow(0);
		Cell TIME = header.createCell(HEADER_INDICES.TIME.getValue());
		Cell GEP = header.createCell(HEADER_INDICES.GEP.getValue());
		Cell F_N = header.createCell(HEADER_INDICES.F_N.getValue());
		Cell PERC = header.createCell(HEADER_INDICES.PERC.getValue());
		Cell BERLET = header.createCell(HEADER_INDICES.BERLET.getValue());
		Cell ADOTT = header.createCell(HEADER_INDICES.ADOTT.getValue());

		TIME.setCellValue("TIME");
		GEP.setCellValue("GEP");
		F_N.setCellValue("F_N");
		PERC.setCellValue("PERC");
		BERLET.setCellValue("BERLET");
		ADOTT.setCellValue("ADOTT");

		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("napiforg.xlsx"));
			napiForg.write(out);
			out.close();
			napiForg.close();
			System.out.println("napiforg.xlsx written successfully on disk.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}