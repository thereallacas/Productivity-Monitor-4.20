package database;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gui.SzoliRekord;

public class Serializer {
	public void write(List<SzoliRekord> szolirekordok){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("szoli.dat"));
			oos.writeObject(szolirekordok);
			oos.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void read(List<SzoliRekord> szolirekordok) {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("szoli.dat"));
			szolirekordok = (List<SzoliRekord>)ois.readObject();
			ois.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
