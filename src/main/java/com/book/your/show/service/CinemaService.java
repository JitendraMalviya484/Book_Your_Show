package com.book.your.show.service;


import java.util.List;

import com.book.your.show.dto.CinemaResponseDto;
import com.book.your.show.dto.CreateCinemaDto;
import com.book.your.show.dto.UpdateCinemaDto;

public interface CinemaService {

	void createCinema(CreateCinemaDto cinemaDto);

	void updateCinema(UpdateCinemaDto cinemaDto);

	void deleteCinemaByName(String name);

	List<CinemaResponseDto> cinemaList();

	CinemaResponseDto cinemaByName(String name);


}
