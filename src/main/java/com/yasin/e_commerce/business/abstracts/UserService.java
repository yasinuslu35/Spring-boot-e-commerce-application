package com.yasin.e_commerce.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.yasin.e_commerce.core.utilities.results.DataResult;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.concretes.User;
import com.yasin.e_commerce.entities.dto.requestes.UpdateUserRequestDto;


public interface UserService {
	ResponseEntity<DataResult<List<User>>> getAllUser();
	
	ResponseEntity<Result> update(UpdateUserRequestDto userRequestDto);


}
