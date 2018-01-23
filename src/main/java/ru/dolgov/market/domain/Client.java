package ru.dolgov.market.domain;

import java.io.Serializable;

public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String phoneNumber;

	private String email;
	
	public Client() {
		this.name = "";
		this.email = "";
		this.phoneNumber = "";
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
 
        if (client.getEmail() != null & this.getEmail() != null) {
			if (!this.getEmail().equals(client.getEmail())) {
				return false;
			}
		}
        if (client.getName() != null & this.getName() != null) {
			if (!this.getName().equals(client.getName())) {
				return false;
			}
		}
        if (client.getPhoneNumber() != null & this.getPhoneNumber() != null) {
			if (!this.getPhoneNumber().equals(client.getPhoneNumber())) {
				return false;
			}
		}
        return true;
	}

	@Override
	public int hashCode() {
		if (getId() != null) {
			return (int) (getName().hashCode() + getEmail().hashCode() + getPhoneNumber().hashCode());
		}
		return 0;
	}
	
}
