package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.Seller;
import com.yasin.e_commerce.entities.dto.responses.SellerResponseDto;

public interface SellerService {
	ResponseEntity<DataResult<List<SellerResponseDto>>> getAll();
	
	ResponseEntity<Result> add(Seller seller);

}
