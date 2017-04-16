package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;

@Controller
public class NavigateController {
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
}
