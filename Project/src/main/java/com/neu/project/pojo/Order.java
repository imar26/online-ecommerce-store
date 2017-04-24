package com.neu.project.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="order_table")
public class Order implements Serializable {
	@Id
    @ManyToOne
    @JoinColumn(name="userID")
    private User user;
	
	@Id
    @ManyToOne
    @JoinColumn(name="productID")
    private Product products;
	
	@Column(name="quantity")
    private int quantity;
	
	@Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date date;
	
	@Id
    @Column(name="orderID")
    private int orderID;
	
	public Order() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return products;
	}

	public void setProduct(Product products) {
		this.products = products;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
		
}
