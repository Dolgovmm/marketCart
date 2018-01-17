package ru.dolgov.market.repository;

import java.sql.SQLException;
import java.util.List;

import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.Product;

public interface Repository {

	public List<Product> getAllProducts() throws SQLException;
	
	public Product getProductById(int id) throws SQLException;
	
	public void saveCart(Cart cart) throws SQLException;
}
