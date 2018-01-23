package ru.dolgov.market.domain;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer article;

	private String name;

	private String description;

	private Integer price;

	private boolean available;
	
	public Product() {
		
	}
	
	public Product(int id, String name, String description, int article, int price, boolean available) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setArticle(article);
		this.setPrice(price);
		this.setAvailable(available);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Product product = (Product)other;
		if (product.getArticle() != null) {
			if (!this.getArticle().equals(product.getArticle())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		if (getArticle() != null) {
			return 31 * getArticle();
		}
		return 0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
	}

}
