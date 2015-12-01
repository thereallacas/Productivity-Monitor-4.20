/*
 * 
 */
package database;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import datatypes.recordtypes.*;


/**
 * Serializes the data already written in the monitor program.
 * Has methods to write and read serialized data.
 */
public class Serializer {
	/**
	 * Writes the List of Lists to the file system.
	 * Uses the current date and the filename
	 * to create the name of the file. Causes overwrite
	 * of files with the same name. Also, the file it creates
	 * is the official file format of Productivity Monitor, with
	 * the extension .mandarin. This method is used to create 
	 * save files during monitoring sessions.
	 * @param recordstosave a collection of collections of records.
	 * @param place the parent directory of the file to be saved.
	 * @param name the name of the file.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(List<List<? extends Record>> recordstosave, File place, String name) throws IOException{
		if (recordstosave.size()>6)
			throw new WriteAbortedException(name, new Exception());
		DateFormat dtformat = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(place, dtformat.format(date)+ name + ".mandarin")));
		oos.writeObject(recordstosave);
		oos.close();
	}
	
	/**
	 * Reads the serialized savefile. Throws 
	 * exception when the file format is not acceptable
	 * or the file is inappropiate.
	 * @param file the file opened
	 * @return recordsread the collection of collections of record types.
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	@SuppressWarnings("unchecked")
	public List<List<? extends Record>> read(File file) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		List<List<? extends Record>> recordsread = new ArrayList<List<? extends Record>>();
		recordsread = (List<List<? extends Record>>)ois.readObject();
		ois.close();
		return recordsread;
	}
}
