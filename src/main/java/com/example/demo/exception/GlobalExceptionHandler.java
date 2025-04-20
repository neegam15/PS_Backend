package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.dto.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecipeNotFound(RecipeNotFoundException ex) {
		logger.error("Error occurred: {}", ex.getMessage());

		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InvalidSearchKeywordException.class)
	public ResponseEntity<ErrorResponse> handleInvalidSearchKeyword(InvalidSearchKeywordException ex) {
		logger.error("Error occurred: {}", ex.getMessage());
		
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<String> handleDatabaseOperation(DatabaseOperationException ex) {
		logger.error("Error occurred: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		logger.error("Unexpected error occurred: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
	}

}
