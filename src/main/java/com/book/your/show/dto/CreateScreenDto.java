package com.book.your.show.dto;

import java.io.Serializable;
import java.sql.Time;

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
@ApiModel(value = "Create Screen Request Dto")
public class CreateScreenDto implements Serializable{
	
	@NotEmpty(message= "chName.not.empty")
	private String chName;
	
	@NotNull(message = "chId.not.null")
	private Long cinemaHallId;
	
	@NotNull(message= "screenId.not.null")
	private Long screenId;

	@NotNull(message= "sCapacity.not.null")
	private Long sCapacity;

	@NotNull(message="sshowDate.not.null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String sshowDateString;
	
	@NotNull(message="sshowTime.not.null")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
	private Time sshowTime;
	
	@NotNull(message= "sBooked.not.empty")
	private Boolean sBooked;
	
	@NotEmpty(message= "movieName.not.empty")
	private String movieName;

	

}
