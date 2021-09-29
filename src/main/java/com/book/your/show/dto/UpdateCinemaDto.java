package com.book.your.show.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@ApiModel(value = "Update Cinema Request Dto")
public class UpdateCinemaDto implements Serializable{
	
	@NotEmpty(message= "movieName.not.empty")
	private String movieName;
	
	@NotNull(message= "movieId.not.null")
	private Long movieId;
	
	@NotEmpty(message= "movieGenre.not.empty")
	private String movieGenre;

	@NotEmpty(message= "releasedDate.not.empty")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private String releasedDate;
	
	@NotEmpty(message= "producer.not.empty")
	private String producer;
	
	@NotEmpty(message= "director.not.empty")
	private String director;
	
	private Boolean isActive;

}
