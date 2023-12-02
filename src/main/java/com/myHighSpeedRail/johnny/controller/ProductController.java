package com.myHighSpeedRail.johnny.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.myHighSpeedRail.johnny.dto.DisplayProductDto;
import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
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
	
	
	
	//
	@GetMapping("/products")
	@ResponseBody
	public List<DisplayProductDto> getAllproduct(){
		// return List<DisplayProductDto> 
		List<Product> pList = pService.findAllProduct();
		List<DisplayProductDto> res= new ArrayList<DisplayProductDto>();
		int flag =0;
		for( Product p : pList) {
			DisplayProductDto tmp = new DisplayProductDto();
			tmp.productDescription= p.getProductDescription();
			tmp.productId= p.getProductId();
			tmp.productInventory= p.getProductInventory();
			tmp.productName=p.getProductName();
			tmp.productPrice=p.getProductPrice();
			tmp.productType=p.getProductType();
			p.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());
//			StringBuilder sb= new StringBuilder();
//			for(ProductPhotoSegment pps: p.getPhotoSegment()) {
//				sb.append(pps.getPhotoSegment().toString());
//				sb.
//			}
			StringBuilder sb = new StringBuilder();
			for(ProductPhotoSegment pps: p.getPhotoSegment()) {
//				sb.concat(pps.getPhotoSegment().toString());
//				String str = new String(byteArray1, 0, 3, StandardCharsets.UTF_8);
				sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
			}
			tmp.PhotoData= sb.toString();
			res.add(tmp);
			if(flag==0) {
//				System.out.println("show"+sb);
				flag=1;
			}
		}
		 return res;
	}
	
	@GetMapping("/product/page")  //取得product (分頁)
	@ResponseBody
	public Page<DisplayProductDto> showProductByPage (@RequestParam(name = "p", defaultValue = "1") Integer pageNumber){
			Page<Product> pList = pService.findbyPage(pageNumber);
			List<DisplayProductDto> res= new ArrayList<DisplayProductDto>();
			int flag =0;
			for( Product p : pList) {
				DisplayProductDto tmp = new DisplayProductDto();
				tmp.productDescription= p.getProductDescription();
				tmp.productId= p.getProductId();
				tmp.productInventory= p.getProductInventory();
				tmp.productName=p.getProductName();
				tmp.productPrice=p.getProductPrice();
				tmp.productType=p.getProductType();
				p.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());
			
				StringBuilder sb = new StringBuilder();
				for(ProductPhotoSegment pps: p.getPhotoSegment()) {
					sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
				}
				
				tmp.PhotoData= sb.toString();
				res.add(tmp);
				if(flag==0) {
					flag=1;
				}
			}
			Pageable pageable = PageRequest.of(pageNumber - 1, pList.getSize());
			Page<DisplayProductDto> pageResult = new PageImpl<>(res, pageable, pList.getTotalElements());
			
			return pageResult;
	}
	
	@DeleteMapping("/product/delete")  //透過id下架商品
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Integer id) {
		
		pService.deleteById(id);
		return new ResponseEntity<String>("商品移除成功",HttpStatus.OK);
	}
	
	@PutMapping("/product/update") // 僅更新商品基本資訊
	public ResponseEntity<String> updateProductById(@RequestBody Product p){
		Product updatedProduct = pService.UpdateProduct(p);
		return new ResponseEntity<String>("商品更新成功，商品ID: " + updatedProduct.getProductId() ,HttpStatus.OK);
	}
	
	@GetMapping("/product/findByNameLike")  //關鍵字搜尋
	@ResponseBody
	public List<DisplayProductDto> findProductByNameLike(@RequestParam("nameInput") String nameInput ){
		 List<Product> pList = pService.findProductByNameLike(nameInput);
		 List<DisplayProductDto> res= new ArrayList<DisplayProductDto>();
			int flag =0;
			for( Product p : pList) {
				DisplayProductDto tmp = new DisplayProductDto();
				tmp.productDescription= p.getProductDescription();
				tmp.productId= p.getProductId();
				tmp.productInventory= p.getProductInventory();
				tmp.productName=p.getProductName();
				tmp.productPrice=p.getProductPrice();
				tmp.productType=p.getProductType();
				p.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());

				StringBuilder sb = new StringBuilder();
				for(ProductPhotoSegment pps: p.getPhotoSegment()) {
					sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
				}
				tmp.PhotoData= sb.toString();
				res.add(tmp);
				if(flag==0) {

					flag=1;
				}
			}
			 return res;
	}
	
	// 依種類搜尋
	@GetMapping("/product/findByType") 
	@ResponseBody
	public List<DisplayProductDto> findProductByProductType(@RequestParam("selectType") String selectType){
		
		List<Product> pList = pService.findProductByProductType(selectType);
		List<DisplayProductDto> res= new ArrayList<DisplayProductDto>();
		int flag =0;
		for( Product p : pList) {
			DisplayProductDto tmp = new DisplayProductDto();
			tmp.productDescription= p.getProductDescription();
			tmp.productId= p.getProductId();
			tmp.productInventory= p.getProductInventory();
			tmp.productName=p.getProductName();
			tmp.productPrice=p.getProductPrice();
			tmp.productType=p.getProductType();
			p.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());

			StringBuilder sb = new StringBuilder();
			for(ProductPhotoSegment pps: p.getPhotoSegment()) {

				sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
			}
			tmp.PhotoData= sb.toString();
			res.add(tmp);
			if(flag==0) {
				flag=1;
			}
		}
		 return res;
		
	}
	
	// 依價格區間搜尋
	@GetMapping("/product/findByPrice")
	@ResponseBody
	public List<DisplayProductDto> findProdictByProductPrice
	(@RequestParam("firstPrice") Integer firstPrice, @RequestParam("secondPrice") Integer secondPrice){
		 List<Product> pList = pService.findProductByProductPrice(firstPrice, secondPrice);
		 List<DisplayProductDto> res= new ArrayList<DisplayProductDto>();
		 int flag =0;
			for( Product p : pList) {
				DisplayProductDto tmp = new DisplayProductDto();
				tmp.productDescription= p.getProductDescription();
				tmp.productId= p.getProductId();
				tmp.productInventory= p.getProductInventory();
				tmp.productName=p.getProductName();
				tmp.productPrice=p.getProductPrice();
				tmp.productType=p.getProductType();
				p.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());

				StringBuilder sb = new StringBuilder();
				for(ProductPhotoSegment pps: p.getPhotoSegment()) {

					sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
				}
				tmp.PhotoData= sb.toString();
				res.add(tmp);
				if(flag==0) {
					flag=1;
				}
			}
			return res;
	}
	
	@GetMapping("/product")
	public String toProduct() {
		return "showProduct";
	}
	
	@GetMapping("/product/add1")
	public String toAddProduct() {
		return "addProduct";
	}
	
}
