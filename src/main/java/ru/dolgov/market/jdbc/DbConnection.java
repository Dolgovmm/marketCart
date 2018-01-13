package ru.dolgov.market.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

   //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/market";
   static final String USER = "user";
   static final String PASS = "user";
   
   private static Connection connection;
   
   private DbConnection() {
	   
   }
   
   public static Connection getConnection() {
	   
	   if (connection == null) {
		   synchronized(DbConnection.class) { 
			   if (connection == null) {
				   try {
					   connection = DriverManager.getConnection(DB_URL,USER,PASS);
				   }catch(SQLException ex) {
					   ex.printStackTrace();
				   }
			   }
		   }
	   }
	   return connection;
   }
	   
}