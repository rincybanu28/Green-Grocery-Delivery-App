package com.example.gorcerydelivery.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public ResponseEntity<ResourceErrorResponse> handleException(ResourceNotFoundException ex)
	{
		ResourceErrorResponse studentErrorResponse =new ResourceErrorResponse();
		studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		studentErrorResponse.setMessage(ex.getMessage());
		studentErrorResponse.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler    // handling all exceptions other than StudentNotFoundException
	public ResponseEntity<ResourceErrorResponse> handleException(Exception ex){
		ResourceErrorResponse studentErrorResponse = new ResourceErrorResponse();
		studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		studentErrorResponse.setMessage(ex.getMessage());
		studentErrorResponse.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
}
