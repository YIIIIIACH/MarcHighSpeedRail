package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.Booking;

import jakarta.transaction.Transactional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	@Query("update Booking set seat.seatId=:sid where bookingId=:bid")
	public void updateBookingSeat(Integer sid, Integer bid);
	
	@Query("from Booking where ticketOrder.ticketOrderId=:tckodid")
	public List<Booking> findByTicketOrderId(Integer tckodid);
	
	@Query("from Booking where memberToken=:memuuid")
	public List<Booking> findByMember(String memuuid);
	@Modifying
    @Transactional
	@Query("update Booking set memberToken=:desmembertoken where bookingId=:bid and memberToken=:srcmembertoken")
	public void allocateBooking(String srcmembertoken, String desmembertoken , Integer bid);
	@Modifying
    @Transactional
	@Query("update Booking set ticketQrcodeUrl=:url where bookingId=:bid")
	public void createBookingQRCode(Integer bid, String url);
}
