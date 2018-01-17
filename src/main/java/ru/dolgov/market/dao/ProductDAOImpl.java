package ru.dolgov.market.dao;

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
	
	PreparedStatement preparedStatementGetAll;
	PreparedStatement preparedStatementGetById;
	
	public ProductDAOImpl() throws SQLException {
		preparedStatementGetAll = DbConnection.getConnection().prepareStatement(GET_ALL_PRODUCTS);
		preparedStatementGetById = DbConnection.getConnection().prepareStatement(GET_PRODUCT_BY_ID);
	}
	
	public List<Product> getAllProducts() throws SQLException {
		
		ResultSet rs = preparedStatementGetAll.executeQuery();
		
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
		
		return products;
		
	}
	
	public Product getProductById(int id) throws SQLException {
		
		preparedStatementGetById.setInt(1, id);
		ResultSet rs = preparedStatementGetById.executeQuery();
		
		Product product = new Product();
	
		if (rs.next()) {;
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));
			product.setArticle(rs.getInt("article"));
			product.setPrice(rs.getInt("price"));
			product.setAvailable(rs.getBoolean("available"));
		}
		
		return product;
	}
}
