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



@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("/product/add") //新增商品
	@ResponseBody
	public String addProduct(@RequestBody Product product) {		
		pService.addProduct(product);		
		return "新增成功";
	}
	
	@GetMapping("/product")  //首頁顯示商品
	@ResponseBody
	public List<Product> findAllProduct(){		
		return pService.findAllProduct();
	}
	
	@DeleteMapping("/product/delete")  //透過id下架商品
	@ResponseBody
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Integer id) {
		pService.deleteById(id);
		return new ResponseEntity<String>("成功",HttpStatus.OK);
	}
	
	@PutMapping("/product/update")  //更新商品描述
	@ResponseBody
	public String updateProductDescriptionById(@RequestParam Integer id, @RequestParam String newDesc) {
		return pService.findByIdUpdateProductDesc(id, newDesc);
	}
	
	@GetMapping("/product/findByNameLike")  //關鍵字搜尋
	@ResponseBody
	public List<Product> findProductByNameLike(@RequestParam("nameInput") String nameInput ){
		return pService.findProductByNameLike(nameInput);
	}
	
	@GetMapping("/product/findByType") //依種類搜尋
	@ResponseBody
	public List<Product> findProductByProductType(@RequestParam("selectType") String selectType){
		return pService.findProductByProductType(selectType);
	}
	
	@GetMapping("/product/findByPrice") //依價格區間搜尋
	@ResponseBody
	public List<Product> findProdictByProductPrice
	(@RequestParam("firstPrice") Integer firstPrice, @RequestParam("secondPrice") Integer secondPrice){
		return pService.findProductByProductPrice(firstPrice, secondPrice);
	}
	
}
