package com.neu.project.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userID", unique=true, nullable = false)
	private long userID;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="emailAddress")
	private String emailAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyerID", nullable = true)
	private Buyer buyerID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerID", nullable = true)
	private Seller sellerID;
	
	public User() {
		
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
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

	public Buyer getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(Buyer buyerID) {
		this.buyerID = buyerID;
	}

	public Seller getSellerID() {
		return sellerID;
	}

	public void setSellerID(Seller sellerID) {
		this.sellerID = sellerID;
	}
	
	
}
