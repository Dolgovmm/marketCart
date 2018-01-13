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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, 
			targetEntity = CartItem.class, mappedBy = "cart")
	private List<CartItem> cartItems = new LinkedList<>();

	public Cart() {
		this.totalItems = 0;
		this.productsCost = 0;
	}

	public boolean isEmpty() {
		return (totalItems == 0);
	}
	
	public void update(Product product, int quantity) {
        CartItem newItem = new CartItem(this, product, quantity);
        List<CartItem> items = getCartItems();
        if (items.contains(newItem)) {
            int index = items.indexOf(newItem);
            if (quantity > 0) {
                items.get(index).setQuantity(quantity);
            } else {
                items.remove(newItem);
            }
        } else {
            items.add(newItem);
        }
        revalidateCartMetrics();
    }

    public void clear() {
        getCartItems().clear();
        revalidateCartMetrics();
    }

    public void revalidateCartMetrics() {
        int total = 0;
        int cost = 0;
        for (CartItem item : getCartItems()) {
            total += item.getQuantity();
            cost += item.getQuantity() * item.getProduct().getPrice();
        }
        setProductsCost(cost);
        setTotalItems(total);
    }
    
   	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getProductsCost() {
		return productsCost;
	}

	public void setProductsCost(int productsCost) {
		this.productsCost = productsCost;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}  
	
}
