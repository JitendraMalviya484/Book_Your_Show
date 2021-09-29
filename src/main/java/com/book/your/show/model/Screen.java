package com.book.your.show.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "screen", schema = "book_your_show")
public class Screen {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "chName")
	private String chName;

	@Column(name = "screenId")
	private Integer screenId;

	@Column(name = "sCapacity")
	private Long sCapacity;

	@Column(name = "sShowDate")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date sshowDate;

	@Column(name = "sshowTime")
	@JsonFormat(pattern ="HH:mm:ss")
	private Time sshowTime;

	@Column(name = "sBooked")
	private Boolean sBooked=false;

	@Column(name = "movieName")
	private String movieName;

	@CreationTimestamp
	@Column(name = "createOn")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "updatedOn")
	private Date updatedOn;

	@Column(name = "isDeleted")
	private Boolean isDeleted = false;

	@Column(name = "isActive")
	private Boolean isActive = true;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private CinemaHall cinemaHall ;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Cinema cinema;

}
