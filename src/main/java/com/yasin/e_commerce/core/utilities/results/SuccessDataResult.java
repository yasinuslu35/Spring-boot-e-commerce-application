package com.yasin.e_commerce.core.utilities.results;

public class SuccessDataResult<T> extends DataResult<T> {

	public SuccessDataResult(T data,int statusCode) {
		super(data, statusCode);
		// TODO Auto-generated constructor stub
	}

	public SuccessDataResult(T data,int statusCode,String message) {
		super(data,statusCode,message);
		// TODO Auto-generated constructor stub
	}
}
