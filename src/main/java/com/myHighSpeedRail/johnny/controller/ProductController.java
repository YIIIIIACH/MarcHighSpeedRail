package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring6.util.SpringRequestUtils;

import com.myHighSpeedRail.johnny.dto.ProductAndProductPhotoDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhoto;
import com.myHighSpeedRail.johnny.service.ProductService;



@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	
	@PostMapping("/product/add") //新增商品
	@ResponseBody
	public ResponseEntity<String> addProduct(@RequestBody ProductAndProductPhotoDto productDto) {	
//		Product p = new Product();
//		BeanUtils.copyProperties(productDto, p);
		try{
			Product aa = pService.addProduct(productDto);
			return ResponseEntity.ok("商品成功新增，商品ID : " + aa.getProductId() + "商品名稱 : " + aa.getProductName());
		
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("新增商品失敗: " + e.getMessage());
		}
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
		return new ResponseEntity<String>("商品移除成功",HttpStatus.OK);
	}
	
//	@PutMapping("/product/update")  //更新商品
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
	
	public ResponseEntity<String>updateProductById(@RequestBody Product product){
		
		return null;
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
