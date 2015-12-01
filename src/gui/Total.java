package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.recordtypes.Record;
import datatypes.recordtypes.SzoliRekord;
import datatypes.recordtypes.TermekRekord;


/**
 * Responisble for showing the stat of the day.
 * It has various functions to complete this task, and two JLabels(!)
 * to serve as a graphical surface.
 */
public class Total extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The osszesbevetelfelirat. */
	JLabel osszesbevetelfelirat;
	
	/** The borravalofelirat. */
	JLabel borravalofelirat;
	
	/** The szolirekords. */
	ArrayList<SzoliRekord> sz;
	
	/** The t1.termek 1, from now on using this abbreviation */
	ArrayList<TermekRekord> t1;
	
	/** The t2. */
	ArrayList<TermekRekord> t2;
	
	/** The t3. */
	ArrayList<TermekRekord> t3;
	
	/** The t4. */
	ArrayList<TermekRekord> t4;
	
	/** The t5. */
	ArrayList<TermekRekord> t5;

	/**
	 * Instantiates a new total.
	 */
	public Total(){
		super("Total");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(MANDARIN.APPSIZE.width/4, MANDARIN.APPSIZE.height/4));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(MANDARIN.APPSIZE.width/2, MANDARIN.APPSIZE.height/2);
	}

	/**
	 * Addlists.
	 *
	 * @param sv a list of Lists
	 */
	public void addlists(List<List<? extends Record>> sv){
		sz=(ArrayList<SzoliRekord>) (sv.get(0));
		t1=(ArrayList<TermekRekord>) (sv.get(1));
		t2=(ArrayList<TermekRekord>) (sv.get(2));
		t3=(ArrayList<TermekRekord>) (sv.get(3));
		t4=(ArrayList<TermekRekord>) (sv.get(4));
		t5=(ArrayList<TermekRekord>) (sv.get(5));
	}

	/**
	 * Osszesbevetel.
	 *
	 * @return all the money money money
	 */
	public int osszesbevetel(){
		int accum=0;
		for (SzoliRekord i:sz)
			accum+=Integer.valueOf(i.getFizetett());
		for (TermekRekord i:t1)
			accum+=Integer.valueOf(i.getFizetett());
		for (TermekRekord i:t2)
			accum+=Integer.valueOf(i.getFizetett());
		for (TermekRekord i:t3)
			accum+=Integer.valueOf(i.getFizetett());
		for (TermekRekord i:t4)
			accum+=Integer.valueOf(i.getFizetett());
		for (TermekRekord i:t5)
			accum+=Integer.valueOf(i.getFizetett());
		return accum;
	}

	/**
	 * Bevetel.
	 *
	 * @return The official profit.
	 */
	public int bevetel(){
		int accum=0;
		for (SzoliRekord i:sz)
			accum+=Integer.valueOf(i.getFizetendo());
		for (TermekRekord i:t1)
			accum+=Integer.valueOf(i.getFizetendo());
		for (TermekRekord i:t2)
			accum+=Integer.valueOf(i.getFizetendo());
		for (TermekRekord i:t3)
			accum+=Integer.valueOf(i.getFizetendo());
		for (TermekRekord i:t4)
			accum+=Integer.valueOf(i.getFizetendo());
		for (TermekRekord i:t5)
			accum+=Integer.valueOf(i.getFizetendo());
		return accum;
	}

	/**
	 * Borravalo.
	 *
	 * @return Calulates the tip using the {@link #osszesbevetel()} and {@link #bevetel()}
	 */
	public int borravalo(){
		return osszesbevetel()-bevetel();
	}

	/**
	 * Create and show gui.
	 */
	public void createandShowGui(){
		osszesbevetelfelirat = new JLabel("Összbevétel: "+Integer.toString(osszesbevetel())+" Ft");
		borravalofelirat = new JLabel("Borravaló: "+Integer.toString(borravalo())+ "Ft");
		getContentPane().add(osszesbevetelfelirat, BorderLayout.CENTER);
		getContentPane().add(borravalofelirat, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
