package com.book.your.show.service;

import java.util.List;

import javax.validation.Valid;

import com.book.your.show.dto.BookingDetailsDto;
import com.book.your.show.dto.CreateBookingDto;
import com.book.your.show.dto.EditBookingDto;
import com.book.your.show.dto.MovieDto;

public interface BookingService {

	List<MovieDto> movieList(String startDate, String endDate);

	String createBooking(@Valid CreateBookingDto createBookingDto);

	List<BookingDetailsDto> ticketBookingDetails(String bookingId, String name, String contactNumber, String email);

	void editTicketBooking(EditBookingDto editBookingDto);

	void cancleTicketBooking(String bookingId);

}
