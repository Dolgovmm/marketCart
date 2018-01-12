package ru.dolgov.market.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cart cart;
	
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Product product;
	
	@Column(name = "quantity")
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
