/*
 * 
 */
package test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import datatypes.recordtypes.SzoliRekord;
import datatypes.recordtypes.TermekRekord;
import tablemodels.SzoliTablaModell;


/**
 * Tests the crucial parts of the {@link SzoliTablaModell}
 */
public class SzoliTablaModellTest {
	
	/** The szt. */
	SzoliTablaModell szt = new SzoliTablaModell();
	
	/** The szolirekordoktest. */
	List<SzoliRekord> szolirekordoktest;
	
	/** The szolirekordoktest2. */
	List<SzoliRekord> szolirekordoktest2;
	
	/** The termekrekordoktest3. */
	List<TermekRekord> termekrekordoktest3; 

	/**
	 * Sets up the test.
	 */
	@Before
	public void setUp() {
		szolirekordoktest = new ArrayList<>();
		szolirekordoktest2 = new ArrayList<>();
		termekrekordoktest3 = new ArrayList<TermekRekord>();
		szt.setSzoliRekordok(szolirekordoktest);
	} 

	/**
	 * Gets the row count test.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	@Test 
	public void getRowCountTest() throws IOException, ClassNotFoundException {
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		Assert.assertEquals(4,szt.getRowCount());

		/////////Adjunk neki termeklistat ClassCastException dobas
		/////setvalueat
	}

	/**
	 * Sets the value at test.
	 */
	@Test
	public void setValueAtTest(){
		szt.setSzoliRekordok(szolirekordoktest2);
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.addSzoliRekord("4:20", "4", "Férfi", 5, true, "4500", "6000");
		szt.setValueAt(8, 3, 3);
		Assert.assertEquals(8, szt.getValueAt(3, 3));
	}
	
	/**
	 * Wrong list test.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected=ClassCastException.class)
	public void wrongListTest() throws Exception {
		termekrekordoktest3.add(new TermekRekord("9:11", "kocka", "400", "500"));
		szt.setSzoliRekordok(termekrekordoktest3);
	} 
	

	
}
