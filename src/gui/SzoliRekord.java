package gui;

public class SzoliRekord {
	private String time;
	private int gep;
	private String f_n;
	private int perc;
	private boolean berlet;
	private int fizetendo;
	private int fizetett;
	
	public SzoliRekord(String time, int gep, String f_n, int perc,
			boolean berlet, int fizetendo, int fizetett){
		setTime(time);setGep(gep);setF_n(f_n);setPerc(perc);setBerlet(berlet);
		setFizetendo(fizetendo);setFizetett(fizetett);
	}
	public String getTime(){
		return time;
	}
	public void setTime(String value) {
		this.time = value;
	}
	public int getGep() {
		return gep;
	}
	public void setGep(int gep) {
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
	public int getFizetendo() {
		return getPerc()*MANDARIN.UNIT;
	}
	public void setFizetendo(int fizetendo) {
		this.fizetendo = getPerc()*MANDARIN.UNIT;
	}
	public int getFizetett() {
		return fizetett;
	}
	public void setFizetett(int fizetett){
		this.fizetett = fizetett;
	}	
}
