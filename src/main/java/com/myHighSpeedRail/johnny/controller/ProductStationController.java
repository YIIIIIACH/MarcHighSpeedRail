package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.service.ProductStationService;

@Controller
public class ProductStationController {
	@Autowired
	private ProductStationService psServ;
	
	@GetMapping("/getpbystid")
	public @ResponseBody List<Product> getpbystid(@RequestParam(value="sid") Integer sid){
		return psServ.getProductByStaitonId(sid);
	}
}
