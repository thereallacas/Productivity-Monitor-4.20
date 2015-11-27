/*
 * 
 */
package datatypes.recordtypes;

import java.io.Serializable;

/**
 * The record type for solarium service
 * it also implements the interface serializable
 * and extends the class {@link Record} 
 * It has all the attributes needed to properly record a solarium service
 * purchase.
 */
public class SzoliRekord extends Record implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4828934103074228498L;
	
	/** The time of the purchase. */
	private String time;
	
	/** The number of the machine the service was used. Uses the type String
	 * because of some casting problems... well I guess it would be painful to change that now */
	private String gep;
	
	/** The sex of the customer using the service. */
	private String f_n;
	
	/** The minutes the service was used (machine run time), serves as a multiplicator for the price unit to 
	 * calculate the {@link fizetendo}*/
	private int perc;
	
	/** Show whether the customer has a... season ticket (that translate tho)*/
	private boolean berlet;
	
	/** The price that has to be paid for the service */
	private String fizetendo;
	
	/** The price that was paid for the product. It can be slightly more than the actual price,
	 * thats why it has to be recorded properly. (tip)*/
	private String fizetett;
	
	/** The number of attributes this record type has. */
	public static int attributumSzam = 7;
	
	/**
	 * Instantiates a new solarium service record.
	 *
	 * @param time the time
	 * @param gep the machine
	 * @param f_n the sex of the customer
	 * @param perc the minutes
	 * @param berlet has seasonal ticket
	 * @param fizetendo the price to be paid
	 * @param fizetett the price paid
	 */
	public SzoliRekord(String time, String gep, String f_n, int perc,
			boolean berlet, String fizetendo, String fizetett){
		setTime(time);setGep(gep);setF_n(f_n);setPerc(perc);setBerlet(berlet);
		setFizetendo(fizetendo);setFizetett(fizetett);
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime(){
		return time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param value the new time
	 */
	public void setTime(String value) {
		this.time = value;
	}
	
	/**
	 * Gets the machine number.
	 *
	 * @return the machine number
	 */
	public String getGep() {
		return gep;
	}
	
	/**
	 * Sets the machine number.
	 *
	 * @param gep the new machine number
	 */
	public void setGep(String gep) {
		this.gep = gep;
	}
	
	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getF_n() {
		return f_n;
	}
	
	/**
	 * Sets the sex.
	 *
	 * @param f_n the new sex
	 */
	public void setF_n(String f_n) {
		this.f_n = f_n;
	}
	
	/**
	 * Checks if is berlet.
	 *
	 * @return true, if is berlet
	 */
	public boolean isBerlet() {
		return berlet;
	}
	
	/**
	 * Sets the berlet.
	 *
	 * @param berlet the new berlet
	 */
	public void setBerlet(boolean berlet) {
		this.berlet = berlet;
	}
	
	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public int getPerc() {
		return perc;
	}
	
	/**
	 * Sets the minute.
	 *
	 * @param perc the new minute
	 */
	public void setPerc(int perc) {
		this.perc = perc;
	}
	
	/**
	 * Gets the fizetendo.
	 *
	 * @return the fizetendo
	 */
	public String getFizetendo() {
		return fizetendo;
	}
	
	/**
	 * Sets the fizetendo.
	 *
	 * @param fizetendo the new fizetendo
	 */
	public void setFizetendo(String fizetendo) {
		this.fizetendo = fizetendo;
	}
	
	/**
	 * Gets the fizetett.
	 *
	 * @return the fizetett
	 */
	public String getFizetett() {
		return fizetett;
	}
	
	/**
	 * Sets the fizetett.
	 *
	 * @param fizetett the new fizetett
	 */
	public void setFizetett(String fizetett){
		this.fizetett = fizetett;
	}
}
