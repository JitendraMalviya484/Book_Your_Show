package com.book.your.show.response.general;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.book.your.show.response.exception.ResponseError;
import com.book.your.show.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

	private Status status;

	private Integer statusCode;
	private T payload;
	private Object errors;
	private Object metadata;

	public static <T> Response<T> badRequest() {
		Response<T> response = new Response<>();
		response.setStatus(Status.BAD_REQUEST);
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return response;
	}

	public static <T> Response<T> ok() {
		Response<T> response = new Response<>();
		response.setStatus(Status.OK);
		response.setStatusCode(HttpStatus.OK.value());
		return response;
	}

	public static <T> Response<T> validationException() {
		Response<T> response = new Response<>();
		response.setStatus(Status.VALIDATION_EXCEPTION);
		response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		return response;
	}

	public void addErrorMsgToResponse(String errorMsg, List<String> details) {
		ResponseError error = new ResponseError().setDetails(details).setMessage(errorMsg)
				.setTimestamp(DateUtils.today());
		setErrors(error);
	}

	public enum Status {
		OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION, ERROR_FOUND
	}

	@Getter
	@Accessors(chain = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PageMetadata {
		private int size;
		private long totalElements;
		private int totalPages;
		private int number;

		public PageMetadata(int size, long totalElements, int totalPages, int number) {
			this.size = size;
			this.totalElements = totalElements;
			this.totalPages = totalPages;
			this.number = number;
		}
	}

}
