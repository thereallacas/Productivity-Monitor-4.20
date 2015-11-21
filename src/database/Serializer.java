package database;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import gui.SzoliRekord;
import gui.TermekRekord;

public class Serializer {
	public void writeszolirekordok(List<SzoliRekord> szolirekordok, String nev) throws IOException{
			DateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
			Date date = new Date();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dtformat.format(date)+" "+nev));
			oos.writeObject(szolirekordok);
			oos.close();
	}
	
	public void writetermekrekordok(List<TermekRekord> termekrekordok, String nev) throws IOException{
			DateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
			Date date = new Date();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dtformat.format(date)+" "+nev));
			oos.writeObject(termekrekordok);
			oos.close();
	}

	public List<SzoliRekord> readszolirekordok(List<SzoliRekord> szolirekordok, String filename)throws IOException, ClassNotFoundException {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			szolirekordok = (List<SzoliRekord>)ois.readObject();
			ois.close();
			return szolirekordok;
	}
	
	public List<TermekRekord> readtermekrekordok(List<TermekRekord> termekrekordok, String filename) throws IOException, ClassNotFoundException {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			termekrekordok = (List<TermekRekord>)ois.readObject();
			ois.close();
			return termekrekordok;
	}
}
