package ru.dolgov.market.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "article")
	@NotNull
	private Integer article;
	
	@Column(name = "name", nullable = false)
	@NotNull
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price", nullable = false)
	@NotNull
	private Integer price;
	
	@Column(name = "availible")
	private boolean availible;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public boolean isAvailible() {
		return availible;
	}

	public void setAvailible(boolean availible) {
		this.availible = availible;
	}
	
	public Integer getArticle() {
		return article;
	}

	public void setArticle(Integer article) {
		this.article = article;
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
		return false;
	}

	@Override
	public int hashCode() {
		if (getArticle() != null) {
			return 31 * getArticle();
		}
		return 0;
	}
	

}
