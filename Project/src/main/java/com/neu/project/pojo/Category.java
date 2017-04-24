package com.neu.project.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="category_table")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "categoryID", unique=true, nullable = false)
	private long categoryID;
	
	@Column(name="categoryName")
	private String categoryName;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="category_product_table",
			joinColumns = {@JoinColumn(name="categoryID",nullable=false,updatable= false)},
			inverseJoinColumns = {@JoinColumn(name="productID")}
	)
	private Set<Product> products = new HashSet<Product>();
	
	public Category() {
		
	}
	
	public Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return categoryName;
	}
}
