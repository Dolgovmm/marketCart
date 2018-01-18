package ru.dolgov.market.repository;

import java.sql.SQLException;
import java.util.List;

import ru.dolgov.market.dao.CartDAOImpl;
import ru.dolgov.market.dao.CartItemDAOImpl;
import ru.dolgov.market.dao.ClientDAOImpl;
import ru.dolgov.market.dao.ProductDAOImpl;
import ru.dolgov.market.dao.interfaces.CartDAO;
import ru.dolgov.market.dao.interfaces.CartItemDAO;
import ru.dolgov.market.dao.interfaces.ClientDAO;
import ru.dolgov.market.dao.interfaces.ProductDAO;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.CartItem;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;

public class RepositoryImpl implements Repository{
	
	private ProductDAO productDAO;
	private ClientDAO clientDAO;
	private CartDAO cartDAO;
	private CartItemDAO cartItemDAO;

	public RepositoryImpl() throws SQLException {
		productDAO = new ProductDAOImpl();
		clientDAO = new ClientDAOImpl();
		cartDAO = new CartDAOImpl();
		cartItemDAO = new CartItemDAOImpl();
	}
	
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
		Client clientFromDb = clientDAO.getClientByEmail(cart.getClient().getEmail());
		if (!clientFromDb.equals(cart.getClient())) {
			clientDAO.saveClient(cart.getClient());
		}else {
			cart.getClient().setId(clientFromDb.getId());
		}
		cartDAO.saveCart(cart);
		for (CartItem cartItem : cart.getCartItems()) {
			cartItemDAO.saveCartItem(cartItem);
		}
	}

	
}
