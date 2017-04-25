package com.neu.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
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

import com.neu.project.dao.AdminDAO;
import com.neu.project.dao.CategoryDAO;
import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Category;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;
import com.neu.project.validator.CategoryValidator;
import com.neu.project.validator.SellerValidator;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
    @Qualifier("adminDao")
	AdminDAO adminDao;
	
	@Autowired
    @Qualifier("sellerDao")
	SellerDAO sellerDao;
	
	@Autowired
    @Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	@Qualifier("categoryValidator")
	CategoryValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/admin/admin-home.htm", method = RequestMethod.GET)
	protected ModelAndView adminHome() throws Exception {
		return new ModelAndView("admin-home");
	}
	
	@RequestMapping(value = "/admin/manage-categories.htm", method = RequestMethod.GET)
	protected ModelAndView manageCategories() throws Exception {
		return new ModelAndView("manage-categories");
	}
	
	@RequestMapping(value = "/admin/add-categories.htm", method = RequestMethod.GET)
	protected ModelAndView addCategories() throws Exception {
		return new ModelAndView("add-categories", "category", new Category());
	}
	
	@RequestMapping(value = "/admin/view-categories.htm", method = RequestMethod.GET)
	protected ModelAndView viewCategories(HttpServletRequest request) throws Exception {
		try {
			List<Category> categories = categoryDao.list();
			return new ModelAndView("view-categories", "categories", categories);
		} catch(HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
	}
	
	@RequestMapping(value = "/admin/view-sellers.htm", method = RequestMethod.GET)
	public ModelAndView viewSellers(HttpServletRequest request) throws Exception {
		try {	
			ModelAndView mv = new ModelAndView();
			
			HttpSession session= (HttpSession)request.getSession();
			int startpage = (Integer)session.getAttribute("startpage");
			System.out.println("Start page: "+startpage);
			String type=request.getParameter("side");
			
			if(type==null || type.equals("next")) {
				int endpage = startpage + 2;
				System.out.println("Endpage : "+endpage);
				int totalSize=sellerDao.getTotalCount();
				System.out.println("size: "+totalSize);
		        if(endpage>totalSize) {
		        	endpage=totalSize - 2;
		        	System.out.println("Inner Endpage : "+endpage);
		            System.out.println("If gt , startPage = "+endpage);
		            mv.addObject("sellers",sellerDao.paginateList(endpage, 2));
		        } else {
		        	mv.addObject("sellers",sellerDao.paginateList(startpage, 2));
		        }
		        mv.setViewName("view-sellers");
		        
		        session.setAttribute("startpage",endpage);
			} else if(type.equals("back")) {
				int endpage = startpage-2;
				System.out.println("Back Endpage : "+endpage);
				if(endpage<0) {
					mv.addObject("sellers",sellerDao.paginateList(0, 2));
					mv.setViewName("view-sellers");
	                session.setAttribute("startpage",2);
	                return mv;
				}
				startpage=endpage;
	            mv.addObject("sellers",sellerDao.paginateList(startpage, 2));
	            mv.setViewName("view-sellers");
	            
	            session.setAttribute("startpage",endpage);
			}
			
//			List<Seller> sellers = sellerDao.list();
//			return new ModelAndView("view-sellers", "sellers", sellers);
			
			return mv;
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(value = "/admin/active-sellers.htm", method = RequestMethod.GET)
	public ModelAndView activeSellers(HttpServletRequest request) throws Exception {
		try {						
			List<Seller> sellers = sellerDao.activelist();
			return new ModelAndView("active-sellers", "sellers", sellers);			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(value = "/admin/pending-sellers.htm", method = RequestMethod.GET)
	public ModelAndView deactiveSellers(HttpServletRequest request) throws Exception {
		try {						
			List<Seller> sellers = sellerDao.deactivelist();
			return new ModelAndView("pending-sellers", "sellers", sellers);			
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
	
	@RequestMapping(value = "/admin/addCategory.htm", method = RequestMethod.POST)
	public ModelAndView addCategories(HttpServletRequest request,  @ModelAttribute("category") Category category, BindingResult result) throws Exception {
		try {	
			Boolean b1 = categoryDao.checkIfCategoryNameExists(request.getParameter("categoryName"));			
			if(b1) {
				Category c = categoryDao.addCategory(category);
				request.getSession().setAttribute("category", c);
				return new ModelAndView("category-success", "category", c);	
			}
			return new ModelAndView("category-error");
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}			
	}
}
