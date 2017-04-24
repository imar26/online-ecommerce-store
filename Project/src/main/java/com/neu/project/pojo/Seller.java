package com.neu.project.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="seller_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Seller extends Person {
	
	@Column(name="companyName")
	private String companyName;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private Boolean status;
	
	@OneToOne(mappedBy = "seller" , cascade = CascadeType.ALL)
	private Email email;

	@OneToOne(mappedBy = "seller" , cascade = CascadeType.ALL)
	private Phone phone;
	
	@OneToOne(mappedBy = "seller" , cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
	private List<Product> product = new ArrayList<Product>();
	
	public Seller(String companyName) {
		this.companyName = companyName;
	}
	
	public Seller() {
		
	}
	
	public Seller(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}	
	
}
