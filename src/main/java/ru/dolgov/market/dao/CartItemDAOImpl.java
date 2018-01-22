package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.dao.interfaces.CartItemDAO;
import ru.dolgov.market.domain.CartItem;
import ru.dolgov.market.jdbc.DbConnection;

public class CartItemDAOImpl implements CartItemDAO{
	private final String INSERT_CART_ITEM = "insert into cart_items (cart_id, product_id, quantity) values (?, ?, ?)";
	private final String UPDATE_CART_ITEM = "update cart_items set cart_id = ?, product_id = ?, quantity = ? where id = ?";
	private final String GET_ID_CART_ITEM = "select @@IDENTITY";
	
	public void saveCartItem(CartItem cartItem) throws SQLException {
		Connection connection = null;
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART_ITEM);
			
			preparedStatement.setInt(1, cartItem.getCart().getId());
			preparedStatement.setInt(2, cartItem.getProduct().getId());
			preparedStatement.setInt(3, cartItem.getQuantity());
			preparedStatement.executeUpdate();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(GET_ID_CART_ITEM);
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			cartItem.setId(id);
			
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
	
	public void updateCartItem(CartItem cartItem) throws SQLException {
		Connection connection = null;
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_ITEM);
			
			preparedStatement.setInt(1, cartItem.getCart().getId());
			preparedStatement.setInt(2, cartItem.getProduct().getId());
			preparedStatement.setInt(3, cartItem.getQuantity());
			preparedStatement.setInt(4, cartItem.getId());
			preparedStatement.executeUpdate();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(GET_ID_CART_ITEM);
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			cartItem.setId(id);
			
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
