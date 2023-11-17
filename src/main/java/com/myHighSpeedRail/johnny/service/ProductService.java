package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.ProductAndProductPhotoDto;
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
	
	public Product addProduct(ProductAndProductPhotoDto pDto) {
		Product product = new Product(null, pDto.getProductName(), pDto.getProductPrice(), pDto.getProductDescription(),pDto.getProductType(),pDto.getProductInventory());
		Product newProduct = pDao.save(product);
		ProductPhoto productPhoto = new ProductPhoto(null, pDto.getMimeType(),pDto.getPhotoPath(), newProduct);	
		ppDao.save(productPhoto);
		return newProduct;
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
	
	public void deleteById(Integer id) {
		pDao.deleteById(id);
	}
	
	public Product UpdateProduct(Product p) {
		Optional<Product> optional = pDao.findById(p.getProductId());

		if(optional.isPresent()) {
			
			Product product = optional.get();
			
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
