package com.myHighSpeedRail.marc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.repository.BookingRepository;

import jakarta.persistence.PersistenceException;

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
	
	public void allocateBooking( String srcMemberToken, String desMemberToken, Integer bookingId) {
		bDao.allocateBooking(srcMemberToken, desMemberToken, bookingId);
	}
	
	public void createBookingQRcode(Integer bookingId ,String url)throws  PersistenceException{
		Optional<Booking> bOp = bDao.findById(bookingId);
		if(bOp.isPresent()) {
			if( bOp.get().getTicketQrcodeUrl()==null)
				bDao.createBookingQRCode(bookingId, url);
			System.out.println("already have qrcode of booking id : "+bookingId);
			return;
		}
	}
	
	public Boolean verifyBookingQRcode( Integer bookingid) {
		Optional<Booking> bOpt = bDao.findById(bookingid);
		if( bOpt.isEmpty()) {
			return false;
		}
		if( "已分配座位".equals(bOpt.get().getStatus()) && bOpt.get().getTicketQrcodeUrl()!= null) {
			bOpt.get().setStatus("已使用");
			bDao.save( bOpt.get());
			return true;
		}
		return false;
	}
}
