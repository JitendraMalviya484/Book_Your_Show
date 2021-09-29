package com.book.your.show.service;


import java.util.List;

import javax.validation.Valid;

import com.book.your.show.dto.CreateScreenDto;
import com.book.your.show.dto.ScreenResponseDto;
import com.book.your.show.dto.UpdateScreenDto;

public interface ScreenService {

	void createScreen(CreateScreenDto screenDto);

	void updateScreen(@Valid UpdateScreenDto updateScreenDto);

	void deletedScreen(Long id);

	List<ScreenResponseDto> screenList();

	ScreenResponseDto screenById(Long id);

}
