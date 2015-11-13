package application;

import gui.MAGIC;
import gui.ULTIMATE_USER_INTERFACE;

public class Main {
	public static void main(String[] args) {
		System.out.println("This is Productivity Monitor " + MAGIC.VERSION + 0);
		ULTIMATE_USER_INTERFACE gui = new ULTIMATE_USER_INTERFACE();
		gui.createandShowGui();
	}
}
