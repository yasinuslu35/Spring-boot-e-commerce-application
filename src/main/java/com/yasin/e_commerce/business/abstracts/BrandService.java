package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.requestes.BrandRequestDto;
import com.yasin.e_commerce.entities.dto.responses.BrandResponseDto;

public interface BrandService {
	ResponseEntity<DataResult<List<BrandResponseDto>>> getAll();
	
	ResponseEntity<Result> add(BrandRequestDto brandRequestDto);

}
