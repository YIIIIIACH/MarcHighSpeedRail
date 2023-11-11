package com.myHighSpeedRail.marc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.repository.TicketDiscountRepository;
@Service
public class TicketDiscountSerivce {

	@Autowired
	private TicketDiscountRepository tdDao;
	
	public List<TicketDiscount> findAll(){
		return tdDao.findAll();
	}
	@PostMapping(value="/insertDiscount")
	public List<TicketDiscount> insert(@RequestBody TicketDiscount td){
		System.out.println(td.getTicketDiscountName());
		tdDao.save(td);
//		return new ArrayList<TicketDiscount>();
		return findAll();
	}
	public List<String> getAllType(){
		return tdDao.getAllDiscountType();
	}
	
	public void save(TicketDiscount td) {
		tdDao.save(td);
	}
	
	public TicketDiscount findById( Integer tid) {
		return tdDao.findById(tid).get();
	}
	
	public List<TicketDiscount> findByDiscountType(String discountType){
		return tdDao.findByDiscountType(discountType);
	}
}
