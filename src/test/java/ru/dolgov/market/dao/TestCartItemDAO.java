package ru.dolgov.market.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import junit.framework.Assert;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.CartItem;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.jdbc.DbConnection;

public class TestCartItemDAO {
	
	@Test
	public void testSaveCartItem() {
		try {
			DbConnection.getConnection().setAutoCommit(false);
			
			CartItemDAOImpl dao = new CartItemDAOImpl();
			Cart cart = new Cart();
			cart.update(new Product(1, "name", "desc", 100, 200, true), 10);
			cart.setId(1);
			
			CartItem cartItem = cart.getCartItems().get(0);
			
			dao.saveCartItem(cartItem);
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement
					.executeQuery("select * from cart_items limit 1;");

			int id = -1;
			int cartId = -1;
			int productId = -1;
			int quantity = -1;
			
			if (rs.next()) {
				id = rs.getInt("id");
				cartId = rs.getInt("cart_id");
				productId = rs.getInt("product_id");
				quantity = rs.getInt("quantity");
			}
			
			DbConnection.getConnection().rollback();
			
			Assert.assertTrue(cartItem.getId() == id & cartItem.getCart().getId() == cartId 
					& cartItem.getProduct().getId() == productId & cartItem.getQuantity() == quantity);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
