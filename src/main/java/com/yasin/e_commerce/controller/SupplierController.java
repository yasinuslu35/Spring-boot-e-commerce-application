package com.yasin.e_commerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.SupplierService;
import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Supplier;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
	
	private SupplierService supplierService;

	public SupplierController(SupplierService supplierService) {
		super();
		this.supplierService = supplierService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Supplier>> getAll() {
		return this.supplierService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody Supplier supplier) {
		return this.supplierService.add(supplier);
	}
	
	
	
}
