package com.neu.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
}
