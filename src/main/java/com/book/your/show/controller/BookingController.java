package com.book.your.show.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.your.show.dto.CreateBookingDto;
import com.book.your.show.dto.EditBookingDto;
import com.book.your.show.response.general.Response;
import com.book.your.show.service.BookingService;

import io.swagger.annotations.Api;

@Api(value = "Booking Contollers API", tags = "Booking's APIs")
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookingService bookingService;

	@Autowired
	private MessageSource messages;

	@SuppressWarnings("rawtypes")
	@GetMapping("/movieList")
	public Response screenById(@RequestParam String startDate, @RequestParam String endDate) {
		logger.info("Movie List Fetching Successfully");
		return Response.ok().setPayload(bookingService.movieList(startDate, endDate));
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/createBooking")
	public Response createBooking(@RequestBody final @Valid CreateBookingDto createBookingDto) {
		String bookingId = bookingService.createBooking(createBookingDto);
		logger.info("Create Booking successfully bookingId : {} ", bookingId);
		return Response.ok().setPayload(bookingId);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/ticketBookingDetails")
	public Response ticketBookingDetails(@RequestParam(defaultValue = "all") String BookingId, @RequestParam(defaultValue = "all") String name,
			@RequestParam(defaultValue = "all") String contactNumber, @RequestParam(defaultValue = "all") String email) {
		logger.info("Fetching ticket booking Records successfully ");
		return Response.ok().setPayload(bookingService.ticketBookingDetails(BookingId,name,contactNumber,email));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/editTicketBooking")
	public Response editTicketBooking(@RequestBody final @Valid EditBookingDto editBookingDto, final HttpServletRequest request) {
			bookingService.editTicketBooking(editBookingDto);
		logger.info("Ticket Boooking editing Successfully");
		return Response.ok().setPayload(messages.getMessage("edit.ticket.booking.successfully", new Object[0], request.getLocale()));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/cancleTicketBooking/{BookingId}")
	public Response cancleTicketBooking(@PathVariable("BookingId") final String BookingId,final HttpServletRequest request){
		bookingService.cancleTicketBooking(BookingId);
		logger.info("Ticket Boooking Cancel Successfully");
		return Response.ok().setPayload(messages.getMessage("Cancel.ticket.booking.successfully", new Object[0], request.getLocale()));
	}
	
}
