package com.yasin.e_commerce.core.utilities.results;


public class DataResult<T> extends Result{

	private T data;
	
	public DataResult(T data, int statusCode, String message) {
		super(statusCode, message);
		this.data = data;
	}
	
	public DataResult(T data, int statusCode) {
		super(statusCode);
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	


}
