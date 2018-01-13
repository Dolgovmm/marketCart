package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.dolgov.market.domain.Product;
import ru.dolgov.market.jdbc.DbConnection;

public class ProductDAO {
	private final String GET_ALL_PRODUCTS = "select * from market.products;";
	private final String GET_PRODUCT_BY_ID = "select * form market.products where id = ";
	
	public List<Product> getAllProducts() throws SQLException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_ALL_PRODUCTS);
		
		List<Product> products = new ArrayList<>();
		
		while(rs.next()) {
			Long id = rs.getLong("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			int article = rs.getInt("article");
			int price = rs.getInt("price");
			boolean available = rs.getBoolean("availible");
			Product product = new Product(id, name, description, article, price, available);
			products.add(product);
		}
		
		connection.close();
		
		return products;
		
	}
	
	public Product getProductById(Long id) throws SQLException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_PRODUCT_BY_ID + id.toString() + ";");
		
		Product product = new Product();
	
		if (rs.next()) {
			product.setId(rs.getLong("id"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setArticle(rs.getInt("article"));
			product.setPrice(rs.getInt("price"));
			product.setAvailible(rs.getBoolean("availible"));
		}
		
		connection.close();
		
		return product;
	}

}
