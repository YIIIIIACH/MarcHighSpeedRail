package com.myHighSpeedRail.johnny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.myHighSpeedRail.johnny.model.Order;
import com.myHighSpeedRail.johnny.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService oService;
	
	

}
