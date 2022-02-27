package com.lnk.marts.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lnk.marts.domain.Product;
import com.lnk.marts.domain.ProductCategory;

public interface ProductRepo extends CrudRepository<Product, Integer> {

	List<Product> findByCategory(ProductCategory category);
}
