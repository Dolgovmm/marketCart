package ru.dolgov.market.repository;

import java.sql.SQLException;
import java.util.List;

import ru.dolgov.market.dao.interfaces.CartDAO;
import ru.dolgov.market.dao.interfaces.CartItemDAO;
import ru.dolgov.market.dao.interfaces.ClientDAO;
import ru.dolgov.market.dao.interfaces.ProductDAO;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.Product;

public class RepositoryImpl implements Repository{
	
	private ProductDAO productDAO;
	private ClientDAO clientDAO;
	private CartDAO cartDAO;
	private CartItemDAO cartItemDAO;

	@Override
	public List<Product> getAllProducts() throws SQLException {
		return productDAO.getAllProducts();
	}

	@Override
	public Product getProductById(int id) throws SQLException {
		return productDAO.getProductById(id);
	}

	@Override
	public void saveCart(Cart cart) throws SQLException {
		cartDAO.saveCart(cart);
		
	}

	
}
