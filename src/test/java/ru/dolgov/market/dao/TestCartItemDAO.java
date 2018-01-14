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
			
			CartItemDAO dao = new CartItemDAO();
			Cart cart = new Cart();
			cart.update(new Product(1, "name", "desc", 100, 200, true), 10);
			
			dao.saveCartItem(cart.getCartItems().get(0));
			
			CartItem cartItemFromDb = new CartItem();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from cart_items;");
			
			if (rs.next()) {
				cartItemFromDb.setCart(rs.getObject("cart_id", Cart.class));
				cartItemFromDb.setProduct(rs.getObject("product_id", Product.class));
				cartItemFromDb.setQuantity(rs.getInt("quantity"));
			}
			
			DbConnection.getConnection().rollback();
			
			Assert.assertEquals(cart.getCartItems().get(0), cartItemFromDb);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
