package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.marc.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

	@Query("update Booking set seat.seatId=:sid where bookingId=:bid")
	public void updateBookingSeat(Integer sid, Integer bid);
	
	@Query("from Booking where ticketOrder.ticketOrderId=:tckodid")
	public List<Booking> findByTicketOrderId(Integer tckodid);
	
	@Query("from Booking where memberToken=:memuuid")
	public List<Booking> findByMember(String memuuid);
}
