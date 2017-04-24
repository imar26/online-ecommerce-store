package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.dao.BuyerDAO;
import com.neu.project.dao.ProductDAO;
import com.neu.project.exception.BuyerException;
import com.neu.project.exception.ProductException;
import com.neu.project.pojo.Product;
import com.neu.project.pojo.User;

@Controller
public class BuyerController {
	@Autowired
    @Qualifier("buyerDao")
	BuyerDAO buyerDao;
	
	@Autowired
    @Qualifier("productDao")
	ProductDAO productDao;
	
	@RequestMapping(value = "/buyer/cart.htm", method = RequestMethod.GET)
	protected ModelAndView getCart(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int uid = Integer.parseInt(request.getParameter("uid"));
		System.out.println(uid);
		mv.addObject("products", buyerDao.list(uid));		
		mv.setViewName("view-cart");
		return mv;
	}
	
	@RequestMapping(value = "/buyer/updateValueInCart.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String deleteCart(HttpServletRequest request) throws ProductException, BuyerException {
		String output = "";
		try {
			int productID = Integer.parseInt(request.getParameter("productID"));
			Long pId = Long.parseLong(request.getParameter("productID"));
			User user = (User)request.getSession().getAttribute("user");
			Long userId = user.getPersonID();
			System.out.println("ID is: "+productID);
			buyerDao.deleteProduct(pId, userId);
			Long prd_qty = Long.parseLong(request.getParameter("prd_qty"));
			System.out.println(prd_qty);
			Product pQty = productDao.get(productID);
			Long oldQty = pQty.getProductQuantity();
			Long newQty = oldQty + prd_qty;
			System.out.println(oldQty);
			System.out.println(newQty);
			productDao.updateQty(newQty, pId);
			
			output = String.valueOf(productID);
		} catch(HibernateException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return output;
	}
}
