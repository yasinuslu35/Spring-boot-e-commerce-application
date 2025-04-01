package com.yasin.e_commerce.core.utilities.exceptions;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yasin.e_commerce.core.utilities.results.ErrorDataResult;
import com.yasin.e_commerce.core.utilities.results.ErrorResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	// ✅ Özel Exception'lar için özel mesaj döndürme
	@ExceptionHandler(ConfigDataResourceNotFoundException.class)
	public ResponseEntity<ErrorResult> handleResourceNotFoundException(ConfigDataResourceNotFoundException ex) {
		return new ResponseEntity<>(new ErrorResult(HttpStatus.NOT_FOUND.value(),ex.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorResult> handleBusinessException(BusinessException ex) {
		return new ResponseEntity<>(new ErrorResult(HttpStatus.BAD_REQUEST.value(),ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Erişim reddedildi: " + ex.getMessage());
	}

	@ExceptionHandler(exception = BadCredentialsException.class)
	public ResponseEntity<ErrorResult> handleBadCredentialsException(BadCredentialsException ex) {
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResult(
						HttpStatus.UNAUTHORIZED.value(), "Kullanıcı adı veya şifre hatalı")
						);
		//return new ResponseEntity<>(new ErrorResult(HttpStatus.UNAUTHORIZED.value(),"Kullanıcı adı veya şifre hatalı "), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDataResult<Map<String, String>>> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		System.out.println(ex.getMessage());

		ex.getBindingResult()
		.getFieldErrors()
		.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		

		ErrorDataResult<Map<String, String>> errorResponse = 
				new ErrorDataResult<>(errors, HttpStatus.BAD_REQUEST.value(), "İşlem başarısız.");
		

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		// return ResponseEntity.badRequest().body(errors);
	}

	// ✅ Genel Exception yakalama (Bilinmeyen hatalar için)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDataResult<Map<String, String>>> handleGlobalException(Exception ex) {
		Map<String, String> errorDetails = new HashMap<>();
		errorDetails.put("error", ex.getMessage());
		errorDetails.put("timestamp", LocalDateTime.now().toString());

		ErrorDataResult<Map<String, String>> errorResponse = 
				new ErrorDataResult<>
		(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR.value(),"Beklenmeyen bir hata oluştu");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
