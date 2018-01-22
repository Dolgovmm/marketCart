package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.dolgov.market.dao.interfaces.ProductDAO;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.jdbc.DbConnection;

public class ProductDAOImpl implements ProductDAO{
	
	private final String GET_ALL_PRODUCTS = "select * from products;";
	private final String GET_PRODUCT_BY_ID = "select * from products where id = ?";
	
	public List<Product> getAllProducts() throws SQLException {
		
		Connection connection = null;
		
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);

			ResultSet rs = preparedStatement.executeQuery();
			
			List<Product> products = new ArrayList<>();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int article = rs.getInt("article");
				int price = rs.getInt("price");
				boolean available = rs.getBoolean("available");
				Product product = new Product(id, name, description, article, price, available);
				products.add(product);
			}
			
			rs.close();
			preparedStatement.close();
			
			return products;
			
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
	
	public Product getProductById(int id) throws SQLException {
		
		Connection connection = null;
		
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
			
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			Product product = new Product();
		
			if (rs.next()) {;
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setArticle(rs.getInt("article"));
				product.setPrice(rs.getInt("price"));
				product.setAvailable(rs.getBoolean("available"));
			}
			
			rs.close();
			preparedStatement.close();
			
			return product;
			
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
