package ru.dolgov.market.dao;

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
	
	private PreparedStatement preparedStatementInsertCart;
	private PreparedStatement preparedStatementUpdateCart;
	
	public void saveCartItem(CartItem cartItem) throws SQLException {
		
		preparedStatementInsertCart.setInt(1, cartItem.getCart().getId());
		preparedStatementInsertCart.setInt(2, cartItem.getProduct().getId());
		preparedStatementInsertCart.setInt(3, cartItem.getQuantity());
		preparedStatementInsertCart.executeUpdate();
		
		Statement statement = DbConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(GET_ID_CART_ITEM);
		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		cartItem.setId(id);
	}
	
	public void updateCartItem(CartItem cartItem) throws SQLException {
		preparedStatementUpdateCart.setInt(1, cartItem.getCart().getId());
		preparedStatementUpdateCart.setInt(2, cartItem.getProduct().getId());
		preparedStatementUpdateCart.setInt(3, cartItem.getQuantity());
		preparedStatementUpdateCart.setInt(4, cartItem.getId());
		preparedStatementUpdateCart.executeUpdate();
	}
	
	
	public CartItemDAOImpl() throws SQLException {
		preparedStatementInsertCart = DbConnection.getConnection().prepareStatement(INSERT_CART_ITEM);
		preparedStatementUpdateCart = DbConnection.getConnection().prepareStatement(UPDATE_CART_ITEM);
	}

}
