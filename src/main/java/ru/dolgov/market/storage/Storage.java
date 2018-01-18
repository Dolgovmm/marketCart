package ru.dolgov.market.storage;

import java.sql.SQLException;
import java.util.List;

import ru.dolgov.market.domain.CartItem;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.domain.Product;

public interface Storage {

	public List<Product> getAllProducts() throws SQLException;
	
	public Product getProductById(String id) throws NumberFormatException, SQLException;
	
	public void addProductToCart(String cartId, String productId, int productQuantity) throws NumberFormatException, SQLException;
	
	public void removeProductFromCart(String cartId, String productId) throws NumberFormatException, SQLException;
	
	public List<CartItem> getProductsFromCart(String id);
	
	public void saveCart(String cartId) throws SQLException;
	
	public void createNewCart(String id);
	
	public void addClientToCart(String cartId, Client client);
}
