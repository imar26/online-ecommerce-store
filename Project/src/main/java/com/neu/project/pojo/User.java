package com.neu.project.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_table")
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	private Email email;
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	private Phone phone;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<Address>();
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Cart> cart = new ArrayList<Cart>();
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User() {
		List<Address> address = new ArrayList<Address>();
		address.add(new Address());
		address.add(new Address());
	}
	
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
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

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}	
	
	public void addAddress(Address add) {
		address.add(add);
		add.setUser(this);
	}
	
	public void removeAddress(Address add) {
		address.remove(add);
		add.setUser(null);
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	
}
