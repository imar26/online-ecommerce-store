package com.neu.project.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.project.exception.SellerException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Address;
import com.neu.project.pojo.Email;
import com.neu.project.pojo.Phone;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;

public class SellerDAO extends DAO {
	public SellerDAO() {
		
	}
	
	public Seller get(String username, String password) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("from Seller where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Seller seller = (Seller) q.uniqueResult();
			commit();
			close();
			return seller;
		} catch (HibernateException e) {
			rollback();
			throw new SellerException("Could not get seller " + username, e);
		}
	}
	
	public List<Seller> list() throws SellerException {
        try {
            begin();
            Query q = getSession().createQuery("from Seller");
            List<Seller> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new SellerException("Could not list the sellers", e);
        }
    }
	
	public void activateSeller(int sellerId) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("Update Seller set status = :status where personID = :personID");
			q.setBoolean("status", true);
			q.setInteger("personID", sellerId);
			int res = q.executeUpdate();
			commit();
			close();
		} catch(HibernateException e) {
			rollback();
            throw new SellerException("Could not activate the seller", e);
		}
	}
	
	public void deactivateSeller(int sellerId) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("Update Seller set status = :status where personID = :personID");
			q.setBoolean("status", false);
			q.setInteger("personID", sellerId);
			int res = q.executeUpdate();
			commit();
			close();
		} catch(HibernateException e) {
			rollback();
            throw new SellerException("Could not activate the seller", e);
		}
	}
	
	public Seller get(int sellerId) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("from Seller where personID= :personID");
			q.setInteger("personID", sellerId);		
			Seller seller = (Seller) q.uniqueResult();
			commit();
			return seller;
		} catch (HibernateException e) {
			rollback();
			throw new SellerException("Could not get seller " + sellerId, e);
		}
	}
	public boolean checkIfUsernameExists(String username) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("from Seller where username = :username");
			q.setString("username", username);
			System.out.print(q);
			Seller seller = (Seller) q.uniqueResult();
			System.out.print(seller);
			if(seller == null) {
				return true;
			} else {
				return false;
			}
		} catch(HibernateException e) {
			System.out.println("Username Already Exists!");
		}
		return false;
	}
	
	public boolean checkIfEmailExists(String email) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("from Email where emailAddress = :emailAddress");
			q.setString("emailAddress", email);
			System.out.print(q);
			Email emailID = (Email) q.uniqueResult();
			System.out.print(emailID);
			if(emailID == null) {
				return true;
			} else {
				return false;
			}
		} catch(HibernateException e) {
			System.out.println("Email Already Exists!");
		}
		return false;
	}
	
	public boolean checkIfPhoneNoExists(String phone) throws SellerException {
		try {
			begin();
			Query q = getSession().createQuery("from Phone where phoneNo = :phoneNo");
			q.setString("phoneNo", phone);
			System.out.print(q);
			Phone phoneNo = (Phone) q.uniqueResult();
			System.out.print(phoneNo);
			if(phoneNo == null) {
				return true;
			} else {
				return false;
			}
		} catch(HibernateException e) {
			System.out.println("Phone Number Already Exists!");
		}
		return false;
	}
	public Seller register(Seller s)
			throws SellerException {
		try {
			begin();
			System.out.println("inside Seller DAO");
			
			Phone phone = new Phone(s.getPhone().getPhoneNo());
			Email email = new Email(s.getEmail().getEmailAddress());
			Address address = new Address(s.getAddress().getStreet(), s.getAddress().getCity(), s.getAddress().getState(), s.getAddress().getZip(), s.getAddress().getCountry());
			Seller seller = new Seller(s.getUsername(), s.getPassword());
			System.out.println(s.getCompanyName());
			seller.setFirstName(s.getFirstName());
			seller.setLastName(s.getLastName());
			seller.setCompanyName(s.getCompanyName());
			seller.setStatus(false);
			seller.setPhone(phone);
			seller.setEmail(email);
			System.out.println(s.getCompanyName());
			String addressType = "Office";
			address.setAddressType(addressType);
			//System.out.println(s.getAddress().getAddressType());
			seller.setAddress(address);			
			phone.setSeller(seller);
			email.setSeller(seller);
			address.setSeller(seller);
			getSession().save(seller);
			commit();
			return seller;

		} catch (HibernateException e) {
			rollback();
			throw new SellerException("Exception while creating seller: " + e.getMessage());
		}
	}
	public void delete(Seller seller) throws SellerException {
		try {
			begin();
			getSession().delete(seller);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new SellerException("Could not delete seller " + seller.getUsername(), e);
		}
	}
	
}
