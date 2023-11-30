package com.myHighSpeedRail.marc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bDao;
	public Booking save(Booking b) {
		return bDao.save(b);
	}
	public List<Booking> findByTicketOrderId(Integer tckid){
		return bDao.findByTicketOrderId(tckid);
	}
	public List<Booking> saveAll(List<Booking> bList) {
		return bDao.saveAll(bList);
	}
	public List<Booking> findByMember(String memuuid){
		return bDao.findByMember(memuuid);
	}
	
	public Integer allocateBooking( String srcMemberToken, String desMemberToken, Integer bookingId) {
		return bDao.allocateBooking(srcMemberToken, desMemberToken, bookingId);
	}
}
