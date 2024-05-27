package com.br.api.casebank.config;

import com.br.api.casebank.dto.ApiError;
import com.br.api.casebank.exception.DateHandlerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

	Logger logger = LogManager.getLogger(RestExceptionHandler.class);

	private ResponseEntity<ApiError> buildResponseEntityApiError(ApiError apiError) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(apiError, headers, apiError.getStatus());
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ApiError> handleEntityNotFound(Exception ex) {
		logger.catching(ex);
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("404");
		return buildResponseEntityApiError(apiError);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(Exception ex) {
		logger.catching(ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("500");
		apiError.setMessage("Unexpected error");
		return buildResponseEntityApiError(apiError);
	}

	@ExceptionHandler(DateHandlerException.class)
	public ResponseEntity<ApiError> handleDataException(Exception ex) {
		logger.catching(ex);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setErrorCode("400");
		return buildResponseEntityApiError(apiError);
	}

}