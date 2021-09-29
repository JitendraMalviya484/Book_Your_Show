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

import com.book.your.show.dto.CreateCinemaDto;
import com.book.your.show.dto.UpdateCinemaDto;
import com.book.your.show.response.general.Response;
import com.book.your.show.service.CinemaService;

import io.swagger.annotations.Api;

@Api(value = "Cinema Contollers CURD Operation API", tags = "Cinema Create, Update, Delete, Get APIs")
@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private MessageSource messages;

	@SuppressWarnings("rawtypes")
	@PostMapping("/createCinema")
	public Response createCinema(@RequestBody final @Valid CreateCinemaDto cinemaDto,
			final HttpServletRequest request) {
		cinemaService.createCinema(cinemaDto);
		logger.info("Ciname record created Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("cinema.created.successfully", new Object[0], request.getLocale()));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/updateCinema")
	public Response updateCinema(@RequestBody final @Valid UpdateCinemaDto cinemaDto,
			final HttpServletRequest request) {
		cinemaService.updateCinema(cinemaDto);
		logger.info("Ciname record updated Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("cinema.updated.successfully", new Object[0], request.getLocale()));

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteCinemaByName/{name}")
	public Response deleteCinemaByName(@PathVariable("name") final String name, final HttpServletRequest request) {
		cinemaService.deleteCinemaByName(name);
		logger.info("Ciname record deleted Successfully");
		return Response.ok().setPayload(messages.getMessage("cinema.record.deleted",new Object[0], request.getLocale()));

	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/cinemaList")
	public Response cinemaList(final HttpServletRequest request) {
		logger.info("All Cinema Record By name");
		return Response.ok().setPayload(cinemaService.cinemaList());
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/cinemaByName/{name}")
	public Response cinemaByName(@PathVariable("name") final String name, final HttpServletRequest request) {
		logger.info("Cinema Record By name");
		return Response.ok().setPayload(cinemaService.cinemaByName(name));

	}

}
