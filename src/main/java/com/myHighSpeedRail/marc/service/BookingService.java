package com.myHighSpeedRail.marc.service;

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
}
