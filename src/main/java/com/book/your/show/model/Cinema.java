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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "cinema", schema = "book_your_show")
public class Cinema {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "movieName", unique = true)
	private String movieName;

	@Column(name = "movieId", unique = true)
	private Long movieId;

	@Column(name = "movieGenre")
	private String movieGenre;

	@Column(name = "releasedDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date releasedDate;

	@Column(name = "producer")
	private String producer;

	@Column(name = "director")
	private String director;

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
