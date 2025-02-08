package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.ProductService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Product;
import com.yasin.e_commerce.entities.dto.ProductWithBrandDto;
import com.yasin.e_commerce.entities.dto.ProductWithCategoryDto;



@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	private ProductService productService;
	
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<Product>>> getAll() {
		return this.productService.getAll();
	}
	
	@GetMapping("/getProductWithCategoryDetails")
	public ResponseEntity<DataResult<List<ProductWithCategoryDto>>> 
	getProductWithCategoryDetails() {
		return this.productService.getProductWithCategoryDetails();
	}
	
	@GetMapping("/findProductsByBrandName")
	public ResponseEntity<DataResult<List<ProductWithBrandDto>>> findProductsByBrandName
	(@RequestParam String brandName) {
		return this.productService.findAllByProductName(brandName);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Result> add(@RequestBody Product product) {
		return this.productService.add(product);
	}
	
}
