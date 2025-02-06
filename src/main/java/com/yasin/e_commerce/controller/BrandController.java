package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.BrandService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	public BrandController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public DataResult<List<Brand>> getAllBrands() {
		return brandService.getAll();
	}
	
	@PostMapping("/add")
	public Result addBrand(@RequestBody Brand brand) {
		return brandService.add(brand);
	}

}
