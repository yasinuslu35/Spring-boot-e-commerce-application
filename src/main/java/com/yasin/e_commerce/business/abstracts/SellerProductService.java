package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.SellerProductDto;

public interface SellerProductService {
	
	ResponseEntity<DataResult<List<SellerProductDto>>> getAll();
	
	ResponseEntity<Result> add(SellerProductDto sellerProductDto);

}
