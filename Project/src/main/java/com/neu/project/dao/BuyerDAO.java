package com.neu.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.project.exception.BuyerException;
import com.neu.project.exception.ProductException;
import com.neu.project.exception.SellerException;
import com.neu.project.pojo.Cart;
import com.neu.project.pojo.Product;
import com.neu.project.pojo.User;

public class BuyerDAO extends DAO {
	public BuyerDAO() {
		
	}
	
	public List<Product> list(int userId) throws ProductException {
        try {
            begin();
            Query q = getSession().createQuery("from Cart where userID = :userId");
            q.setInteger("userId", userId);
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not list the products", e);
        }
    }
	
	public Boolean checkCart(Long productId, Long userId) throws BuyerException {
		try {	
			System.out.println("checkCart");
			begin();
			Query query = getSession().createQuery("from Cart where userID =:userId AND productID =:productId");
			query.setLong("userId", userId);
			query.setLong("productId", productId);
			List res = query.list();
			System.out.println(res);
			if (res.size() == 0) {
				return true;
			} else {
				return false;
			}
		} catch (HibernateException e) {
			System.out.println("Exception it is"+e);
			rollback();
			throw new BuyerException("Error while checking cart: " + e.getMessage());
		}
	}
	
	public Cart getQuantity(Long productId, Long userId) throws BuyerException {
		try {		
			System.out.println("getQuantity");
			begin();
			Query query = getSession().createQuery("from Cart where userID =:userId AND productID =:productId");
			query.setLong("userId", userId);
			query.setLong("productId", productId);
			Cart cart = (Cart)query.uniqueResult();
			System.out.println("Cart: "+cart);
			//commit();
			close();
			return cart;
		} catch (HibernateException e) {
			System.out.println("Quantity Exception it is: "+e);
			rollback();
			throw new BuyerException("Error while getting quantity: " + e.getMessage());
		}
	}
	
	public void updateCart(int qty_cart, Long productId, Long userId) throws BuyerException {
		try {		
			System.out.println("updateCart");
			begin();
			Query query = getSession().createQuery("Update Cart set quantity =:qty_cart where userID =:userId AND productID =:productId");
			query.setLong("userId", userId);
			query.setLong("productId", productId);
			query.setInteger("qty_cart", qty_cart);
			int res = query.executeUpdate();
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new BuyerException("Error while updating cart: " + e.getMessage());
		}
	}
	
	public void insertIntoCart(int qty_cart, Product productId, User userId) throws BuyerException {
		try {				
			begin();
			Cart cart = new Cart();
			cart.setQuantity(qty_cart);
			cart.setProduct(productId);
			cart.setUser(userId);
			getSession().save(cart);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new BuyerException("Error while adding cart: " + e.getMessage());
		}
	}
	
	public void deleteProduct(int productId, Long userId) throws ProductException {
		try {
			begin();
			Query q = getSession().createQuery("delete Cart where productID = :productId AND userID = :userId");
			q.setInteger("productId", productId);
			q.setLong("userId", userId);
			int res = q.executeUpdate();
			commit();
			close();
		} catch(HibernateException e) {
			rollback();
            throw new ProductException("Could not delete the product", e);
		}
	}
}
