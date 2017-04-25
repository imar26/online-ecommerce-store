package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.exception.AdminException;
import com.neu.project.exception.SellerException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Admin;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;
import com.neu.project.dao.AdminDAO;
import com.neu.project.dao.SellerDAO;
import com.neu.project.dao.UserDAO;

@Controller
public class NavigateController {
	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@Autowired
    @Qualifier("adminDao")
	AdminDAO adminDao;
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView redirectIndex(HttpServletRequest request) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView redirectLogin(HttpServletRequest request) {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public ModelAndView redirectSignup(HttpServletRequest request) {
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView redirectLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/redirectsignup.htm", method = RequestMethod.POST)
	public ModelAndView redirectSignupBy(HttpServletRequest request) {
		String value = request.getParameter("signup");
		if (value.equalsIgnoreCase("buyer")) {
			return new ModelAndView("buyer-signup", "user", new User());
		} else if (value.equalsIgnoreCase("seller")) {
			return new ModelAndView("seller-signup", "seller", new Seller());
		}
		return new ModelAndView("signup","results",null);
		
	}
	
	@RequestMapping(value = "/redirectlogin.htm", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String loginType = request.getParameter("login");
		System.out.println(loginType);
		if(loginType.equalsIgnoreCase("buyer")) {
			try {
				User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
				if (u == null) {
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");					
					return new ModelAndView("error");
				}
				//session.setAttribute("startpage", 1);
				session.setAttribute("user", u);
				
				return new ModelAndView("buyer-home");
			} catch(HibernateException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while login");
				return new ModelAndView("error");
			}
		} else if(loginType.equalsIgnoreCase("seller")) {
			try {

				Seller s = sellerDao.get(request.getParameter("username"), request.getParameter("password"));
				
				if(s == null){
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");
					return new ModelAndView("error");
				}
				
				boolean sell = s.getStatus();
				System.out.println(sell);
				if(sell == false) {
					return new ModelAndView("seller-nologin");
				}
				
				session.setAttribute("seller", s);
				
				return new ModelAndView("seller-home");

			} catch (HibernateException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while login");
				return new ModelAndView("error");
			}
		} else if(loginType.equalsIgnoreCase("admin")) {
			try {

				Admin a = adminDao.get(request.getParameter("username"), request.getParameter("password"));
				
				if(a == null){
					System.out.println("UserName/Password does not exist");
					session.setAttribute("errorMessage", "UserName/Password does not exist");
					return new ModelAndView("error");
				}
				
				session.setAttribute("startpage", 0);
				session.setAttribute("admin", a);
				
				return new ModelAndView("admin-home");

			} catch (HibernateException e) {
				System.out.println("Exception: " + e.getMessage());
				session.setAttribute("errorMessage", "error while login");
				return new ModelAndView("error");
			}
		}
//		return "";
		return null;
	}
}
