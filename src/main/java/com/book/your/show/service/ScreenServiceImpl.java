package com.book.your.show.service;

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

import com.book.your.show.dto.CreateScreenDto;
import com.book.your.show.dto.ScreenResponseDto;
import com.book.your.show.dto.UpdateScreenDto;
import com.book.your.show.model.Cinema;
import com.book.your.show.model.CinemaHall;
import com.book.your.show.model.Screen;
import com.book.your.show.repository.CinemaHallRepository;
import com.book.your.show.repository.ScreenRepository;
import com.book.your.show.response.exception.ScreenException;
import com.book.your.show.util.DateUtils;

@Service
public class ScreenServiceImpl implements ScreenService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ScreenRepository screenRepository;

	@Autowired
	private CinemaHallRepository cinemaHallRepository;
	
	@Autowired
	private CinemaServiceImpl cinemaServiceImpl;

	@Autowired
	private MessageSource messages;

	@Override
	public void createScreen(CreateScreenDto screenDto) {
		logger.info("Screen Request DTO : {} ", screenDto);
		CinemaHall cinemaHall = checkCinemaHallId(screenDto.getCinemaHallId());
		Cinema cinema=cinemaServiceImpl.checkCinemaName(screenDto.getMovieName());
		Screen screen = modelMapper.map(screenDto, Screen.class);
		Date sshowDate=DateUtils.dateConversionOnlyForDate(screenDto.getSshowDateString());
		screen.setSshowDate(sshowDate);
		screen.setCinemaHall(cinemaHall);
		screen.setCinema(cinema);
		logger.info("Screen record created");
		screenRepository.save(screen);
	}

	@Override
	public void updateScreen(@Valid UpdateScreenDto updateScreenDto) {
		logger.info("Update Screen Dto :{} ", updateScreenDto);
		CinemaHall cinemaHall = checkCinemaHallId(updateScreenDto.getCinemaHallId());
		Optional<Screen> screenOptonal = screenRepository
				.findByIdAndIsDeletedFalseAndIsActiveTrue(updateScreenDto.getId());
		if (screenOptonal.isPresent()) {
			Screen screen = screenOptonal.get();
			Cinema cinema=cinemaServiceImpl.checkCinemaName(updateScreenDto.getMovieName());
			modelMapper.map(updateScreenDto, screen);
			Date sshowDate=DateUtils.dateConversionOnlyForDate(updateScreenDto.getSshowDateString());
			screen.setSshowDate(sshowDate);
			screen.setCinemaHall(cinemaHall);
			screen.setCinema(cinema);
			logger.info("Screen record Updating ");
			screenRepository.save(screen);
		} else {
			throw new ScreenException(
					messages.getMessage("Screen.id.not.present.error", new Object[0], new Locale("US")));
		}
	}

	@Override
	public List<ScreenResponseDto> screenList() {
		List<Screen> screenOptonal = screenRepository.findAllByIsDeletedFalseAndIsActiveTrue();
		logger.info("Fetched All screen records from DB");
		return modelMapper.map(screenOptonal, new TypeToken<List<ScreenResponseDto>>() {
		}.getType());
	}

	@Override
	public ScreenResponseDto screenById(Long id) {
		Optional<Screen> screenOptonal = screenRepository.findByIdAndIsDeletedFalseAndIsActiveTrue(id);
		if (screenOptonal.isPresent()) {
			return modelMapper.map(screenOptonal.get(), ScreenResponseDto.class);
		}
		return null;
	}

	@Override
	public void deletedScreen(Long id) {
		Optional<Screen> screenOptonal = screenRepository.findByIdAndIsDeletedFalseAndIsActiveTrue(id);
		if (screenOptonal.isPresent()) {
			logger.info("Deleteing Screen Dto :{} ");
			Screen screen = screenOptonal.get();
			screen.setIsDeleted(true);
			screenRepository.save(screen);
		} else {
			throw new ScreenException(
					messages.getMessage("Screen.id.not.present.error", new Object[0], new Locale("US")));
		}

	}

	private CinemaHall checkCinemaHallId(Long cinemaHallId) {
		Optional<CinemaHall> cinemaHallOptional = cinemaHallRepository
				.findByChIdAndIsDeletedFalseAndIsActiveTrue(cinemaHallId);
		if (!cinemaHallOptional.isPresent()) {
			throw new ScreenException(
					messages.getMessage("Cinema.id.not.present.error", new Object[0], new Locale("US")));
		}
		return cinemaHallOptional.get();
	}

}
