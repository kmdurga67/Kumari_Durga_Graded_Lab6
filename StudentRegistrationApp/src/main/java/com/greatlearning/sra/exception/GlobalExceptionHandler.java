package com.greatlearning.sra.exception;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String invalidStudentId(NoSuchElementException exception) {

		return "Invalid Student Id passed.";
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String validationException(MethodArgumentNotValidException exception) {

		return exception.getFieldErrors()
				 .stream()
				 .map(fieldError -> fieldError.getDefaultMessage())
				 .collect(Collectors.joining(";\n"));
	}
}
