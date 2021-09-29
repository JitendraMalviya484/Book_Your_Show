package com.book.your.show.service;

import java.util.List;

import javax.validation.Valid;

import com.book.your.show.dto.CinemaHallResponseDto;
import com.book.your.show.dto.CreateCinemaHallDto;
import com.book.your.show.dto.UpdateCinemaHallDto;

public interface CinemaHallService {

	void createCinema(CreateCinemaHallDto cinemaHallDto);

	void updateCinemaHall(@Valid UpdateCinemaHallDto updateCinemaHallDto);

	void deleteCinemaHall(Long chId);

	List<CinemaHallResponseDto> cinemaHallList();

	CinemaHallResponseDto cinemaHall(Long chId);

}
