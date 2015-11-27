/*
 * 
 */
package application;

import java.io.IOException;
import gui.MANDARIN;
import gui.ULTIMATE_USER_INTERFACE;

/**
 * Contains the program's entry point, the main method.
 */
public class Main {
	
	/**
	 * Entry point for the program. Writes on the standard output,
	 * and instantiate the {@link gui.ULTIMATE_USER_INTERFACE} class.
	 * Also handles exceptions.
	 * @param args the arguments for the well-known main method.
	 */
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
