package com.book.your.show.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.book.your.show.model.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>,JpaSpecificationExecutor<Cinema>{

	Optional<Cinema> findByMovieNameAndIsDeletedFalseAndIsActiveTrue(String movieName);

	List<Cinema> findAllByIsDeletedFalseAndIsActiveTrue();

	List<Cinema> findAllByIsDeletedFalseAndIsActiveTrueAndReleasedDateBetween(Date startDate, Date endDate);

	//Optional<Cinema> findByMovieNameAndMovieIdAndIsDeletedFalseAndIsActiveTrue(String movieName, Long movieId);

	Optional<Cinema> findByMovieNameAndIsDeletedFalseAndIsActiveTrueOrMovieIdAndIsDeletedFalseAndIsActiveTrue(
			String movieName, Long movieId);


}
