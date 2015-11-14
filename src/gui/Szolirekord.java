package gui;

public class Szolirekord {
	private int gep;
	private boolean f_n;
	private boolean berlet;
	private int perc;
	private int fizetendo;
	public int getGep() {
		return gep;
	}
	public void setGep(int gep) {
		this.gep = gep;
	}
	public boolean isF_n() {
		return f_n;
	}
	public void setF_n(boolean f_n) {
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
