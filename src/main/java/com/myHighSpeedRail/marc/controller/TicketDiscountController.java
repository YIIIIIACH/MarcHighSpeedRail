package com.myHighSpeedRail.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.repository.TicketDiscountRepository;
import com.myHighSpeedRail.marc.service.TicketDiscountService;

@CrossOrigin
@RestController
public class TicketDiscountController {
	@Autowired
	private TicketDiscountService tdServ;
	@GetMapping(value="/getAllDiscount")
	public List<TicketDiscount> getAll(){
		return tdServ.findAll();
	}
	@PostMapping(value="/insertDiscount")
	public List<TicketDiscount> insert(@RequestBody TicketDiscount td){
		System.out.println(td.getTicketDiscountName());
		tdServ.save(td);
		return this.getAll();
	}
	@GetMapping(value="/getAllTicketDiscountType")
	public List<String> getAllType(){
		return tdServ.getAllType();
	}
	@GetMapping("/getTicketDiscountByTypeName")
	public List<TicketDiscount> getByDiscountTypeName(@RequestParam String discName){
		return tdServ.findByDiscountType(discName);
	}
}
