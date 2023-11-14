package com.myHighSpeedRail.marc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.TicketOrder;
import com.myHighSpeedRail.marc.repository.TicketOrderRepository;

@Service
public class TicketOrderService {
	@Autowired
	private TicketOrderRepository tkoDao;
	
	public TicketOrder save(TicketOrder tcko) {
		return tkoDao.save(tcko);
	}
}
