package com.authorization.server.exception;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@PropertySource("classpath:errormap.properties")
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Environment env;

	@Autowired
	private MessageSource messageSource;

	private static final String ERROR_CODE = "Error Code: {}";
	private static final String ERROR_MESSAGE = "Error Message: {}";
	private static final String RETURNING_RESPONSE = "Returning ErrorResponse To Client: {}";
	private static final String CAUSE = "Cause: {}";

	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ErrorResponse> usernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {
		log.info("Handling UsernameNotFoundException");
		String errorCode = env.getProperty(ExceptionConstantsMap.USERNAME_NOT_FOUND_EXCEPTION);
		log.info(ERROR_CODE, errorCode);

		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		log.info(ERROR_MESSAGE, errorMessage);

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				""); // Sending Stack Trace is not compulsory ex.getMessage() is also enough

		log.info(RETURNING_RESPONSE, errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public final ResponseEntity<ErrorResponse> incorrectPasswordException(IncorrectPasswordException ex,
			WebRequest request) {
		log.info("Handling IncorrectPasswordException");
		String errorCode = env.getProperty(ExceptionConstantsMap.INCORRECT_PASSWORD_EXCEPTION);
		log.info(ERROR_CODE, errorCode);

		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		log.info(ERROR_MESSAGE, errorMessage);

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				""); // Sending Stack Trace is not compulsory ex.getMessage() is also enough

		log.info(RETURNING_RESPONSE, errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(JwtExpiredException.class)
	public final ResponseEntity<ErrorResponse> jwtExpiredException(JwtExpiredException ex, WebRequest request) {
		log.info("Handling JwtExpiredException");
		String errorCode = env.getProperty(ExceptionConstantsMap.JWT_EXPIRED_EXCEPTION);
		log.info(ERROR_CODE, errorCode);

		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		log.info(ERROR_MESSAGE, errorMessage);

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				""); // Sending Stack Trace is not compulsory ex.getMessage() is also enough

		log.info(RETURNING_RESPONSE, errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(JwtParseException.class)
	public final ResponseEntity<ErrorResponse> jwtParseException(JwtParseException ex, WebRequest request) {
		log.info("Handling JwtParseException");
		String errorCode = env.getProperty(ExceptionConstantsMap.JWT_PARSE_EXCEPTION);
		log.info(ERROR_CODE, errorCode);

		String errorMessage = messageSource.getMessage(errorCode, ex.getArgs(), LocaleContextHolder.getLocale());
		log.info(ERROR_MESSAGE, errorMessage);

		ErrorResponse errorResponse = new ErrorResponse(errorCode, Calendar.getInstance().getTime(), "", errorMessage,
				""); // Sending Stack Trace is not compulsory ex.getMessage() is also enough

		log.info(RETURNING_RESPONSE, errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}
}
