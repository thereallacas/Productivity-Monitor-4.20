package database;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import datatypes.recordtypes.*;

public class Serializer {
	public void write(List<List<? extends Record>> recordstosave, File place, String name) throws IOException{
		DateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(place, dtformat.format(date)+ name + ".mandarin")));
		oos.writeObject(recordstosave);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<List<? extends Record>> read(File what) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(what));
		List<List<? extends Record>> recordsread = new ArrayList<List<? extends Record>>();
		recordsread = (List<List<? extends Record>>)ois.readObject();
		ois.close();
		return recordsread;
	}
	
	@SuppressWarnings("unchecked")
	public List<SzoliRekord> readszolirekordok(List<SzoliRekord> szolirekordok, String filename)throws IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		szolirekordok = (List<SzoliRekord>)ois.readObject();
		ois.close();
		return szolirekordok;
	}

	@SuppressWarnings("unchecked")
	public List<TermekRekord> readtermekrekordok(List<TermekRekord> termekrekordok, String filename) throws IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
		termekrekordok = (List<TermekRekord>)ois.readObject();
		ois.close();
		return termekrekordok;
	}
}
