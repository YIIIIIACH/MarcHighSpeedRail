package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository pDao;
	@Autowired
	private ProductBuyingMethodService pbmServ;
	
	public Product addProduct(Product product) {
		return pDao.save(product);
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
	
	public Product UpdateProduct(Integer id, String pName, String desc, Integer price, String type, Integer inventory) {
		Optional<Product> optional = pDao.findById(id);

		if(optional.isPresent()) {
			
			Product product = optional.get();
			product.setProductDescription(desc);
			product.setProductName(pName);
			product.setProductPrice(price);
			product.setProductType(type);
			product.setProductInventory(inventory);
			
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
