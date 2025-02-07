package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Brand;

public interface BrandService {
	ResponseEntity<DataResult<List<Brand>>> getAll();
	
	ResponseEntity<Result> add(Brand brand);

}
