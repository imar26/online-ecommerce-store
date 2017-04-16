package com.neu.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.project.exception.AdminException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Admin;
import com.neu.project.pojo.User;

public class AdminDAO extends DAO {
	public AdminDAO() {
		
	}
	
	public Admin get(String username, String password) throws AdminException {
		try {
			begin();
			Query q = getSession().createQuery("from Admin where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Admin admin = (Admin) q.uniqueResult();
			commit();
			close();
			return admin;
		} catch (HibernateException e) {
			rollback();
			throw new AdminException("Could not get admin " + username, e);
		}
	}
}
