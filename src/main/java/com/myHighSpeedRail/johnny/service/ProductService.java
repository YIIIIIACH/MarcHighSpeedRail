package com.myHighSpeedRail.johnny.service;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.PostProductDto;
import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductBuyingMethod;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.repository.ProductBuyingMethodRepository;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pDao;
	
	@Autowired
	private ProductBuyingMethodRepository pbmDao;
	
//	@Autowired
//	private ProductPhotoSegmentService ppss;
	
	
	public Product addProduct(PostProductDto ppDto) {
			
			Product product = new Product(); // 新增product
			product.setProductName(ppDto.productName);
			product.setProductPrice(ppDto.productPrice);
			product.setProductDescription(ppDto.productDescription);
			product.setProductType(ppDto.productType);
			product.setProductInventory(ppDto.productInventory);
			
			Product savedProduct = pDao.save(product); // 儲存product
			
//			ppss.savePhoto(ppDto);
			
			for(String method : ppDto.buyingMethod) {
				ProductBuyingMethod buyingMethod = new ProductBuyingMethod();
				buyingMethod.setBuyingMethod(method);
				buyingMethod.setProduct(savedProduct);
				pbmDao.save(buyingMethod);
			}
	
			return savedProduct;
	}
	
	public List<Product> addAllProduct(List<Product> pList){
			
		return pDao.saveAll(pList);
	}
	
//	public Product findProductById(Integer id) {
//		Optional<Product> optional = pDao.findById(id);
//		
//		if(optional.isPresent()) {
//			Product product = optional.get();
//			
//			return product;
//		}
//		return null;
//	}
	
	public List<ProductAndPhotoSegmentDto> findAllProduct(){
		List<Product> pList = pDao.findAll();
		List<ProductAndPhotoSegmentDto> res = new ArrayList<ProductAndPhotoSegmentDto>();
		
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
		}	
		return res;
	}
	
//	public Page<Product> findbyPage(Integer pageNumber){
//		Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "productId"); //分頁設定
//		Page<Product> page = pDao.findAll(pgb); //找到符合分頁設定的product
////		Page<Product> products = pDao.findProductAndPhoto(pgb);
////		List<Product> content = page.getContent();
//		return page;
//		//回傳是Page型別, 在前端要用.content.forEach()取得裡面的物件
//		//回傳是List型別, 在前端要用.forEach()取得物件
//	}
	
	public Page<Product> findbyPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 8, Sort.Direction.ASC, "productId"); //分頁設定
		Page<Product> page = pDao.findAll(pgb); //找到符合分頁設定的product
//		Page<Product> products = pDao.findProductAndPhoto(pgb);
//		List<Product> content = page.getContent();
//		return content;
		return page;
		
		//回傳是Page型別, 在前端要用.content.forEach()取得裡面的物件
		//回傳是List型別, 在前端要用.forEach()取得物件
	}
	
	public String deleteById(Integer id) {
		Optional<Product> optional = pDao.findById(id);
		if(optional.isEmpty()) {
			return "沒有這筆資料";
		}
		Product product = optional.get();
		pDao.delete(product);
		
		return "成功刪除";
	}
	
	public Product UpdateProduct(Product p) {
		Optional<Product> optional = pDao.findById(p.getProductId());

		if(optional.isPresent()) {
			
			Product product = optional.get();
			product.setProductName(p.getProductName());
			product.setProductDescription(p.getProductDescription());
			product.setProductPrice(p.getProductPrice());
			product.setProductType(p.getProductType());
			product.setProductInventory(p.getProductInventory());
			
			Product updatedProduct = pDao.save(product);
			return updatedProduct;
		}
		return null;
	}
	
	public List<Product> findProductByNameLike(String productName) {
		return pDao.findProductByNameLike(productName);	
	}
	
	public List<Product> findProductByProductType(String productType){
		return pDao.findProductByProductType(productType);
	}
	
	public List<Product> findProductByProductPrice(Integer firstPrice, Integer secondPrice){
		return pDao.findProductByPrice(firstPrice, secondPrice);
	}
	public Optional<Product> findById( Integer pid){
		return pDao.findById(pid);
	}
	
	public ProductAndPhotoSegmentDto findProductById(Integer pId) {
		
		Product product = pDao.findById(pId).get();
		
		ProductAndPhotoSegmentDto temp = new ProductAndPhotoSegmentDto();
		temp.productId = product.getProductId();
		temp.productName = product.getProductName();
		temp.productPrice = product.getProductPrice();
		temp.productInventory = product.getProductInventory();
		temp.productType = product.getProductType();
		temp.productDescription = product.getProductDescription();
		
		product.getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());
		
		StringBuilder sb = new StringBuilder();
		for(ProductPhotoSegment pps: product.getPhotoSegment()) {

			sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
		}
		
		temp.photoData= sb.toString();

		return temp;
	}
}
