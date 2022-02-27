package com.lnk.marts.repo;

import org.springframework.data.repository.CrudRepository;

import com.lnk.marts.domain.ProductCategory;

public interface ProductCategoryRepo extends CrudRepository<ProductCategory, Integer> {

}
