package ru.dolgov.market.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", insertable = false, updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "total_items")
	private int totalItems;
	
	@Column(name = "product_cost")
	private int productsCost;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, 
			targetEntity = CartItem.class, mappedBy = "cart")
	private List<CartItem> cartItems = new LinkedList<>();

	public Cart() {
		this.totalItems = 0;
		this.productsCost = 0;
	}

	public boolean isEmpty() {
		return (totalItems == 0);
	}
	
}
