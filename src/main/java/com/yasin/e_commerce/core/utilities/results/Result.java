package com.yasin.e_commerce.core.utilities.results;

import lombok.Data;

@Data
public class Result {
	private boolean success;
	private String message;

	
	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
}
