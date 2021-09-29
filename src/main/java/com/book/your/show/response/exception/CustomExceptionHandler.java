package com.book.your.show.response.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.book.your.show.response.general.Response;
import com.book.your.show.response.general.Response.Status;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler{

	@Autowired
	private MessageSource messages;

	@ExceptionHandler({ CinemaCustomException.class,CinemaHallException.class,ScreenException.class })
	public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) {
		final List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		Response error = Response.badRequest();
		error.addErrorMsgToResponse(String.valueOf(Status.ERROR_FOUND), details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			WebRequest request) {

		final List<String> details = new ArrayList<>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.add(error.getField() + ": " + messages.getMessage(error.getDefaultMessage(), null, null));
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			details.add(error.getObjectName() + ": " + messages.getMessage(error.getDefaultMessage(), null, null));
		}
		Response error = Response.badRequest();
		error.addErrorMsgToResponse("Validation Failed", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler({Exception.class,})
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		final List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		Response error = Response.badRequest();
		error.addErrorMsgToResponse("Server Error", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
