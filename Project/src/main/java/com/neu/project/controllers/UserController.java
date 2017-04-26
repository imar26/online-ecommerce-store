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

import com.neu.project.validator.UserValidator;
import com.neu.project.pojo.User;
import com.neu.project.exception.UserException;
import com.neu.project.dao.UserDAO;

@Controller
@RequestMapping("/buyer/*")
public class UserController {
	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/buyer/buyer-home.htm", method = RequestMethod.GET)
	protected ModelAndView buyerHome() throws Exception {
		return new ModelAndView("buyer-home");
	}
	
	@RequestMapping(value = "/buyer/buyersignup.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		return new ModelAndView("buyer-signup", "user", new User());

	}
	
	@RequestMapping(value = "/buyer/buyersignup.htm", method = RequestMethod.POST)
	protected ModelAndView registerBuyer(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		
		try {
//			System.out.println(request.getParameter("username"));
			Boolean b1 = userDao.checkIfUsernameExists(request.getParameter("username"));
			Boolean b2 = userDao.checkIfEmailExists(request.getParameter("email.emailAddress"));
			Boolean b3 = userDao.checkIfPhoneNoExists(request.getParameter("phone.phoneNo"));
			if(b1 && b2 && b3) {
				User u = userDao.register(user);
				System.out.println("Now sending email.");
				Email email = new SimpleEmail();
		        email.setSmtpPort(465);
		        email.setAuthenticator(new DefaultAuthenticator("aadeshranderia26@gmail.com", "Dock443slew224@"));
		        email.setHostName("smtp.gmail.com");//if a server is capable of sending email.
		        email.setSSL(true);//setSSLOnConnect(true);
		        email.setFrom("aadeshranderia26@gmail.com");
		        email.setSubject("Signup Successfull!");
		        email.setMsg("Your have successfully signed up on our E-Commerce Application.\n\n"
		        		+"Your Username is:\t"+user.getUsername()+"\n"
		        		+"Your Password is:\t"+user.getPassword()+"\n"
		                +"Thank you!");
		        email.addTo(user.getEmail().getEmailAddress());
		        email.setTLS(true);//startTLS.enable.true
		        email.send();
		        System.out.println("Email sent successfully");
	//			request.getSession().setAttribute("user", u);
				return new ModelAndView("signup-success");
			}
			return new ModelAndView("buyer-error");
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
		
}
