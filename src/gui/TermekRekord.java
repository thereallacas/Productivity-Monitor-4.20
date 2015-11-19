package gui;

public class TermekRekord extends Termek{
	private String time;
	private String termeknev;
	private int egysegar;
	private int fizetett;
	
	public TermekRekord(String time, String termeknev, int egysegar, int fizetett){
		super(termeknev, egysegar);
		setTime(time);setTermeknev(termeknev);setFizetett(fizetett);
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

	public int getEgysegar() {
		return egysegar;
	}

	public void setEgysegar(int fizetendo) {
		this.egysegar = fizetendo;
	}

	public int getFizetett() {
		return fizetett;
	}

	public void setFizetett(int fizetett) {
		this.fizetett = fizetett;
	}
	
}
