package com.neu.project.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.neu.project.dao.CategoryDAO;
import com.neu.project.dao.ProductDAO;
import com.neu.project.dao.SellerDAO;
import com.neu.project.exception.ProductException;
import com.neu.project.exception.UserException;
import com.neu.project.pojo.Category;
import com.neu.project.pojo.Product;
import com.neu.project.pojo.Seller;
import com.neu.project.pojo.User;
import com.neu.project.validator.ProductValidator;

@Controller
public class ProductController {
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@Autowired
	@Qualifier("sellerDao")
	SellerDAO sellerDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;

	@Autowired
	@Qualifier("productValidator")
	ProductValidator validator;

	@Autowired
	ServletContext servletContext;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/seller/add-products.htm", method = RequestMethod.GET)
	protected ModelAndView addProd() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories", categoryDao.list());			
		mv.addObject("product", new Product());
		mv.setViewName("add-products");
		return mv;
	}
	
	@RequestMapping(value = "/seller/view-products.htm", method = RequestMethod.GET)
	protected ModelAndView viewProd(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		System.out.println(sellerId);
		mv.addObject("products", productDao.list(sellerId));		
		mv.setViewName("view-products");
		return mv;
	}

	@RequestMapping(value = "/seller/edit-products.htm", method = RequestMethod.GET)
	protected ModelAndView editProd(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int productId = Integer.parseInt(request.getParameter("id"));
		System.out.println(productId);
		mv.addObject("product", productDao.get(productId));		
		mv.setViewName("edit-products");
		return mv;
	}

	@RequestMapping(value = "/seller/addProducts.htm", method = RequestMethod.POST)
	protected ModelAndView addProducts(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		try {
			System.out.println(product.getProductName());
			if(product.getProductName().trim() != "" || product.getProductName() != null) {
				CommonsMultipartFile photoInMemory = product.getPhoto();

				String fileName = photoInMemory.getOriginalFilename();
				// could generate file names as well

				File localFile = new File("C:/Users/Aadesh/Downloads/Web_Tools/SpringProjects/Project/src/main/webapp/resources/images/", fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				product.setFileName(localFile.getPath());
				System.out.println("File is stored at" + localFile.getPath());
				System.out.print("registerNewUser");
				int sellerId = Integer.parseInt(request.getParameter("seller-name"));

				Seller seller = sellerDao.get(sellerId);
				product.setSeller(seller);

				Product p = productDao.addProduct(product);

				for(Category c: product.getCategories()) {
					c = categoryDao.get(c.getCategoryName());
					c.getProducts().add(product);
					categoryDao.update(c);
				}

				return new ModelAndView("product-success");			
			}
		} 	catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} 	catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} 	catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/seller/updateProducts.htm", method = RequestMethod.POST)
	protected ModelAndView updateProducts(HttpServletRequest request,  @ModelAttribute("product") Product product, BindingResult result) throws Exception {
		try {
			long productId = Long.parseLong(request.getParameter("pid"));
			product.setProductID(productId);
			String filename = request.getParameter("filename");
			product.setFileName(filename);
			CommonsMultipartFile photoInMemory = product.getPhoto();
			System.out.println("photo is: "+photoInMemory);
			
			if(!photoInMemory.isEmpty()) {
				String fileName = photoInMemory.getOriginalFilename();
				// could generate file names as well

				File localFile = new File("C:/Users/Aadesh/Downloads/Web_Tools/SpringProjects/Project/src/main/webapp/resources/images/", fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				product.setFileName(localFile.getPath());
				System.out.println("File is stored at" + localFile.getPath());
			}

			System.out.print("registerNewUser");

			productDao.updateProduct(product);

			return new ModelAndView("update-success");



		} 	catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} 	catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} 	catch (ProductException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/buyer/view-all-products.htm", method = RequestMethod.GET)
	protected ModelAndView viewAllProd(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("products", productDao.list());		
		mv.setViewName("view-all-products");
		return mv;
	}
	
	@RequestMapping(value = "/buyer/single-product.htm", method = RequestMethod.GET)
	protected ModelAndView viewSingleProd(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		int productid = Integer.parseInt(request.getParameter("id"));
		mv.addObject("product", productDao.get(productid));		
		mv.setViewName("single-product");
		return mv;
	}
}
