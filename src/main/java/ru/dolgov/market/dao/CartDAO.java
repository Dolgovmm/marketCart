package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.jdbc.DbConnection;

public class CartDAO {
	private final String INSERT_CART = "insert";
	
	public void saveCart(Cart cart) throws SQLException {
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		
		
		
		connection.close();
		
	}
	
	public void removeCart(Cart cart) {
		
	}
	
}
