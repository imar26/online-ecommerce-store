package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.SellerException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;
import com.neu.project.validator.SellerValidator;
import com.neu.project.validator.UserValidator;

@Controller
public class SellerController {
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@Autowired
	@Qualifier("sellerValidator")
	SellerValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/sellersignup.htm", method = RequestMethod.GET)
	protected ModelAndView registerSeller() throws Exception {
		return new ModelAndView("seller-signup", "seller", new Seller());

	}
	
	@RequestMapping(value = "/sellersignup.htm", method = RequestMethod.POST)
	protected ModelAndView registerSeller(HttpServletRequest request,  @ModelAttribute("seller") Seller seller, BindingResult result) throws Exception {
		try {
			Boolean b1 = sellerDao.checkIfUsernameExists(request.getParameter("username"));
			Boolean b2 = sellerDao.checkIfEmailExists(request.getParameter("email.emailAddress"));
			Boolean b3 = sellerDao.checkIfPhoneNoExists(request.getParameter("phone.phoneNo"));
			if(b1 && b2 && b3) {
				Seller s = sellerDao.register(seller);
//				request.getSession().setAttribute("seller", s);
				return new ModelAndView("signup-success");
			}
			return new ModelAndView("seller-error");
		} catch (SellerException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
}