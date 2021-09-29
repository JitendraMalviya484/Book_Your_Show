package com.book.your.show.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.book.your.show.dto.CinemaResponseDto;
import com.book.your.show.dto.CreateCinemaDto;
import com.book.your.show.dto.UpdateCinemaDto;
import com.book.your.show.model.Cinema;
import com.book.your.show.repository.CinemaRepository;
import com.book.your.show.response.exception.CinemaCustomException;

@Service
public class CinemaServiceImpl implements CinemaService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CinemaRepository cinemaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messages;
	
	@Override
	public void createCinema(CreateCinemaDto cinemaDto) {
		logger.info("Cinema Request DTO : {} ",cinemaDto);
		checkCinemaNameIsPresent(cinemaDto.getMovieName(),cinemaDto.getMovieId());
		Cinema cinema=modelMapper.map(cinemaDto, Cinema.class);
		cinemaRepository.save(cinema);
	}

	@Override
	public void updateCinema(UpdateCinemaDto cinemaDto) {
		logger.info("update Cinema Request DTO : {} ", cinemaDto);
		Cinema cinema = checkCinemaName(cinemaDto.getMovieName());
		modelMapper.map(cinemaDto, cinema);
		cinemaRepository.save(cinema);
	}

	@Override
	public void deleteCinemaByName(String name) {
		Cinema cinema = checkCinemaName(name);
		cinema.setIsDeleted(true);
		logger.info("Cinema record : {}", cinema);
		cinemaRepository.save(cinema);
	}

	@Override
	public List<CinemaResponseDto> cinemaList() {
		List<Cinema> cinemaList=cinemaRepository.findAllByIsDeletedFalseAndIsActiveTrue();
		logger.info("Fetching all records of Cinema");
		return modelMapper.map(cinemaList, new TypeToken<List<CinemaResponseDto>>() {}.getType());
	}

	@Override
	public CinemaResponseDto cinemaByName(String name) {
		logger.info("Fetching cinema record from DB");
		return modelMapper.map(checkCinemaName(name),CinemaResponseDto.class);
	}

	public Cinema checkCinemaName(String name) {
		Optional<Cinema> cinemaOptional = cinemaRepository.findByMovieNameAndIsDeletedFalseAndIsActiveTrue(name);
		if (cinemaOptional.isPresent()) {
			logger.info("Cinema record is present");
			return cinemaOptional.get();
		} else {
			logger.info("Cinema record is not present");
			throw new CinemaCustomException(
					messages.getMessage("cinema.movie.name.not.present.error", new Object[0], new Locale("US")));
		}
	}
	
	private void checkCinemaNameIsPresent(String movieName, Long movieId) {
		Optional<Cinema> cinemaOptional = cinemaRepository.findByMovieNameAndIsDeletedFalseAndIsActiveTrueOrMovieIdAndIsDeletedFalseAndIsActiveTrue(movieName,movieId);
		if (cinemaOptional.isPresent()) {
			logger.info("Cinema Already record is present");
			if(movieName.equals(cinemaOptional.get().getMovieName())) {
				throw new CinemaCustomException(
						messages.getMessage("cinema.movie.name.present.error", new Object[0], new Locale("US")));
			}else {
				throw new CinemaCustomException(
						messages.getMessage("cinema.movie.id.present.error", new Object[0], new Locale("US")));
			}
		}
	}

}
