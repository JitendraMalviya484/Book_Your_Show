package com.book.your.show.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.your.show.dto.CreateScreenDto;
import com.book.your.show.dto.UpdateScreenDto;
import com.book.your.show.response.general.Response;
import com.book.your.show.service.ScreenService;

import io.swagger.annotations.Api;

@Api(value = "Screen Contollers CURD Operation API", tags = "Screen Create, Update, Delete, Get APIs")
@RestController
@RequestMapping("/api/v1/screen")
public class ScreenController {
	
private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ScreenService screenService;

	@Autowired
	private MessageSource messages;
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/createScreen")
	public Response createScreen(@RequestBody final @Valid CreateScreenDto screenDto,
			final HttpServletRequest request) {
		screenService.createScreen(screenDto);
		logger.info("Screen record created Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("Screen.created.successfully", new Object[0], request.getLocale()));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/createScreen")
	public Response updateScreen(@RequestBody final @Valid UpdateScreenDto updateScreenDto,
			final HttpServletRequest request) {
		screenService.updateScreen(updateScreenDto);
		logger.info("Screen record updated Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("Screen.updated.successfully", new Object[0], request.getLocale()));
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deletedScreen/{id}")
	public Response deletedScreen(@PathVariable("name") final Long id, final HttpServletRequest request) {
		screenService.deletedScreen(id);
		logger.info("Screen record deleted Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("Screen.deleted.successfully", new Object[0], request.getLocale()));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/screenList")
	public Response screenList() {
		logger.info("Screen record Fetching Successfully");
		return Response.ok().setPayload(screenService.screenList());
	}

	
	@SuppressWarnings("rawtypes")
	@GetMapping("/screenById/{id}")
	public Response screenById(@PathVariable("id") final Long id) {
		logger.info("Screen record Fetching Successfully");
		return Response.ok().setPayload(screenService.screenById(id));
	}

}
