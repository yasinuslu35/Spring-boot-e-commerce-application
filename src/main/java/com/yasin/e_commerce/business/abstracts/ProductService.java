package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.ProductWithCategoryDto;

public interface ProductService {
	DataResult<List<Product>> getAll();
	
	Result add(Product product);
	
	DataResult<List<Product>> getAll(int pageNo,int pageSize);
	
	DataResult<List<Product>> getAllSorted();
	
	DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails();
	
	DataResult<List<ProductWithBrandDto>> findAllByProductName(String productName);
	

}
