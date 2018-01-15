package ru.dolgov.market.dao;

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
	
	private PreparedStatement preparedStatementInsertCart;
	private PreparedStatement preparedStatementUpdateCart;
	
	public void saveCart(Cart cart) throws SQLException {
		
		preparedStatementInsertCart.setInt(1, cart.getClient().getId());
		preparedStatementInsertCart.setInt(2, cart.getTotalItems());
		preparedStatementInsertCart.setInt(3, cart.getProductsCost());
		preparedStatementInsertCart.executeUpdate();
		
		Statement statement = DbConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(GET_ID_CART);
		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		cart.setId(id);
	}
	
	public void removeCart(Cart cart) {
		
	}
	
	public void updateCart(Cart cart) throws SQLException {
		
		preparedStatementUpdateCart.setInt(1, cart.getClient().getId());
		preparedStatementUpdateCart.setInt(2, cart.getTotalItems());
		preparedStatementUpdateCart.setInt(3, cart.getProductsCost());
		preparedStatementUpdateCart.setInt(4, cart.getId());
		preparedStatementUpdateCart.executeUpdate();
		
	}
	
	public CartDAOImpl() throws SQLException {
		preparedStatementInsertCart = DbConnection.getConnection().prepareStatement(INSERT_CART);
		preparedStatementUpdateCart = DbConnection.getConnection().prepareStatement(UPDATE_CART);
	}
	
}
