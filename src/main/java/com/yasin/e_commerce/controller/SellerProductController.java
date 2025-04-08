package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.SellerProductService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.requestes.SellerProductRequestDto;
import com.yasin.e_commerce.entities.dto.responses.SellerProductResponseDto;

@RestController
@RequestMapping("/api/sellerProducts")
public class SellerProductController {

	private final SellerProductService sellerProductService;

	public SellerProductController(SellerProductService sellerProductService) {
		super();
		this.sellerProductService = sellerProductService;
	}

	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<SellerProductResponseDto>>> getAll() {
		return this.sellerProductService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<Result> add(@RequestBody SellerProductRequestDto sellerProductDto) {
		return this.sellerProductService.add(sellerProductDto);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Result> updateProduct(@PathVariable Long id,
			@RequestBody SellerProductRequestDto sellerProductDto) {
		return this.sellerProductService.update(sellerProductDto, id);
	}

}
