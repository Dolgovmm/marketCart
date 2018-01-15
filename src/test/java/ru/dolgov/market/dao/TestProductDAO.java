package ru.dolgov.market.dao;

import java.sql.PreparedStatement;
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
			
			ProductDAOImpl dao = new ProductDAOImpl();
			
			PreparedStatement preparedStatement = DbConnection.getConnection()
					.prepareStatement("insert into products (name, description, article, price, available)" 
			+ " values (?, ?, ?, ?, ?)");
			
			for (int i = 0; i < 5; i++) {
				preparedStatement.setString(1, "some name" + i);
				preparedStatement.setString(2, "some description" + i);
				preparedStatement.setInt(3, 1000 + i);
				preparedStatement.setInt(4, 100 + i);
				preparedStatement.setBoolean(5, true);
				preparedStatement.execute();
			}
			
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
			
			PreparedStatement preparedStatement = DbConnection.getConnection()
					.prepareStatement("insert into products (name, description, article, price, available)" 
			+ " values (?, ?, ?, ?, ?)");
			
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setInt(3, product.getArticle());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.setBoolean(5, product.isAvailable());
			preparedStatement.execute();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select @@IDENTITY");
			
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			product.setId(id);
			
			ProductDAOImpl dao = new ProductDAOImpl();
			Product productFromDb = dao.getProductById(id);
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertEquals(product, productFromDb);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
