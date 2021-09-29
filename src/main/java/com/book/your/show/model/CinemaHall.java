package com.book.your.show.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cinemaHall", schema = "book_your_show")
public class CinemaHall {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long chId;

	@Column(name = "chName",unique = true)
	private String chName;

	@Column(name = "chCity")
	private String chCity;

	@Column(name = "chState")
	private String chState;

	@Column(name = "chCountry")
	private String chCountry;

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

}
