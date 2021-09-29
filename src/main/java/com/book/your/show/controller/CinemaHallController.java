package com.book.your.show.controller;

import java.util.Locale;

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

import com.book.your.show.dto.CreateCinemaHallDto;
import com.book.your.show.dto.UpdateCinemaHallDto;
import com.book.your.show.response.general.Response;
import com.book.your.show.service.CinemaHallService;

import io.swagger.annotations.Api;

@Api(value = "CinemaHall Contollers CURD Operation API", tags = "CinemaHall Create, Update, Delete, Get APIs")
@RestController
@RequestMapping("/api/v1/cinemaHall")
public class CinemaHallController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CinemaHallService cinemaHallService;

	@Autowired
	private MessageSource messages;
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/createCinemaHall")
	public Response createCinemaHall(@RequestBody final @Valid CreateCinemaHallDto cinemaHallDto,
			final HttpServletRequest request) {
		cinemaHallService.createCinema(cinemaHallDto);
		logger.info("Ciname Hall record created Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("cinema.Hall.created.successfully", new Object[0], new Locale("US")));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/updateCinemaHall")
	public Response updateCinemaHall(@RequestBody final @Valid UpdateCinemaHallDto updateCinemaHallDto,
			final HttpServletRequest request) {
		cinemaHallService.updateCinemaHall(updateCinemaHallDto);
		logger.info("Ciname Hall record Updated Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("cinema.Hall.updated.successfully", new Object[0], new Locale("US")));
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/deleteCinemaHall/{chId}")
	public Response deleteCinemaHall(@PathVariable("name") final Long chId, final HttpServletRequest request) {
		cinemaHallService.deleteCinemaHall(chId);
		logger.info("Ciname Hall record Deleted Successfully");
		return Response.ok()
				.setPayload(messages.getMessage("cinema.Hall.deleted.successfully", new Object[0], request.getLocale()));
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/cinemaHallList")
	public Response cinemaHallList() {
		logger.info("All Cinema Hall record fected Successfully");
		return Response.ok()
				.setPayload(cinemaHallService.cinemaHallList());
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/cinemaHall/{chId}")
	public Response cinemaHall(@PathVariable("name") final Long chId) {
		logger.info("Cinema Hall record fected Successfully");
		return Response.ok()
				.setPayload(cinemaHallService.cinemaHall(chId));
		
	}

}
