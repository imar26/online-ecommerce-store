package com.neu.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "phone_table")
public class Phone {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="phoneID", unique=true,nullable = false)
	private long phoneID;
	
	@Column(name="phoneNo")
	private String phoneNo;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Seller seller;
	
	public Phone() {
		
	}
	
	public Phone(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getPhoneID() {
		return phoneID;
	}

	public void setPhoneID(long phoneID) {
		this.phoneID = phoneID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
}
