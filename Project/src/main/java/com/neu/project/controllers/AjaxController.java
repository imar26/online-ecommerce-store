package com.neu.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.neu.project.dao.BuyerDAO;
import com.neu.project.dao.CategoryDAO;
import com.neu.project.dao.ProductDAO;
import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.CategoryException;
import com.neu.project.exception.ProductException;
import com.neu.project.exception.SellerException;
import com.neu.project.pojo.Cart;
import com.neu.project.pojo.Product;
import com.neu.project.pojo.User;
import com.neu.project.exception.BuyerException;

@Controller
public class AjaxController {
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@Autowired
    @Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
    @Qualifier("productDao")
	ProductDAO productDao;
	
	@Autowired
    @Qualifier("buyerDao")
	BuyerDAO buyerDao;
	
	@RequestMapping(value = "/admin/activateSeller.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String activateSeller(HttpServletRequest request) throws SellerException {
		String output = "";
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("ID is: "+aid);
			sellerDao.activateSeller(aid);
			output = String.valueOf(aid);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
	
	@RequestMapping(value = "/buyer/addToCart.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String addToCart(HttpServletRequest request) throws ProductException, BuyerException {
		String output = "";
		
		try {
			
			System.out.println("In Controller");
			int qty_cart = Integer.parseInt(request.getParameter("qty_cart"));
			int quantity = qty_cart;
			int productId = Integer.parseInt(request.getParameter("productID"));
			Product product = productDao.get(productId);
			User user = (User)request.getSession().getAttribute("user");
			Long pId = product.getProductID();
			Long userId = user.getPersonID();			
			boolean b1 = buyerDao.checkCart(pId, userId);
			System.out.println(b1);
			if(b1) {
				System.out.println("Now inserting");
				buyerDao.insertIntoCart(qty_cart, product, user);
			} else {
				System.out.println("Now updating");
				Cart cart = buyerDao.getQuantity(pId, userId);
				quantity = cart.getQuantity();
				quantity = quantity + qty_cart;
				buyerDao.updateCart(quantity, pId, userId);				
			}
			Product pQty = productDao.get(productId);
			Long oldQty = pQty.getProductQuantity();
			Long newQty = oldQty - qty_cart;
			productDao.updateQty(newQty, pId);
			System.out.println("Quantity is: "+qty_cart);
			output = String.valueOf(qty_cart);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
	
	@RequestMapping(value = "/admin/deactivateSeller.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String deactivateSeller(HttpServletRequest request) throws SellerException {
		String output = "";
		try {
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("ID is: "+aid);
			sellerDao.deactivateSeller(aid);
			output = String.valueOf(aid);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
	
	@RequestMapping(value = "/buyer/searchProducts.htm", method = RequestMethod.GET)
	public @ResponseBody
	String searchProducts(HttpServletRequest request) throws ProductException {
		String output = "";
		try {
			HttpSession session = (HttpSession) request.getSession();
			String searchValue = request.getParameter("searchValue");
			System.out.println("Search for: "+searchValue);
			List<Product> searchList = buyerDao.searchProducts(searchValue);
			System.out.println("Size List: "+searchList.size());
			session.setAttribute("products", searchList);
			output = String.valueOf(searchList.size());
			return output;
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}		
		return output;
	}
	
	@RequestMapping(value = "/buyer/filterProducts.htm", method = RequestMethod.GET)
	public @ResponseBody
	String filterProducts(HttpServletRequest request) throws ProductException {
		String output = "";
		try {
			HttpSession session = (HttpSession) request.getSession();
			String filter = request.getParameter("filter");
			System.out.println("Search for: "+filter);
			List<Product> filterList = new ArrayList<Product>();
			if(filter.equals("h2l")) {
				filterList = productDao.filterH2l();
			} else if(filter.equals("l2h")) {
				filterList = productDao.filterL2h();
			}
			System.out.println("Size List: "+filterList.size());
			session.setAttribute("products", filterList);
			output = "list retured";
			return output;
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}		
		return output;
	}
	
	@RequestMapping(value = "/admin/deleteCategory.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String deleteCategory(HttpServletRequest request) throws CategoryException {
		String output = "";
		try {
			int cid = Integer.parseInt(request.getParameter("cid"));
			System.out.println("ID is: "+cid);
			categoryDao.deleteCategory(cid);
			output = String.valueOf(cid);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
	
	@RequestMapping(value = "/seller/deleteProducts.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String deleteProduct(HttpServletRequest request) throws ProductException {
		String output = "";
		try {
			int pid = Integer.parseInt(request.getParameter("pid"));
			System.out.println("ID is: "+pid);
			productDao.deleteProduct(pid);
			output = String.valueOf(pid);
			System.out.println("output is: "+output);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
}
