package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
