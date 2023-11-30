package com.myHighSpeedRail.johnny.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
//	回傳是 ResponseEntity, 就不需要加@ResponseBody 
	@PostMapping("/product/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductAndPhotoSegmentDto pappDto) {	
		try{
//			Product p = new Product();
//			BeanUtils.copyProperties(pappDto, p);
			
			Product pp = pService.addProduct(pappDto);
			return ResponseEntity.ok("商品新增成功，商品ID: " + pp.getProductId() + ", 商品名稱 : " + pp.getProductName());
		
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增商品失敗: " + e.getMessage());
		}
	}
	
//	@PostMapping("/product/add") //只新增單筆 product 基本資訊
//	public ResponseEntity<String> addProduct(@RequestBody Product p) {	
//		try{
////			Product p = new Product();
////			BeanUtils.copyProperties(pappDto, p);
//			Product pp = pService.addProduct(p);
//			return ResponseEntity.ok("商品新增成功，商品ID: " + pp.getProductId() + ", 商品名稱 : " + pp.getProductName());
//		
//		}catch(Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增商品失敗: " + e.getMessage());
//		}
//	}
	
	@PostMapping("/product/addAll") // 新增多筆 product
	@ResponseBody
	public List<Product> addAllProduct(@RequestBody List<Product> pList){
		 return pService.addAllProduct(pList);
	}
	
	@GetMapping("/product")
	public String toProduct() {
		return "showProduct";
	}
	
	@GetMapping("/product/add1")
	public String toAddProduct() {
		return "addProduct";
	}
	
	@GetMapping("/product/page")  //取得product (分頁)
	public ResponseEntity<Page<Product>> showProductByPage (@RequestParam(name = "p", defaultValue = "1") Integer pageNumber){
			Page<Product> products = pService.findbyPage(pageNumber);
			
			return ResponseEntity.ok(products);	
	}
	
	@DeleteMapping("/product/delete")  //透過id下架商品
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
		
		List<Product> pList = pService.findProductByProductType(selectType);
		return pList;
	}
	
	@GetMapping("/product/findByPrice") //依價格區間搜尋
	@ResponseBody
	public List<Product> findProdictByProductPrice
	(@RequestParam("firstPrice") Integer firstPrice, @RequestParam("secondPrice") Integer secondPrice){
		return pService.findProductByProductPrice(firstPrice, secondPrice);
	}
	
}
