package com.myHighSpeedRail.marc.service;

import java.util.List;
import java.util.Optional;

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
	public String getPaypalOrderIdByTicketOrderId(Integer tckid) {
		Optional<TicketOrder> tckodOpt = tkoDao.findById(tckid);
		if( tckodOpt.isEmpty()) {
			return null;
		}
		return tckodOpt.get().getPaypalOrderId();
	}
	public List<TicketOrder> findByMember(String memuuid){
		return tkoDao.findByMember(memuuid);
	}
	public Boolean registPaypalTicketOrder(Integer ticketOrderId , String paypalOrderId) {
		Optional<TicketOrder> tckodOpt = tkoDao.findById(ticketOrderId);
		if(tckodOpt.isPresent()) {
			TicketOrder tckod = tckodOpt.get();
			tckod.setPaypalOrderId(paypalOrderId);
			tkoDao.save(tckod );
			return true;
		}
		return false;
	}
}
