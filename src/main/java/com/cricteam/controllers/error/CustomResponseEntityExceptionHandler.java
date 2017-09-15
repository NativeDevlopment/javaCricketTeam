package com.cricteam.controllers.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.TypeMismatchException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.cricteam.exception.ResourceNotFroundException;
import com.cricteam.models.Response;

@ControllerAdvice
@Order(1)
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public CustomResponseEntityExceptionHandler() {
		super();
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<FieldErrorDTO> errors = new ArrayList<FieldErrorDTO>(fieldErrors.size() + globalErrors.size());
		// String error;
		for (FieldError fieldError : fieldErrors) {
			FieldErrorDTO error = new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			FieldErrorDTO error = new FieldErrorDTO(objectError.getObjectName(), objectError.getDefaultMessage());
			errors.add(error);
		}
		Response errorMessage = new Response();
		errorMessage.setErrors(errors);
		errorMessage.setSuccess(false);
		return new ResponseEntity<Object>(errorMessage, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(errorMessage, headers, status);
	}

	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(errorMessage, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setPath(ex.getRequestURL());
		errorMessage.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
	}

	/**
	 * A single place to customize the response body of all Exception types.
	 * <p>
	 * The default implementation sets the
	 * {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE} request attribute and creates
	 * a {@link ResponseEntity} from the given body, headers, and status.
	 * 
	 * @param ex
	 *            the exception
	 * @param body
	 *            the body for the response
	 * @param headers
	 *            the headers for the response
	 * @param status
	 *            the response status
	 * @param request
	 *            the current request
	 */

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		pageNotFoundLogger.warn(ex.getMessage());
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return super.handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for HttpRequestMethodNotSupportedException.
	 * <p>
	 * This method logs a warning, sets the "Allow" header, and delegates to
	 * {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		pageNotFoundLogger.warn(ex.getMessage());

		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!supportedMethods.isEmpty()) {
			headers.setAllow(supportedMethods);
		}
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for HttpMediaTypeNotAcceptableException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for MissingPathVariableException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 * @since 4.2
	 */
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for MissingServletRequestParameterException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for ServletRequestBindingException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for ConversionNotSupportedException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for TypeMismatchException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	public ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for HttpMessageNotWritableException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for MissingServletRequestPartException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	/**
	 * Customize the response for BindException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatusCode(203);//TODO Confirm the code
		return handleExceptionInternal(ex, errorMessage, headers, status, request);
	}

	// API
	// 400
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final Exception ex, final WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatusCode(400);
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	/*// 403
	@ExceptionHandler({  })
	public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}*/

	// 409
	@ExceptionHandler({ NonUniqueResultException.class, DataAccessException.class, InvalidDataAccessApiUsageException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setStatusCode(409);
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	// 500
	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class, Exception.class })
	public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setStatusCode(500);
		errorMessage.setMessage(ex.getMessage());
		
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ResourceNotFroundException.class)
	@ResponseBody
	public Response handleNotFound(ResourceNotFroundException ex, HttpServletRequest request) {
		Response errorMessage = new Response();
		errorMessage.setSuccess(false);
		errorMessage.setStatusCode(303);
		errorMessage.setPath(request.getRequestURL().toString());
		errorMessage.setMessage(ex.getMessage());
		return errorMessage;
	}
}
