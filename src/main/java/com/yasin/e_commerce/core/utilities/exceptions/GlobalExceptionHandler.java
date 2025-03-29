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
    public ResponseEntity<ErrorResult> handleResourceNotFoundException
    (ConfigDataResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResult(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResult> handleBusinessException(BusinessException ex) {
    	return new ResponseEntity<>(new ErrorResult(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Erişim reddedildi: " + ex.getMessage());
    }
    
    @ExceptionHandler(exception = BadCredentialsException.class)
    public ResponseEntity<ErrorResult> handleBadCredentialsException(BadCredentialsException ex) {
    	return new ResponseEntity<>(new ErrorResult
    			("Kullanıcı adı veya şifre hatalı "), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        return ResponseEntity.badRequest().body(errors);
    }

    // ✅ Genel Exception yakalama (Bilinmeyen hatalar için)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDataResult<Map<String, String>>> handleGlobalException
    (Exception ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", ex.getMessage());
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        ErrorDataResult<Map<String, String>> errorResponse =
                new ErrorDataResult<>(errorDetails, "Beklenmeyen bir hata oluştu");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
