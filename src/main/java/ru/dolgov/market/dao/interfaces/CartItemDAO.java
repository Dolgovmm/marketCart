package ru.dolgov.market.dao.interfaces;

import java.sql.SQLException;

import ru.dolgov.market.domain.CartItem;

public interface CartItemDAO {

	public void saveCartItem(CartItem cartItem) throws SQLException;
	
	public void updateCartItem(CartItem cartItem) throws SQLException;	
}
