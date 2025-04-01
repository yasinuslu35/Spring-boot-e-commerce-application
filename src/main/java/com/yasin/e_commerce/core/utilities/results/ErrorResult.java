package com.yasin.e_commerce.core.utilities.results;

public class ErrorResult extends Result {
	
	public ErrorResult(int statusCode) {
		super(statusCode);
	}
	
	public ErrorResult(int statusCode,String message) {
		super(statusCode,message);
		// TODO Auto-generated constructor stub
	}
}
