package com.neu.project.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "product_table")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "productID", unique=true, nullable = false)
	private long productID;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productDesc")
	private String productDesc;
	
	@Column(name="productPrice")
	private String productPrice;
	
	@Column(name="productQuantity")
	private long productQuantity;
	
	@Transient
	private CommonsMultipartFile photo;
	
	@Column(name = "fileName")
	private String fileName;
	
	@ManyToOne(fetch=FetchType.LAZY)
    private Seller seller;
	
	@ManyToMany(mappedBy="products")
	private Set<Category> categories = new HashSet<Category>();
	
	@OneToMany(mappedBy = "products")
    private List<Cart> cart = new ArrayList<Cart>();
	
	@OneToMany(mappedBy = "products")
    private List<Order> order = new ArrayList<Order>();
	
	public Product(String productName, String productDesc, String productPrice, long productQuantity) {
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}
	
	public Product() {
		
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
}
