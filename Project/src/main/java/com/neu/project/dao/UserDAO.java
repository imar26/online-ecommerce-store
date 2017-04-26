package com.neu.project.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.project.exception.UserException;
import com.neu.project.pojo.Address;
import com.neu.project.pojo.Email;
import com.neu.project.pojo.Phone;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;

public class UserDAO extends DAO {
	public UserDAO() {
		
	}
	
	public User get(String username, String password) throws UserException {
		try {
			begin();
//			Criteria criteria = getSession().createCriteria(User.class);
//			criteria.add(Restrictions.eq("username", username));
//			criteria.add(Restrictions.eq("password", password));
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setInteger("personID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	public boolean checkIfUsernameExists(String username) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
			System.out.print(q);
			User user = (User) q.uniqueResult();
			System.out.print(user);
			if(user == null) {
				return true;
			} else {
				return false;
			}
		} catch(HibernateException e) {
			System.out.println("Username Already Exists!");
		}
		return false;
	}
	
	public boolean checkIfEmailExists(String email) throws UserException {
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
	
	public boolean checkIfPhoneNoExists(String phone) throws UserException {
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
	
	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");
			
			
			
			Phone phone = new Phone(u.getPhone().getPhoneNo());
			Email email = new Email(u.getEmail().getEmailAddress());
			//List<Address> address = new ArrayList<Address>();
			User user = new User(u.getUsername(), u.getPassword());
			List<Address> address = u.getAddress();
//			Address add = new Address();
//			add.se
			String at1 = "Billing";
			String at2 = "Shipping";
			int i = 0;
			Iterator<Address> itr = address.iterator();
			while(itr.hasNext()) {
				Address add = new Address();
				add = itr.next();
				if (i == 0) {
					add.setAddressType(at1);
				} else {
					add.setAddressType(at2);
				}
				//System.out.println(add.getStreet());
				//Address add1 = address.get(0);
				//add.setAddressType(add1.getAddressType());
				add.setUser(user);
				i++;
			     //getSession().save(add);
			}

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			user.setPhone(phone);			
			email.setUser(user);
			phone.setUser(user);
			user.setAddress(address);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}	
	
	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}
}
