package com.yasin.e_commerce.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T>{
	
	public ErrorDataResult(T data, int statusCode) {
		super(data, statusCode);
	}

	public ErrorDataResult(T data,int statusCode,String message) {
		super(data,statusCode,message);
	}
}
