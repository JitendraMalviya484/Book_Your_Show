package com.book.your.show.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.book.your.show.model.CinemaHall;
@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long>,JpaSpecificationExecutor<CinemaHall>{


	Optional<CinemaHall> findByChNameAndIsDeletedFalseAndIsActiveTrue(String chName);

	Optional<CinemaHall> findByChIdAndIsDeletedFalseAndIsActiveTrue(Long chName);

	
	List<CinemaHall> findAllByIsDeletedFalseAndIsActiveTrue();

	
}
