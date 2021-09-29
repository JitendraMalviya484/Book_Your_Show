package com.book.your.show.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.book.your.show.dto.BookingDetailsDto;
import com.book.your.show.dto.CinemaResponseDto;
import com.book.your.show.dto.CreateBookingDto;
import com.book.your.show.dto.EditBookingDto;
import com.book.your.show.dto.MovieDto;
import com.book.your.show.model.Booking;
import com.book.your.show.model.Cinema;
import com.book.your.show.repository.BookingRepository;
import com.book.your.show.repository.CinemaRepository;
import com.book.your.show.repository.spec.BookingSpecification;
import com.book.your.show.repository.spec.SearchOperation;
import com.book.your.show.response.exception.BookingException;
import com.book.your.show.util.DateUtils;

@Service
public class BookingServiceImpl implements BookingService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CinemaRepository cinemaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messages;

	@Autowired
	private BookingRepository bookingRepository;

	private static String ALL = "all";

	@Override
	public List<MovieDto> movieList(String start, String end) {
		List<Cinema> cinemaList = new ArrayList<>();
		if (start != null && end != null) {
			Date startDate = DateUtils.dateConversion(start);
			Date endDate = DateUtils.dateConversion(end);
			cinemaList = cinemaRepository.findAllByIsDeletedFalseAndIsActiveTrueAndReleasedDateBetween(startDate,
					endDate);
		} else {
			cinemaList = cinemaRepository.findAllByIsDeletedFalseAndIsActiveTrue();
		}
		logger.info("cinemaList===="+cinemaList);
		return modelMapper.map(cinemaList, new TypeToken<List<CinemaResponseDto>>() {}.getType());
	}

	@Override
	public String createBooking(@Valid CreateBookingDto createBookingDto) {
		logger.info("Create Booking DTO : {}", createBookingDto);
		Booking booking = modelMapper.map(createBookingDto, Booking.class);
		logger.info("Ticket Booking for movie");
		Booking bookingRecord = bookingRepository.save(booking);
		return bookingRecord.getBookingId();
	}

	@Override
	public List<BookingDetailsDto> ticketBookingDetails(String bookingId, String name, String contactNumber, String email) {
		List<Booking> bookingList=ticketBookingDetailsFromDB(bookingId,name,contactNumber,email);
		logger.info("Fetching Ticket Booking Details form DB");
		return modelMapper.map(bookingList, new TypeToken<List<BookingDetailsDto>>() {}.getType());
	}

	@Override
	public void editTicketBooking(EditBookingDto editBookingDto) {
		List<Booking> bookingList=ticketBookingForEdit(editBookingDto);
		logger.info("Fetching Ticket Booking Details form DB");
		if(bookingList.isEmpty()) {
			throw new BookingException(
					messages.getMessage("booking.details.is.not.present", new Object[0], new Locale("US")));
		}
		Booking booking=bookingList.get(1);
		modelMapper.map(editBookingDto,booking);
		logger.info("Ticket Boooking editing");
		bookingRepository.save(booking);
	}

	@Override
	public void cancleTicketBooking(String bookingId) {

		Optional<Booking> bookingOptional=bookingRepository.findByBookingIdAndIsCancelFalse(bookingId);
		if(!bookingOptional.isPresent()) {
			throw new BookingException(
					messages.getMessage("booking.details.is.not.present", new Object[0], new Locale("US")));
		}
		Booking booking=bookingOptional.get();
		booking.setIsCancel(true);
		bookingRepository.save(booking);
	}
	
	private List<Booking> ticketBookingDetailsFromDB(String bookingId, String name, String contactNumber,
			String email) {
		BookingSpecification bookingSpecification = new BookingSpecification();
		if (bookingId.equals(ALL)) {
			bookingSpecification.add("bookingId", bookingId, SearchOperation.EQUAL);
		}
		if (name.equals(ALL)) {
			bookingSpecification.add("name", name, SearchOperation.EQUAL);
		}
		if (contactNumber.equals(ALL)) {
			bookingSpecification.add("contactNumber", contactNumber, SearchOperation.EQUAL);
		}
		if (email.equals(ALL)) {
			bookingSpecification.add("email", email, SearchOperation.EQUAL);
		}
		bookingSpecification.add("cancel", false, SearchOperation.EQUAL);
		
		return bookingRepository.findAll(bookingSpecification);
	}

	private List<Booking> ticketBookingForEdit(EditBookingDto editBookingDto) {
		BookingSpecification bookingSpecification = new BookingSpecification();
		if (editBookingDto.getOptionalBookingId()!=null && (editBookingDto.getOptionalBookingId()).equals(ALL)) {
			bookingSpecification.add("bookingId", editBookingDto.getOptionalBookingId(), SearchOperation.EQUAL);
		}
		if (editBookingDto.getOptionalName()!=null && (editBookingDto.getOptionalName()).equals(ALL)) {
			bookingSpecification.add("name", editBookingDto.getOptionalName(), SearchOperation.EQUAL);
		}
		if (editBookingDto.getOptionalContactNumber()!=null && (editBookingDto.getOptionalContactNumber()).equals(ALL)) {
			bookingSpecification.add("contactNumber", editBookingDto.getOptionalContactNumber(), SearchOperation.EQUAL);
		}
		if (editBookingDto.getOptionalEmail()!=null && (editBookingDto.getOptionalEmail()).equals(ALL)) {
			bookingSpecification.add("email", editBookingDto.getOptionalEmail(), SearchOperation.EQUAL);
		}
		bookingSpecification.add("cancel", false, SearchOperation.EQUAL);
		
		return bookingRepository.findAll(bookingSpecification);
	}

	


}
