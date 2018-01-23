package ru.dolgov.market.domain;

import java.io.Serializable;

public class CartItem implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Cart cart;

	private Product product;

	private Integer quantity;

	public CartItem() {
		
	}
	
	public CartItem(Cart cart, Product product, Integer quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		CartItem cartItem = (CartItem)other;
		if (cartItem.getCart() != null && getProduct() != null) {
			if (!this.getCart().equals(cartItem.getCart()) || !this.getProduct().equals(cartItem.getProduct())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		if (getCart() != null && getProduct() != null) {
			return 31 * getCart().hashCode() + getProduct().hashCode();
		}
		return 0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
		
}
