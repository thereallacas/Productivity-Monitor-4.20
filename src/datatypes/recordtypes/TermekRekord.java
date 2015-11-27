/*
 * 
 */
package datatypes.recordtypes;

import java.io.Serializable;

/**
 * The record type for product purchases
 * it also implements the interface serializable
 * and extends the class {@link Record} 
 * It has all the attributes needed to properly record a product purchase
 */
public class TermekRekord extends Record implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1480075798550735050L;
	
	/** The time of the purchase. */
	private String time;
	
	/** The name of the product */
	private String termeknev;
	
	/** The price that has to be paid for the product */
	private String fizetendo;
	
	/** The price paid for the product */
	private String fizetett;
	
	/** The number of attributes in the record type. */
	public static int attributumSzam = 4;
	
	/**
	 * Instantiates a new termek rekord.
	 *
	 * @param time the time
	 * @param termeknev the product name
	 * @param egysegar the unit price (for the attribute fizetendo)
	 * @param fizetett the paid price
	 */
	public TermekRekord(String time, String termeknev, String egysegar, String fizetett){
		setTime(time);setTermeknev(termeknev); setEgysegar(egysegar);setFizetett(fizetett);
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the termeknev.
	 *
	 * @return the termeknev
	 */
	public String getTermeknev() {
		return termeknev;
	}

	/**
	 * Sets the termeknev.
	 *
	 * @param termeknev the new termeknev
	 */
	public void setTermeknev(String termeknev) {
		this.termeknev = termeknev;
	}

	/**
	 * Gets the egysegar.
	 *
	 * @return the egysegar
	 */
	public String getEgysegar() {
		return fizetendo;
	}

	/**
	 * Sets the egysegar.
	 *
	 * @param fizetendo the new egysegar
	 */
	public void setEgysegar(String fizetendo) {
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
	public void setFizetett(String fizetett) {
		this.fizetett = fizetett;
	}
	
}
