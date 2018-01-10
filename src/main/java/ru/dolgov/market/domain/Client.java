package ru.dolgov.market.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "clients")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String phoneNumber;
	private String email;

}
