package com.neu.project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.project.pojo.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Product.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.invalid.product", "Product Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productDesc", "error.invalid.product", "Product Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "error.invalid.product", "Product Price Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productQuantity", "error.invalid.product", "Product Quantity Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "error.invalid.product", "Photo Required");
	}

}
