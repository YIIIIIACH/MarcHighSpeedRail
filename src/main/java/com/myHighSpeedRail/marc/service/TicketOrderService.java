package com.myHighSpeedRail.marc.service;

import java.util.List;

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
	
	public TicketOrder findById(Integer tckid) {
		return tkoDao.findById(tckid).get();
	}
	
	public List<TicketOrder> findByMember(String memuuid){
		return tkoDao.findByMember(memuuid);
	}
}
