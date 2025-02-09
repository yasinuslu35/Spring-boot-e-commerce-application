package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.SellerProductService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.SellerProduct;

@RestController
@RequestMapping("/api/sellerProducts")
public class SellerProductController {
	
	private final SellerProductService sellerProductService;
	
	public SellerProductController(SellerProductService sellerProductService) {
		super();
		this.sellerProductService = sellerProductService;
	}



	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<SellerProduct>>> getAll() {
		return this.sellerProductService.getAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Result> add(SellerProduct sellerProduct) {
		return this.sellerProductService.add(sellerProduct);
	}

}
