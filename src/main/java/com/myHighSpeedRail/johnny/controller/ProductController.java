package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.service.ProductService;

import jakarta.transaction.Transactional;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("/product/add")
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		
		return pService.addProduct(product);

	}
	
	@GetMapping("/product")
	@ResponseBody
	public List<Product> findAllProduct(){
		
		return pService.findAllProduct();
	}
	
	@DeleteMapping("/product/delete")
	@ResponseBody
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Integer id) {
		pService.deleteById(id);
		return new ResponseEntity<String>("成功",HttpStatus.OK);
	}
	
	@PutMapping("/product/update")
	@Transactional
	@ResponseBody
	public String updateProductDescriptionById(@RequestParam Integer id, @RequestParam String newDesc) {
		return pService.findByIdUpdateProductDesc(id, newDesc);
	}
}
