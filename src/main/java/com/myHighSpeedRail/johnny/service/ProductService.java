package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.ProductAndPhotoDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhoto;
import com.myHighSpeedRail.johnny.repository.ProductPhotoRepository;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pDao;
		
	@Autowired
	private ProductPhotoRepository ppDao;
	
	public Product addProduct(ProductAndPhotoDto pappDto) {
			
			Product product = new Product(); // 新增product
			product.setProductName(pappDto.getProductName());
			product.setProductPrice(pappDto.getProductPrice());
			product.setProductDescription(pappDto.getProductDescription());
			product.setProductType(pappDto.getProductType());
			product.setProductInventory(pappDto.getProductInventory());
			
			Product savedProduct = pDao.save(product); // 儲存product
			
			ProductPhoto productPhoto = new ProductPhoto(); // 新增photo
			productPhoto.setMimeType(pappDto.getMimeType());
			productPhoto.setPhotoPath(pappDto.getPhotoPath());
			
			productPhoto.setProduct(savedProduct); // 設置ProductPhoto的Product屬性
			
			ppDao.save(productPhoto);
			
			return savedProduct;

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
		PageRequest pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC, "productId");
//		Page<Product> page = pDao.findAll(pgb);
		Page<Product> products = pDao.findProductAndPhoto(pgb);
		
//		System.out.println("aaaaaaaaaaaaaaaaaaaa");
//		System.out.println(products.getContent().toString());
		return products;
	}
	
	public void deleteById(Integer id) {
		pDao.deleteById(id);
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
