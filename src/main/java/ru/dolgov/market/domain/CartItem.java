package ru.dolgov.market.domain;

import java.io.Serializable;

public class CartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cart cart;
	private Product product;
	private Integer quantity;

}
