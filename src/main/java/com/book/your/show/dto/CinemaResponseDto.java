package com.book.your.show.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@ApiModel(value = "Cinema Response Dto")
public class CinemaResponseDto implements Serializable{
	
	private Long Id;
	
	private String movieName;
	
	private Long movieId;
	
	private String movieGenre;

	@JsonFormat(pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private String releasedDate;
	
	private String producer;
	
	private String director;
	
	private Boolean isActive;

}
