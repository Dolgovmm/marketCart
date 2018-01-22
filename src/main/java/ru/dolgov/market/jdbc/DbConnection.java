package ru.dolgov.market.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DbConnection {

   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
   private static final String DB_URL = "jdbc:mysql://localhost/market?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   private static final String USER = "user";
   private static final String PASS = "user";
   
   private static DataSource datasource;
   
   private DbConnection() {
	   
   }
   
   public static Connection getConnection() throws SQLException {
	   
	   if (datasource == null) {
		   synchronized(DbConnection.class) { 
			   if (datasource == null) {
				   init();
			   }
		   }
	   }
		return datasource.getConnection();
   }
   
   private static void init() {
	   PoolProperties p = new PoolProperties();
       p.setUrl(DB_URL);
       p.setDriverClassName(JDBC_DRIVER);
       p.setUsername(USER);
       p.setPassword(PASS);
       p.setJmxEnabled(true);
       p.setTestWhileIdle(false);
       p.setTestOnBorrow(true);
       p.setValidationQuery("SELECT 1");
       p.setTestOnReturn(false);
       p.setValidationInterval(30000);
       p.setTimeBetweenEvictionRunsMillis(30000);
       p.setMaxActive(100);
       p.setInitialSize(10);
       p.setMaxWait(10000);
       p.setRemoveAbandonedTimeout(60);
       p.setMinEvictableIdleTimeMillis(30000);
       p.setMinIdle(10);
       p.setLogAbandoned(true);
       p.setRemoveAbandoned(true);
       p.setJdbcInterceptors(
         "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
         "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
       datasource = new DataSource();
       datasource.setPoolProperties(p);
   }
    
}