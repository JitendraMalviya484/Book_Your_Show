package com.book.your.show.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.your.show.dto.CinemaResponseDto;
import com.book.your.show.model.Cinema;
import com.book.your.show.model.Screen;
import com.book.your.show.repository.ScreenRepository;
import com.book.your.show.repository.spec.SearchOperation;
import com.book.your.show.repository.spec.SearchSpecification;
import com.book.your.show.util.DateUtils;

@Service
public class SearchServiceImpl implements SearchService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static String ALL = "all";

	@Autowired
	private ScreenRepository screenRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CinemaResponseDto> screenById(String movieName, String chName, String sshowDate, String chCity) {
		logger.info("movieName  :{} ",movieName);
		logger.info("chName  :{} ",chName);
		logger.info("sshowDate  :{} ",sshowDate);
		logger.info("chCity  :{} ",chCity);
		SearchSpecification searchSpecification = new SearchSpecification();
		if (!movieName.equals(ALL)) {
			searchSpecification.add("movieName", movieName, SearchOperation.EQUAL);
		}
		if (!chName.equals(ALL)) {
			searchSpecification.add("chName", chName, SearchOperation.EQUAL);
		}
		if (!sshowDate.equals(ALL)) {
			searchSpecification.add("sshowDate", DateUtils.dateConversionOnlyForDate(sshowDate), SearchOperation.EQUAL);
		}
		if (!chCity.equals(ALL)) {
			searchSpecification.setCriteriaValue("cinemaHall_chCity", chCity, SearchOperation.JOIN);
		}

		searchSpecification.add("isActive", true, SearchOperation.EQUAL);
		List<Screen> screenList = screenRepository.findAll(searchSpecification);
		if (screenList.size() > 0) {
			Set<Cinema> cinemaSet = new HashSet<>();
			for (Screen screen : screenList) {
				cinemaSet.add(screen.getCinema());
			}
			return modelMapper.map(cinemaSet, new TypeToken<List<CinemaResponseDto>>() {}.getType());
		}

		return null;
	}

}
