package database;

import java.sql.*;

public class sql_manager {
	public void createdatabase(){
		Connection c = null;
		Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:szoli.db");
	      
	    
	      stmt = c.createStatement();
	      String sql = "CREATE TABLE SZOLI " +
	                   "(TIME INT PRIMARY KEY     	NOT NULL," +
	                   " GEP           	INT    , " + 
	                   " F_N            BOOLEAN     NOT NULL, " + 
	                   " BERLET			BOOLEAN" +
	                   " MINUTE        	INT, " + 
	                   " FIZETENDO		INT"	+	
	                   " FIZETETT      	INT)"; 
	      stmt.executeUpdate(sql);
	      String[] tablenames = {"UDITO","KAVE", "BERLET", "KREM"};
	      for(String i:tablenames){
	      sql = "CREATE TABLE " + i +
                  " (TIME INT	PRIMARY KEY	NOT NULL," +
                  "FIZETENDO	INT			NOT NULL, " + 
                  "FIZETETT		REAL)"; 
	      stmt.executeUpdate(sql);
	      }
	      
	     
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    System.out.println("Opened database successfully");
	  }
	};
