package ru.dolgov.market.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotNull
	private String name;
	
	@Column(name = "phone_number", nullable = false)
	@NotNull
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
 
        Client client = (Client) other;
 
        if (client.getId() != null) {
			if (!this.getId().equals(client.getId())) {
				return false;
			}
		}
        return true;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return (int) (31 * getId());
		}
		return 0;
	}
	
}
