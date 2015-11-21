package application;

import java.io.IOException;

import database.Serializer;
import gui.MANDARIN;
import gui.ULTIMATE_USER_INTERFACE;

public class Main {
	public static void main(String[] args) {
		System.out.println("This is Productivity Monitor " + MANDARIN.VERSION);
		try{
		ULTIMATE_USER_INTERFACE gui = new ULTIMATE_USER_INTERFACE();
		gui.createandShowGui();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
		}
	}
}
