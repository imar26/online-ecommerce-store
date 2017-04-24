package com.neu.project.dao;

import java.util.List;

import javax.persistence.Transient;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.neu.project.exception.BuyerException;
import com.neu.project.exception.CategoryException;
import com.neu.project.exception.ProductException;
import com.neu.project.pojo.Product;

public class ProductDAO extends DAO {
	public ProductDAO() {
		
	}
	
	public List<Product> list(int sellerId) throws ProductException {
        try {
            begin();
            Query q = getSession().createQuery("from Product where seller = :sellerid");
            q.setInteger("sellerid", sellerId);
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not list the products", e);
        }
    }
	
	public List<Product> list() throws ProductException {
        try {
            begin();
            Query q = getSession().createQuery("from Product");
            List<Product> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new ProductException("Could not list the products", e);
        }
    }
	
	public Product get(int productId) throws ProductException {
		try {
			begin();
			Query q = getSession().createQuery("from Product where productID= :productId");
			q.setInteger("productId", productId);		
			Product product = (Product) q.uniqueResult();
			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Could not get product " + productId, e);
		}
	}
	
	public Product addProduct(Product p)
			throws ProductException {
		try {
			begin();
			System.out.println("inside Product DAO");
			
//			Seller seller = new Seller(p.getSeller().getFirstName());
//			Product product = new Product(p.getProductName(), p.getProductDesc(), p.getProductPrice(), p.getProductQuantity());
//			product.setProductName(p.getProductName());
//			product.setProductDesc(p.getProductDesc());
//			product.setProductPrice(p.getProductPrice());
//			product.setProductQuantity(p.getProductQuantity());
//			product.setSeller(seller);
			
			getSession().save(p);
			commit();
			return p;

		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Exception while creating product: " + e.getMessage());
		}
	}
	
	public void updateProduct(Product p)
			throws ProductException {
		try {
			begin();
			System.out.println("inside update Product DAO");
			
			Query q = getSession().createQuery("Update Product set productDesc = :productDesc, productPrice = :productPrice, productQuantity = :productQuantity, fileName = :filename where productID = :productID");
			q.setLong("productID", p.getProductID());
			q.setString("productDesc", p.getProductDesc());
			q.setString("productPrice", p.getProductPrice());
			q.setLong("productQuantity", p.getProductQuantity());
			q.setString("filename", p.getFileName());
			System.out.println(p.getProductID());
			System.out.println(p.getProductDesc());
			System.out.println(p.getProductPrice());
			System.out.println(p.getProductQuantity());
			System.out.println(p.getFileName());
			int r = q.executeUpdate();
			System.out.print(r);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Exception while creating product: " + e.getMessage());
		}
	}
	
	public void updateQty(Long qty_cart, Long productId) throws BuyerException {
		try {		
			System.out.println("updateCart");
			begin();
			Query query = getSession().createQuery("Update Product set productQuantity =:qty_cart where productID =:productId");
			query.setLong("productId", productId);
			query.setLong("qty_cart", qty_cart);
			int res = query.executeUpdate();
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new BuyerException("Error while updating cart: " + e.getMessage());
		}
	}
	
	public void deleteProduct(int productId) throws ProductException {
		try {
			begin();
			Query q = getSession().createQuery("delete Product where productID = :productId");
			q.setInteger("productId", productId);
			int res = q.executeUpdate();
			commit();
			close();
		} catch(HibernateException e) {
			rollback();
            throw new ProductException("Could not delete the product", e);
		}
	}
}
