package com.yasin.e_commerce.core.utilities.results;

import lombok.Data;

@Data
public class Result {
	private int statusCode;
	private String message;

	
	public Result(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public Result(int statusCode, String message) {
		this(statusCode);
		this.message = message;
	}
}
