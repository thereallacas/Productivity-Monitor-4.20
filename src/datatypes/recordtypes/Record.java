/*
 * 
 */
package datatypes.recordtypes;

import java.io.Serializable;
import java.sql.DatabaseMetaData;

/**
 * Serves as a supertype for the various 
 * record types the database has. Also it implements
 * the interface Serializable for the list to be good 
 * for consume by the {@link database.Serializer}
 */
public class Record implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
}
