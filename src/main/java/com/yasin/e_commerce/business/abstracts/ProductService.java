package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.responses.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.responses.ProductWithCategoryDto;

public interface ProductService {
	ResponseEntity<DataResult<List<Product>>> getAll();
	
	ResponseEntity<Result> add(Product product);
	
	ResponseEntity<DataResult<List<Product>>> getAll(int pageNo,int pageSize);
	
	ResponseEntity<DataResult<List<Product>>> getAllSorted();
	
	ResponseEntity<DataResult<List<ProductWithCategoryDto>>> getProductWithCategoryDetails();
	
	ResponseEntity<DataResult<List<ProductWithBrandDto>>> findAllByProductName(String productName);
	

}
