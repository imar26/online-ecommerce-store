package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
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
import com.neu.project.pojo.Product;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;
import com.neu.project.validator.SellerValidator;
import com.neu.project.validator.UserValidator;

@Controller
@RequestMapping("/seller/*")
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
	
	@RequestMapping(value = "/seller/seller-home.htm", method = RequestMethod.GET)
	protected ModelAndView sellerHome() throws Exception {
		return new ModelAndView("seller-home");
	}
	
	@RequestMapping(value = "/seller/sellersignup.htm", method = RequestMethod.GET)
	protected ModelAndView registerSeller() throws Exception {
		return new ModelAndView("seller-signup", "seller", new Seller());

	}	
	
	@RequestMapping(value = "/seller/sellersignup.htm", method = RequestMethod.POST)
	protected ModelAndView registerSeller(HttpServletRequest request,  @ModelAttribute("seller") Seller seller, BindingResult result) throws Exception {
		try {
			Boolean b1 = sellerDao.checkIfUsernameExists(request.getParameter("username"));
			Boolean b2 = sellerDao.checkIfEmailExists(request.getParameter("email.emailAddress"));
			Boolean b3 = sellerDao.checkIfPhoneNoExists(request.getParameter("phone.phoneNo"));
			if(b1 && b2 && b3) {
				Seller s = sellerDao.register(seller);
				System.out.println("Now sending email.");
				Email email = new SimpleEmail();
		        email.setSmtpPort(465);
		        email.setAuthenticator(new DefaultAuthenticator("aadeshranderia26@gmail.com", "Dock443slew224@"));
		        email.setHostName("smtp.gmail.com");//if a server is capable of sending email.
		        email.setSSL(true);//setSSLOnConnect(true);
		        email.setFrom("aadeshranderia26@gmail.com");
		        email.setSubject("Signup Successfull!");
		        email.setMsg("Your have successfully signed up on our E-Commerce Application.\n\n"
		        		+"Your Username is:\t"+seller.getUsername()+"\n"
		        		+"Your Password is:\t"+seller.getPassword()+"\n"
		                +"Thank you!");
		        email.addTo(seller.getEmail().getEmailAddress());
		        email.setTLS(true);//startTLS.enable.true
		        email.send();
		        System.out.println("Email sent successfully");
//				request.getSession().setAttribute("seller", s);
				return new ModelAndView("signup-success");
			}
			return new ModelAndView("seller-error");
		} catch (SellerException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/seller/view-all-orders.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllProd(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		
		long id = Long.parseLong(request.getParameter("sellerId"));
		mv.addObject("sellerID", id);
		mv.addObject("orders", sellerDao.orderlist());

		mv.setViewName("view-all-orders");
		return mv;
	}
}
