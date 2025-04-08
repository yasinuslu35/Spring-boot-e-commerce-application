package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.requestes.SellerProductRequestDto;
import com.yasin.e_commerce.entities.dto.requestes.UpdateSellerProductDto;
import com.yasin.e_commerce.entities.dto.responses.SellerProductResponseDto;

public interface SellerProductService {
	
	ResponseEntity<DataResult<List<SellerProductResponseDto>>> getAll();
	
	ResponseEntity<Result> add(SellerProductRequestDto sellerProductDto);
	
	ResponseEntity<Result> update(SellerProductRequestDto sellerProductDto, Long id);

}
