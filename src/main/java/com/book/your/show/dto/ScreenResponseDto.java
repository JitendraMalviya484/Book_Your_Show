package com.book.your.show.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.book.your.show.model.CinemaHall;
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
@ApiModel(value = " Screen Response Dto")
public class ScreenResponseDto implements Serializable{
	
	private Long id;
	
	private String chName;
	
	private Long cinemaHallId;
	
	private Long screenId;

	private Long sCapacity;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date sshowDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
	private Time sshowTime;
	
	private Boolean sBooked;
	
	private String movieName;
	
	private Boolean isActive;

	private CinemaHall cinemaHall ;
}
