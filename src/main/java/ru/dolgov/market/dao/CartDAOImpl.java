package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.dao.interfaces.CartDAO;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.jdbc.DbConnection;

public class CartDAOImpl implements CartDAO{
	private final String INSERT_CART = "insert into cart (client_id, total_items, products_cost) values (?, ?, ?)";
	private final String UPDATE_CART = "update cart set client_id = ?, total_items = ?, products_cost = ? where id = ?";
	private final String GET_ID_CART = "select @@IDENTITY";
	
	public void saveCart(Cart cart) throws SQLException {
		
		Connection connection = null;
		try {
			connection = DbConnection.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART);
			
			preparedStatement.setInt(1, cart.getClient().getId());
			preparedStatement.setInt(2, cart.getTotalItems());
			preparedStatement.setInt(3, cart.getProductsCost());
			preparedStatement.executeUpdate();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(GET_ID_CART);
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			cart.setId(id);
			
			rs.close();
			statement.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			if (connection != null) {
				try {
					connection.close();
				}catch (Exception ignore) {}
			}
		}		
	}
	
	public void updateCart(Cart cart) throws SQLException {
		
		Connection connection = null;
		try {
			
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART);
			
			preparedStatement.setInt(1, cart.getClient().getId());
			preparedStatement.setInt(2, cart.getTotalItems());
			preparedStatement.setInt(3, cart.getProductsCost());
			preparedStatement.setInt(4, cart.getId());
			preparedStatement.executeUpdate();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(GET_ID_CART);
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			cart.setId(id);
			
			rs.close();
			statement.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			if (connection != null) {
				try {
					connection.close();
				}catch (Exception ignore) {}
			}
		}			
	}
}
