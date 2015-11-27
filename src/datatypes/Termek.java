/*
 * 
 */
package datatypes;


/**
 * This type is created for the price list elements.
 * The program uses the price list to assign prices to 
 * the various products the database has.
 * @see inputpanels.TermekInputPanel for the price list read process.
 */
public class Termek {
	
	/**
	 * Instantiates a new Termek product.
	 *
	 * @param termeknev the name of the product
	 * @param egysegar the the unit price of the product
	 */
	public Termek(String termeknev, String egysegar){
	this.termeknev = termeknev;
	this.egysegar=egysegar;
	}
	
	/** The name */
	public String termeknev;
	
	/** The price unit. */
	public String egysegar;
}
