package ru.dolgov.market.storage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.CartItem;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;
import ru.dolgov.market.repository.Repository;
import ru.dolgov.market.repository.RepositoryImpl;

public class StorageImpl implements Storage{

	private Repository repository;
	private Map<String, Cart> carts;
	
	public StorageImpl() throws SQLException {
		repository = new RepositoryImpl();
		carts = new HashMap<>();
	}

	@Override
	public List<Product> getAllProducts() throws SQLException {
		return repository.getAllProducts();
	}
	
	@Override
	public Product getProductById(String id) throws NumberFormatException, SQLException {
		return repository.getProductById(Integer.parseInt(id));
	}

	@Override
	public void addProductToCart(String cartId, String productId, String productQuantity) throws NumberFormatException, SQLException {
		Product product = repository.getProductById(Integer.parseInt(productId));
		carts.get(cartId).update(product, Integer.parseInt(productQuantity));
	}

	@Override
	public void removeProductFromCart(String cartId, String productId) throws NumberFormatException, SQLException {
		Product product = repository.getProductById(Integer.parseInt(productId));
		carts.get(cartId).update(product, 0);
	}

	@Override
	public List<CartItem> getProductsFromCart(String id) {
		return carts.get(id).getCartItems();
	}

	@Override
	public void saveCart(String id) throws SQLException {
		repository.saveCart(carts.get(id));
	}

	@Override
	public void createNewCart(String id) {
		carts.put(id, new Cart());
		
	}
	
	@Override
	public void addClientToCart(String cartId, Client client) {
		carts.get(cartId).setClient(client);
	}

}
