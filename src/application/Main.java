package application;

import gui.MANDARIN;
import gui.ULTIMATE_USER_INTERFACE;

public class Main {
	public static void main(String[] args) {
		System.out.println("This is Productivity Monitor " + MANDARIN.VERSION);
		ULTIMATE_USER_INTERFACE gui = new ULTIMATE_USER_INTERFACE();
		gui.createandShowGui();
	}
}
