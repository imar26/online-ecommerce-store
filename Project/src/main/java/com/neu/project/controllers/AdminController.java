package com.neu.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.dao.AdminDAO;
import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;

@Controller
public class AdminController {
	@Autowired
    @Qualifier("adminDao")
	AdminDAO adminDao;
	
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@RequestMapping(value = "/admin-home.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {
		return new ModelAndView("admin-home");
	}
	
	@RequestMapping(value = "/view-sellers.htm", method = RequestMethod.GET)
	public ModelAndView viewSellers(HttpServletRequest request) throws Exception {
		try {						
			List<Seller> sellers = sellerDao.list();
			return new ModelAndView("view-sellers", "sellers", sellers);			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(value = "/active-sellers.htm", method = RequestMethod.GET)
	public ModelAndView activeSellers(HttpServletRequest request) throws Exception {
		try {						
			List<Seller> sellers = sellerDao.activelist();
			return new ModelAndView("active-sellers", "sellers", sellers);			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(value = "/pending-sellers.htm", method = RequestMethod.GET)
	public ModelAndView deactiveSellers(HttpServletRequest request) throws Exception {
		try {						
			List<Seller> sellers = sellerDao.deactivelist();
			return new ModelAndView("pending-sellers", "sellers", sellers);			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
}
