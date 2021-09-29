package com.book.your.show.service;

import java.util.List;

import com.book.your.show.dto.CinemaResponseDto;

public interface SearchService {

	List<CinemaResponseDto> screenById(String movieName, String chName, String sshowDate, String chCity);

}
