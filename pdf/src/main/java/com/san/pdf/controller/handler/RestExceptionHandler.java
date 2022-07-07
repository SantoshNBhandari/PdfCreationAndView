package com.san.pdf.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.san.pdf.exception.BadRequestParamaterException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BadRequestParamaterException.class)
	protected ResponseEntity<Object> handleBadRequest(BadRequestParamaterException e){
		return new ResponseEntity<>("Invalid data for Parameters",HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
