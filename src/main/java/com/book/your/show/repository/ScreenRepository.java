package com.book.your.show.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.book.your.show.model.Screen;
@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long>,JpaSpecificationExecutor<Screen>{

	List<Screen> findAllByIsDeletedFalseAndIsActiveTrue();

	Optional<Screen> findByIdAndIsDeletedFalseAndIsActiveTrue(Long id);

	Screen findByCinemaHall_ChCity(String chCity);

}
