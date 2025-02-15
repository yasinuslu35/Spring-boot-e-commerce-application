package com.yasin.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yasin.e_commerce.business.abstracts.UserService;
import com.yasin.e_commerce.core.utilities.results.Result;
import com.yasin.e_commerce.entities.dto.requestes.UpdateUserRequestDto;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PutMapping("/update")
	public ResponseEntity<Result> updateUser(@RequestBody UpdateUserRequestDto userRequestDto) {
		return this.userService.update(userRequestDto);
	}
	

}
