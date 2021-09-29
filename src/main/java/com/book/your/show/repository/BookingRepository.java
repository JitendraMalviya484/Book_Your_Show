package com.book.your.show.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.book.your.show.model.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, String>, JpaSpecificationExecutor<Booking>{


	Optional<Booking> findByBookingIdAndIsCancelFalse(String bookingId);

}
