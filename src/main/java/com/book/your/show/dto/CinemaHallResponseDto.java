package com.book.your.show.dto;

import java.io.Serializable;

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
@ApiModel(value = "Cinema Hall Response Dto")
public class CinemaHallResponseDto implements Serializable{

	private Long chId;
	private String chName;
	private String chCity;
	private String chState;
	private String chCountry;
	private Boolean isActive;
}
