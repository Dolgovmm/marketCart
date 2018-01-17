package ru.dolgov.market.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

//   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private static final String DB_URL = "jdbc:mysql://localhost/market";
   private static final String USER = "user";
   private static final String PASS = "user";
   
   private static Connection connection;
   
   private DbConnection() {
	   
   }
   
   public static Connection getConnection() {
	   
	   if (connection == null) {
		   synchronized(DbConnection.class) { 
			   if (connection == null) {
				   try {
					   connection = DriverManager.getConnection(DB_URL,USER,PASS);
					   System.out.println("create connection");
				   }catch(SQLException ex) {
					   ex.printStackTrace();
				   }
			   }
		   }
	   }
	   return connection;
   }
   
   public static void shutdown() {
	   try {
		   if (connection != null) {
			   connection.close();
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }   
}