package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.BrandService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.requestes.BrandRequestDto;
import com.yasin.e_commerce.entities.dto.responses.BrandResponseDto;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public ResponseEntity<DataResult<List<BrandResponseDto>>> getAllBrands() {
		return brandService.getAll();
	}
	
	@PostMapping("/add")
	private ResponseEntity<Result> addBrand(@RequestBody BrandRequestDto brandRequestDto) {
		return brandService.add(brandRequestDto);
	}

}
