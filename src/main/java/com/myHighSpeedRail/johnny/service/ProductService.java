package com.myHighSpeedRail.johnny.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pDao;
		
	@Autowired
	private ProductPhotoSegmentService ppss;
	
	public Product addProduct(ProductAndPhotoSegmentDto pappDto) {
			
			Product product = new Product(); // 新增product
			product.setProductName(pappDto.productName);
			product.setProductPrice(pappDto.productPrice);
			product.setProductDescription(pappDto.productDescription);
			product.setProductType(pappDto.productType);
			product.setProductInventory(pappDto.productInventory);
			
			Product savedProduct = pDao.save(product); // 儲存product
			ppss.savePhoto(pappDto);
			
			return savedProduct;
	}
	
//	public Product addProduct(Product p) {
//		
//		return pDao.save(p); 
//	}
	
	public List<Product> addAllProduct(List<Product> pList){
			
		return pDao.saveAll(pList);
	}
	
	public Product findProductById(Integer id) {
		Optional<Product> optional = pDao.findById(id);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			return product;
		}
		return null;
	}
	
	public List<Product> findAllProduct(){
		return pDao.findAll();
	}
	
	public Page<Product> findbyPage(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "productId"); //分頁設定
		Page<Product> page = pDao.findAll(pgb); //找到符合分頁設定的product
//		Page<Product> products = pDao.findProductAndPhoto(pgb);
//		List<Product> content = page.getContent();
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
}
