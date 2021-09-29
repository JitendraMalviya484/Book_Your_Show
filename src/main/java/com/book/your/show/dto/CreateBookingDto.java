package com.book.your.show.dto;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "Create Booking Request Dto")
public class CreateBookingDto implements Serializable {
	
	@NotEmpty(message= "movieName.not.empty")
	private String movieName;
	
	@NotEmpty(message= "chName.not.empty")
	private String chName;
	
	@NotNull(message= "date.not.empty")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private Date date;
	
	@NotEmpty(message= "name.not.empty")
	private String name;
	
	@NotEmpty(message= "contactNumber.not.empty")
	private String contactNumber;
	
	@NotEmpty(message= "email.not.empty")
	private String email;
	
}
