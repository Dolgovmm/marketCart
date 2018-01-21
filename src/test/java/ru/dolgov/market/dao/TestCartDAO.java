package ru.dolgov.market.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import junit.framework.Assert;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.jdbc.DbConnection;

public class TestCartDAO {

	@Test
	public void testSaveCart() {
		
		try {
			DbConnection.getConnection().setAutoCommit(false);
			
			CartDAOImpl dao = new CartDAOImpl();
			Cart cart = new Cart();
			
			cart.update(new Product(1, "name", "desc", 100, 200, true), 10);
			
			Client client = new Client();
			client.setId(1);
			client.setName("some name");
			client.setEmail("some email");
			client.setPhoneNumber("phone number");
			cart.setClient(client);
			
			dao.saveCart(cart);
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from cart where id = " + cart.getId() + ";");
			
			int id = -1;
			int clientId = -1;
			int totalItems = -1;
			int productsCost = -1;
			
			if (rs.next()) {
				id = rs.getInt("id");
				clientId = rs.getInt("client_id");
				totalItems = rs.getInt("total_items");
				productsCost = rs.getInt("products_cost");
			}
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertTrue(cart.getId() == id && cart.getClient().getId() == clientId 
					&& cart.getTotalItems() == totalItems && cart.getProductsCost() == productsCost);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCart() {
		
		try {
			
			DbConnection.getConnection().setAutoCommit(false);
			
			CartDAOImpl dao = new CartDAOImpl();
			
			Cart cart = new Cart();
			Product product = new Product(1, "name", "desc", 100, 200, true);
			cart.update(product, 10);
			
			Client client = new Client();
			client.setId(1);
			client.setName("some name");
			client.setEmail("some email");
			client.setPhoneNumber("phone number");
			cart.setClient(client);
			
			dao.saveCart(cart);
			
			cart.update(product, 20);
			
			dao.updateCart(cart);
			
			Cart cartFromDb = new Cart();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from cart;");
			
			if (rs.next()) {
				cartFromDb.setTotalItems(rs.getInt("total_items"));
				cartFromDb.setProductsCost(rs.getInt("products_cost"));
			}
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertEquals(cart, cartFromDb);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
