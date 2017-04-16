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

import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.SellerException;

@Controller
public class AjaxController {
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@RequestMapping(value = "/activateSeller.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
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
	
	@RequestMapping(value = "/deactivateSeller.htm", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
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
}
