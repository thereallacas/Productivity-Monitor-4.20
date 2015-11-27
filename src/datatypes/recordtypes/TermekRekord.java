package datatypes.recordtypes;

import java.io.Serializable;

public class TermekRekord extends Record implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1480075798550735050L;
	private String time;
	private String termeknev;
	private String fizetendo;
	private String fizetett;
	public static int attributumSzam = 4;
	
	public TermekRekord(String time, String termeknev, String egysegar, String fizetett){
		setTime(time);setTermeknev(termeknev); setEgysegar(egysegar);setFizetett(fizetett);
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTermeknev() {
		return termeknev;
	}

	public void setTermeknev(String termeknev) {
		this.termeknev = termeknev;
	}

	public String getEgysegar() {
		return fizetendo;
	}

	public void setEgysegar(String fizetendo) {
		this.fizetendo = fizetendo;
	}

	public String getFizetett() {
		return fizetett;
	}

	public void setFizetett(String fizetett) {
		this.fizetett = fizetett;
	}
	
}
