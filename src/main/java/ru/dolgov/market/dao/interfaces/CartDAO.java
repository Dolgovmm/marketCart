package ru.dolgov.market.dao.interfaces;

import java.sql.SQLException;

import ru.dolgov.market.domain.Cart;

public interface CartDAO {

	public void saveCart(Cart cart) throws SQLException;
	
	public void updateCart(Cart cart) throws SQLException;
}
