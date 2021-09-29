package com.book.your.show.service;

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

import com.book.your.show.dto.CinemaHallResponseDto;
import com.book.your.show.dto.CreateCinemaHallDto;
import com.book.your.show.dto.UpdateCinemaHallDto;
import com.book.your.show.model.CinemaHall;
import com.book.your.show.repository.CinemaHallRepository;
import com.book.your.show.response.exception.CinemaHallException;

@Service
public class CinemaHallServiceImpl implements CinemaHallService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CinemaHallRepository cinemaHallRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messages;
	
	
	@Override
	public void createCinema(CreateCinemaHallDto cinemaHallDto) {
		logger.info("Cinema Hall DTO {} :",cinemaHallDto);
		checkCinemaHallIsExist(cinemaHallDto.getChId());
		CinemaHall cinemaHall= modelMapper.map(cinemaHallDto,CinemaHall.class);
		logger.info("Cinema Hall record created successfully");
		cinemaHallRepository.save(cinemaHall);
		
	}
	
	
	@Override
	public void updateCinemaHall(@Valid UpdateCinemaHallDto updateCinemaHallDto) {
		logger.info("update Cinema Hall DTO {} :",updateCinemaHallDto);
		CinemaHall cinemaHall=checkCinemaHall(updateCinemaHallDto.getChId());
		modelMapper.map(updateCinemaHallDto, cinemaHall);
		cinemaHallRepository.save(cinemaHall);
	}

	
	@Override
	public void deleteCinemaHall(Long chId) {
		CinemaHall cinemaHall=checkCinemaHall(chId);
		cinemaHall.setIsDeleted(true);
		logger.info("deleting cinema Hall record");
		cinemaHallRepository.save(cinemaHall);
		
	}
	
	@Override
	public List<CinemaHallResponseDto> cinemaHallList() {
		List<CinemaHall> cinemaHallList=cinemaHallRepository.findAllByIsDeletedFalseAndIsActiveTrue();
		logger.info("Fetching CinemaHall List");
		return  modelMapper.map(cinemaHallList, new TypeToken<List<CinemaHallResponseDto>>() {}.getType());
	}
	
	private CinemaHall checkCinemaHall(Long chName) {
		Optional<CinemaHall> cinemaHallOptional=cinemaHallRepository.findByChIdAndIsDeletedFalseAndIsActiveTrue(chName);
		if(cinemaHallOptional.isPresent()) {
			logger.info("Cinema Hall is already present");
			return cinemaHallOptional.get();
		}else {
			throw new CinemaHallException(
					messages.getMessage("cinema.Hall.not.present.error", new Object[0], new Locale("US")));
		}
	}


	private void checkCinemaHallIsExist(Long chName) {
		Optional<CinemaHall> cinemaHallOptional=cinemaHallRepository.findByChIdAndIsDeletedFalseAndIsActiveTrue(chName);
		if(cinemaHallOptional.isPresent()) {
			logger.info("Cinema Hall is already present");
			throw new CinemaHallException(
					messages.getMessage("cinema.Hall.present.error", new Object[0], new Locale("US")));
		}
		
		
	}


	@Override
	public CinemaHallResponseDto cinemaHall(Long chId) {
		Optional<CinemaHall> cinemaHallOptional=cinemaHallRepository.findByChIdAndIsDeletedFalseAndIsActiveTrue(chId);
		if(cinemaHallOptional.isPresent()) {
			logger.info("Cinema Hall is  present");
			return modelMapper.map(cinemaHallOptional.get(), CinemaHallResponseDto.class);
		}
		return null;
	}


	


	


	

}
