package ru.dolgov.market.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;
import ru.dolgov.market.domain.Product;

public class TestProductDAO {
	
	@Test
	public void testGetAllProducts() throws SQLException {
		ProductDAO dao = new ProductDAO();
		List<Product> products = dao.getAllProducts();
		Assert.assertNotNull(products);
	}
	
	@Test
	public void testGetProductById() throws SQLException {
		ProductDAO dao = new ProductDAO();
		Product product = dao.getProductById(1l);
		Assert.assertNotNull(product);
	}

}
