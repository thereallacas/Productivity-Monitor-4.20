package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import datatypes.recordtypes.Record;
import datatypes.recordtypes.SzoliRekord;
import datatypes.recordtypes.TermekRekord;
import gui.Total;


/**
 * Tests whether the most important functions of {@link Total} are working.
 */
public class TotalTest {

	/** The sv. */
	List<List<? extends Record>> sv;
	
	/** The sz. */
	ArrayList<SzoliRekord> sz;
	
	/** The t1. */
	ArrayList<TermekRekord> t1;
	
	/** The t2. */
	ArrayList<TermekRekord> t2;
	
	/** The t3. */
	ArrayList<TermekRekord> t3;
	
	/** The t4. */
	ArrayList<TermekRekord> t4;
	
	/** The t5. */
	ArrayList<TermekRekord> t5;
	
	/** The t. */
	Total t;

	/**
	 * Sets up the test.
	 */
	@Before
	public void SetUpTest(){
		List<List<? extends Record>> sv = new ArrayList<List<? extends Record>>();
		sz = new ArrayList<>();
		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		t3 = new ArrayList<>();
		t4 = new ArrayList<>();
		t5 = new ArrayList<>();
		sz.add(new SzoliRekord("9:11", "5", "Férfi", 5, true, "1000", "1500"));
		t4.add(new TermekRekord("4:20", "kave", "2000", "3000"));
		sv.add(sz);
		sv.add(t1);
		sv.add(t2);
		sv.add(t3);
		sv.add(t4);
		sv.add(t5);
		t = new Total();
		t.addlists(sv);
	}

	/**
	 * Borravalo test.
	 */
	@Test
	public void borravaloTest() {
		Assert.assertEquals(1500,t.borravalo());
	}

	/**
	 * Osszesbevetel test.
	 */
	@Test
	public void osszesbevetelTest(){
		Assert.assertEquals(4500, t.osszesbevetel());
	}
	
	/**
	 * Bevetel test.
	 */
	@Test
	public void bevetelTest(){
		Assert.assertEquals(3000, t.bevetel());
	}
	
	
}
