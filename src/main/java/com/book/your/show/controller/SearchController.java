package com.book.your.show.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.your.show.response.general.Response;
import com.book.your.show.service.SearchService;

import io.swagger.annotations.Api;

@Api(value = "Search Contollers API", tags = "Search APIs")
@RestController
@RequestMapping("/api/v1/cinema")
public class SearchController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SearchService searchService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/search")
	public Response screenById(@RequestParam(defaultValue = "all") String movieName,
			@RequestParam(defaultValue = "all") String chName,
			@RequestParam(defaultValue = "all") String sshowDate,
			@RequestParam(defaultValue = "all") String chCity ){
		logger.info("Screen record Fetching Successfully");
		return Response.ok().setPayload(searchService.screenById(movieName,chName,sshowDate,chCity));
	}

}
