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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.PostProductDto;
import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.model.ProductTrackingList;
import com.myHighSpeedRail.johnny.service.ProductPhotoSegmentService;
import com.myHighSpeedRail.johnny.service.ProductService;
import com.myHighSpeedRail.johnny.service.ProductTrackingListService;
import com.myHighSpeedRail.johnny.util.ConfirmIsTracking;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	@Autowired
	private ProductPhotoSegmentService ppsServ;
	
	@Autowired
	private ProductTrackingListService ptlServ;
	
	
	@PostMapping("/product/add")
	public ResponseEntity<String> addProduct(@RequestBody PostProductDto postDto) {	
		try{
//			BeanUtils.copyProperties(postDto, p);
			
			Product addedProduct = pService.addProduct(postDto);// 先儲存商品, identity建立一個productId
			ppsServ.savePhoto(postDto, addedProduct.getProductId()); //呼叫photoService, 傳入DTO & 建立好的productId
			
			return ResponseEntity.ok("商品新增成功，商品ID: " + addedProduct.getProductId() + ", 商品名稱 : " + addedProduct.getProductName());
		
		}catch(Exception e) {
			e.printStackTrace();
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
	
	// 新增多筆 product
	@PostMapping("/product/addAll") 
	@ResponseBody
	public List<Product> addAllProduct(@RequestBody List<Product> pList){
		 return pService.addAllProduct(pList);
	}
	
	
	
	// 取得所有商品資訊
	@GetMapping("/products")
	@ResponseBody
	public List<ProductAndPhotoSegmentDto> getAllproduct(@RequestParam("mId") String mId){
		return getTrackingProductID(mId);	
	}
	
	//確認商品是否已追蹤
	public List<ProductAndPhotoSegmentDto> getTrackingProductID(String mId){
		List<ProductTrackingList> trackings = ptlServ.findByMemberId(mId);
		List<ProductAndPhotoSegmentDto> products = pService.findAllProduct();
		
		for (ProductAndPhotoSegmentDto p : products) {
	        p.isTracking = false;
	    }
		
		for(ProductTrackingList t : trackings) {
			for(ProductAndPhotoSegmentDto p : products) {
				if(t.getProduct().getProductId() == p.productId) {
					p.isTracking = true;
				}
			}
		}
		return products;
		
	}
	
	//取得product (分頁)
	@GetMapping("/product/page")  
	@ResponseBody
	public Page<ProductAndPhotoSegmentDto> showProductByPage (@RequestParam(name = "p", defaultValue = "1") Integer pageNumber){
			Page<Product> pList = pService.findbyPage(pageNumber);
			List<ProductAndPhotoSegmentDto> res= new ArrayList<ProductAndPhotoSegmentDto>();
			int flag =0;
			for( Product p : pList) {
				ProductAndPhotoSegmentDto tmp = new ProductAndPhotoSegmentDto();
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
				
				tmp.photoData= sb.toString();
				res.add(tmp);
				if(flag==0) {
					flag=1;
				}
			}
			Pageable pageable = PageRequest.of(pageNumber - 1, pList.getSize());
			Page<ProductAndPhotoSegmentDto> pageResult = new PageImpl<>(res, pageable, pList.getTotalElements());
			
			return pageResult;
	}
	
	
	@GetMapping("/api/product/{id}")
	@ResponseBody
	public ProductAndPhotoSegmentDto findProductById(@PathVariable("id") Integer id) {
		
		return pService.findProductById(id);
	}
	
	//透過id下架商品
	@DeleteMapping("/product/delete")  
	public ResponseEntity<String> deleteProductById(@RequestParam("id") Integer id) {
		
		pService.deleteById(id);
		return new ResponseEntity<String>("商品移除成功",HttpStatus.OK);
	}
	
	// 僅更新商品基本資訊
	@PutMapping("/product/update") 
	public ResponseEntity<String> updateProductById(@RequestBody Product p){
		Product updatedProduct = pService.UpdateProduct(p);
		return new ResponseEntity<String>("商品更新成功，商品ID: " + updatedProduct.getProductId() ,HttpStatus.OK);
	}
	
	//關鍵字搜尋
	@GetMapping("/product/findByNameLike")  
	@ResponseBody
	public List<ProductAndPhotoSegmentDto> findProductByNameLike(@RequestParam("nameInput") String nameInput ){
		 List<Product> pList = pService.findProductByNameLike(nameInput);
		 List<ProductAndPhotoSegmentDto> res= new ArrayList<ProductAndPhotoSegmentDto>();
			int flag =0;
			for( Product p : pList) {
				ProductAndPhotoSegmentDto tmp = new ProductAndPhotoSegmentDto();
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
				tmp.photoData= sb.toString();
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
	public List<ProductAndPhotoSegmentDto> findProductByProductType(@RequestParam("selectType") String selectType){
		
		List<Product> pList = pService.findProductByProductType(selectType);
		List<ProductAndPhotoSegmentDto> res= new ArrayList<ProductAndPhotoSegmentDto>();
		int flag =0;
		for( Product p : pList) {
			ProductAndPhotoSegmentDto tmp = new ProductAndPhotoSegmentDto();
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
			tmp.photoData= sb.toString();
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
	public List<ProductAndPhotoSegmentDto> findProdictByProductPrice
	(@RequestParam("firstPrice") Integer firstPrice, @RequestParam("secondPrice") Integer secondPrice){
		 List<Product> pList = pService.findProductByProductPrice(firstPrice, secondPrice);
		 List<ProductAndPhotoSegmentDto> res= new ArrayList<ProductAndPhotoSegmentDto>();
		 int flag =0;
			for( Product p : pList) {
				ProductAndPhotoSegmentDto tmp = new ProductAndPhotoSegmentDto();
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
				tmp.photoData= sb.toString();
				res.add(tmp);
				if(flag==0) {
					flag=1;
				}
			}
			return res;
	}
	
	//測試
	@GetMapping("/product")
	public String toProduct() {
		return "showProduct";
	}
	
	//測試
	@GetMapping("/product/add1")
	public String toAddProduct() {
		return "addProduct";
	}
	
	@GetMapping("/product/findByProductIds")
	@ResponseBody
	public List<ProductAndPhotoSegmentDto> findProductByIds(@RequestParam("productIds") List<Integer> productIds){
		return pService.findProductByIds(productIds);
	}
	
}
