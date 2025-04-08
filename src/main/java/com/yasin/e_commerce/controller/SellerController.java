package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.SellerService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Seller;
import com.yasin.e_commerce.entities.dto.responses.SellerResponseDto;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
	
	private final SellerService sellerService;

	public SellerController(SellerService sellerService) {
		super();
		this.sellerService = sellerService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<SellerResponseDto>>> getAll() {
		return this.sellerService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<Result> add(@RequestBody Seller seller) {
		return this.sellerService.add(seller);
	}
	
	
	
}
