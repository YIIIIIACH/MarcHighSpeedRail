package com.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marc.model.TicketDiscount;
import com.marc.model.TicketDiscountRepository;


@RestController
public class TicketDiscountController {
	@Autowired
	private TicketDiscountRepository tdDao;
	
	@GetMapping(value="/getAllDiscount")
	public List<TicketDiscount> getAll(){
		return tdDao.findAll();
	}
	@PostMapping(value="/insertDiscount")
	public List<TicketDiscount> insert(@RequestBody TicketDiscount td){
		System.out.println(td.getTicketDiscountName());
		tdDao.save(td);
//		return new ArrayList<TicketDiscount>();
		return this.getAll();
	}
	@GetMapping(value="/getAllTicketDiscountType")
	public List<String> getAllType(){
		return tdDao.getAllDiscountType();
	}
}
