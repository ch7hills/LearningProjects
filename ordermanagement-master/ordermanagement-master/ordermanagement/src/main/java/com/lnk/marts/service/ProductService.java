package com.lnk.marts.service;

import com.lnk.marts.domain.Product;
import com.lnk.marts.domain.ProductCategory;
import com.lnk.marts.repo.ProductCategoryRepo;
import com.lnk.marts.repo.ProductRepo;

public class ProductService {

	public ProductService(ProductRepo productRepo , ProductCategoryRepo productCategoryRepo) {
		this.productRepo=productRepo;
		this.productCategoryRepo=productCategoryRepo;
	}

	private ProductRepo productRepo;
	private ProductCategoryRepo productCategoryRepo;
	
	
	/*public Product createProduct()
	{
		
		 ProductCategory productCategory = productCategoryRepo.findOne("a");
		 
	        if (product == null) {
	            throw new RuntimeException("Product categpru does not exist: " + "aa");
	        }
	        return productRepo.save(product);
	        
	}*/
	
	

	
}
