package gui;

import java.io.Serializable;

public class SzoliRekord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4828934103074228498L;
	private String time;
	private String gep;
	private String f_n;
	private int perc;
	private boolean berlet;
	private String fizetendo;
	private String fizetett;
	public static int attributumSzam = 7;
	
	public SzoliRekord(String time, String string, String f_n, int perc,
			boolean berlet, String fizetendo, String fizetett){
		setTime(time);setGep(string);setF_n(f_n);setPerc(perc);setBerlet(berlet);
		setFizetendo(fizetendo);setFizetett(fizetett);
	}
	
	public String getTime(){
		return time;
	}
	public void setTime(String value) {
		this.time = value;
	}
	public String getGep() {
		return gep;
	}
	public void setGep(String gep) {
		this.gep = gep;
	}
	public String getF_n() {
		return f_n;
	}
	public void setF_n(String f_n) {
		this.f_n = f_n;
	}
	public boolean isBerlet() {
		return berlet;
	}
	public void setBerlet(boolean berlet) {
		this.berlet = berlet;
	}
	public int getPerc() {
		return perc;
	}
	public void setPerc(int perc) {
		this.perc = perc;
	}
	public String getFizetendo() {
		return fizetendo;
	}
	public void setFizetendo(String fizetendo) {
		this.fizetendo = fizetendo;
	}
	public String getFizetett() {
		return fizetett;
	}
	public void setFizetett(String fizetett){
		this.fizetett = fizetett;
	}
}
