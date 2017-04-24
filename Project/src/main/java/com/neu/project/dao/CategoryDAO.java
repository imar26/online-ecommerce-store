package com.neu.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.project.exception.CategoryException;
import com.neu.project.exception.SellerException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Category;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;

public class CategoryDAO extends DAO {
	public CategoryDAO() {
		
	}
	
	public Category get(String categoryName) throws CategoryException {
		try {
			begin();
			Query q = getSession().createQuery("from Category where categoryName = :categoryName");
			q.setString("categoryName", categoryName);			
			Category category = (Category) q.uniqueResult();
			commit();
			close();
			return category;
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not get category " + categoryName, e);
		}
	}
	
	public Category get(int categoryId) throws CategoryException {
		try {
			begin();
			Query q = getSession().createQuery("from Category where categoryID= :categoryId");
			q.setInteger("categoryId", categoryId);		
			Category category = (Category) q.uniqueResult();
			commit();
			return category;
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not get category " + categoryId, e);
		}
	}
	
	public boolean checkIfCategoryNameExists(String categoryName) throws CategoryException {
		try {
			begin();
			Query q = getSession().createQuery("from Category where categoryName = :categoryName");
			q.setString("categoryName", categoryName);
			System.out.print(q);
			Category category = (Category) q.uniqueResult();
			System.out.print(category);
			if(category == null) {
				return true;
			} else {
				return false;
			}
		} catch(HibernateException e) {
			System.out.println("Category Name Already Exists!");
		}
		return false;
	}
	
	public Category addCategory(Category c)
			throws CategoryException {
		try {
			begin();
			System.out.println("inside Category DAO");
						
			Category category = new Category(c.getCategoryName());
			category.setCategoryName(c.getCategoryName());
			
			getSession().save(category);
			commit();
			return category;

		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Exception while creating category: " + e.getMessage());
		}
	}
	
	public void update(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the category", e);
        }
    }
	
	public void delete(Category category) throws CategoryException {
		try {
			begin();
			getSession().delete(category);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not delete category " + category.getCategoryName(), e);
		}
	}
	
	public List<Category> list() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }
	
	public void deleteCategory(int categoryId) throws CategoryException {
		try {
			begin();
			Query q = getSession().createQuery("delete Category where categoryID = :categoryId");
			q.setInteger("categoryId", categoryId);
			int res = q.executeUpdate();
			commit();
			close();
		} catch(HibernateException e) {
			rollback();
            throw new CategoryException("Could not delete the category", e);
		}
	}
}
