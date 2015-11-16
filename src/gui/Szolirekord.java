package gui;

public class Szolirekord {
	private int gep;
	private String f_n;
	private boolean berlet;
	private int perc;
	private int fizetendo;
	private int fizetett;
	
	
	//TODO TODO DODO
	public Szolirekord(int perc){
		setFizetendo(perc);
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
	
	
}
