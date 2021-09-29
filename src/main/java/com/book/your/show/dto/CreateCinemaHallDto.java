package com.book.your.show.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@ApiModel(value = "Create CinemaHall Request Dto")
public class CreateCinemaHallDto implements Serializable{
	
	@NotEmpty(message = "chName.not.empty")
	private String chName;

	@NotNull(message = "chId.not.null")
	private Long chId;

	@NotEmpty(message = "chCity.not.empty")
	private String chCity;

	@NotEmpty(message = "chState.not.empty")
	private String chState;

	@NotEmpty(message = "chCountry.not.empty")
	private String chCountry;
}
