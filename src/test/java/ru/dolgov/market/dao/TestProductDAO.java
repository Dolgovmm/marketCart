package ru.dolgov.market.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ru.dolgov.market.domain.Product;
import ru.dolgov.market.jdbc.DbConnection;

public class TestProductDAO {
	
	@Test
	public void testGetAllProducts() {
		
		try {
			
			DbConnection.getConnection().setAutoCommit(false);
			
			ProductDAO dao = new ProductDAO();
			
			Statement statement = DbConnection.getConnection().createStatement();
			statement.execute("insert into products (name, description, article, price, available) " 
			+ "values ('some Name', 'some description', 12345, 23456, true)");
			statement.execute("insert into products (name, description, article, price, available) "
			+ "values ('some Name2', 'some description2', 123456, 1234567, false)");			
			
			List<Product> products = dao.getAllProducts();
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertNotNull(products);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetProductById() {
		
		try {
			
			DbConnection.getConnection().setAutoCommit(false);
			
			Product product = new Product(1, "some name", "some desc", 123456, 100, true);
			
			Statement statement = DbConnection.getConnection().createStatement();
			statement.execute("insert into products (name, description, article, price, available)"
			+ " values ('" + product.getName() + "', '" + product.getDescription() + "', " + product.getArticle()
			+ ", " + product.getPrice() + ", " + product.isAvailable() + ")");
			ResultSet rs = statement.executeQuery("select @@IDENTITY");
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			product.setId(id);
			
			ProductDAO dao = new ProductDAO();
			Product productFromDb = dao.getProductById(id);
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertEquals(product, productFromDb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
