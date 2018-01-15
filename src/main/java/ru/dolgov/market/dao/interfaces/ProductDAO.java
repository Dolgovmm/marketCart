package ru.dolgov.market.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import ru.dolgov.market.domain.Product;

public interface ProductDAO {

	public List<Product> getAllProducts() throws SQLException;
	
	public Product getProductById(int id) throws SQLException;
}
