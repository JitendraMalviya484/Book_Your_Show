package com.book.your.show.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "booking", schema = "book_your_show")
public class Booking {

	@Id
	@Column(unique = true, nullable = false)
	@GenericGenerator(name = "sequenceBookingId", strategy = "com.book.your.show.model.BookingIdGenerator")
	@GeneratedValue(generator = "sequenceBookingId", strategy = GenerationType.IDENTITY)
	private String bookingId;

	@Column(name = "movieName")
	private String movieName;

	@Column(name = "chName")
	private String chName;

	
	@Column(name = "date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private Date date;

	@Column(name = "name")
	private String name;

	@Column(name = "contactNumber")
	private String contactNumber;

	@Column(name = "email")
	private String email;
	
	@Column(name = "isCancel")
	private Boolean isCancel=false;
}
