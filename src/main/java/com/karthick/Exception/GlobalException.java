package com.karthick.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException  {

	@ExceptionHandler(SellerException.class)
	public ResponseEntity<ErrorDettails> SellerExceptionHandler(SellerException se,WebRequest req)
	{
		ErrorDettails  errorDettails=new ErrorDettails();
		 System.out.println(se.getMessage()+"hi");
		errorDettails.setError(se.getMessage());
		errorDettails.setDetails(req.getDescription(false));
		errorDettails.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDettails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDettails> ProductExceptionExceptionHandler(ProductException se,WebRequest req)
	{
		ErrorDettails  errorDettails=new ErrorDettails();
		 System.out.println(se.getMessage()+"hi");
		errorDettails.setError(se.getMessage());
		errorDettails.setDetails(req.getDescription(false));
		errorDettails.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDettails,HttpStatus.BAD_REQUEST);
	}
}
