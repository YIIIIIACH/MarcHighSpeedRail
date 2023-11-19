package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring6.util.SpringRequestUtils;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhoto;
import com.myHighSpeedRail.johnny.service.ProductPhotoService;
import com.myHighSpeedRail.johnny.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("/product/add")
	@ResponseBody
	public ResponseEntity<String> addProduct(@RequestBody ProductAndPhotoDto pappDto) {	
		try{
			Product p = new Product();
			BeanUtils.copyProperties(pappDto, p);
			Product pp = pService.addProduct(pappDto);
			return ResponseEntity.ok("商品新增成功，商品ID: " + pp.getProductId() + ", 商品名稱 : " + p.getProductName());
		
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增商品失敗: " + e.getMessage());
		}
	}
	
	@GetMapping("/product")  
	@ResponseBody
	public Page<Product> showProducts (@RequestParam(name = "page", defaultValue = "1") Integer pageNumber, Model m){
			Page<Product> page = pService.findbyPage(pageNumber);
		
			m.addAttribute("page", page);	
			m.addAttribute("totalPages", page.getTotalPages());
			
			return page;	
	}
	
	@DeleteMapping("/product/delete")  //透過id下架商品
	@ResponseBody
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Integer id) {
		pService.deleteById(id);
		return new ResponseEntity<String>("商品移除成功",HttpStatus.OK);
	}
	
//	@PutMapping("/product/update")  
//	@ResponseBody
//	public ResponseEntity<String> updateProductById(
//			@RequestParam Integer id, 
//			@RequestParam String newDesc, 
//			@RequestParam String newName, 
//			@RequestParam Integer newPrice,
//			@RequestParam String newType, 
//			@RequestParam Integer newInventory,
//			@RequestParam String newMimeType,
//			@RequestParam String newPhotoPath) {
//		
//		Product updatedProduct = pService.UpdateProduct(id, newName, newDesc, newPrice, newType, newInventory);
//		ppService.updatePhoto(id, newMimeType, newPhotoPath);
//		return new ResponseEntity<String>("商品更新成功，商品ID : " + updatedProduct.getProductId() ,HttpStatus.OK);
//	}
	
	@PutMapping("/product/update") // 僅更新商品基本資訊
	@ResponseBody
	public ResponseEntity<String> updateProductById(@RequestBody Product p){
		Product updatedProduct = pService.UpdateProduct(p);
		return new ResponseEntity<String>("商品更新成功，商品ID: " + updatedProduct.getProductId() ,HttpStatus.OK);
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
