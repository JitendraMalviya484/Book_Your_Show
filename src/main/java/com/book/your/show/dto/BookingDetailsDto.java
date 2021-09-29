package com.book.your.show.dto;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "Booking Details Response Dto")
public class BookingDetailsDto implements Serializable{

	private String bookingId;

	private String movieName;

	private String chName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private Date date;

	private String name;

	private String contactNumber;

	private String email;
}
