package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.repository.ProductRepository;
import com.myHighSpeedRail.johnny.repository.ShoppingCartItemRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class ShoppingCartItemService {
	
	@Autowired
	private ShoppingCartItemRepository cartDao;
	
	@Autowired
	private ProductRepository pDao;
	
	public ShoppingCartItem addItemToCart(Integer Id, Integer quantity, HttpServletRequest req) {
//	public ShoppingCartItem addItemToCart(Integer Id, Integer quantity, String member) {
		
		String token = getTokenFromRequest(req);
		
		
		Optional<Product> optional = pDao.findById(Id);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			ShoppingCartItem cart = new ShoppingCartItem();
			cart.setProduct(product);
			cart.setQuantity(quantity);
			cart.setMember(token);
			
			return cartDao.save(cart);
			
		}else {
			throw new RuntimeException("商品不存在");
		}
	}
	
	private String getTokenFromRequest(HttpServletRequest req) {
		return "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
	}

	public List<ShoppingCartItem> showAllCartItems(){
		return cartDao.findAll();	
	}
	
	public String removeItemFromCart(Integer id) {
		try {
			Optional<ShoppingCartItem> optional = cartDao.findById(id);
		
			if(optional.isPresent()) {
				ShoppingCartItem item = optional.get();
				cartDao.delete(item);
				return "刪除成功";
			}else {
				return "項目不存在, 刪除失敗";
			}
		}catch(Exception e) {
			return "刪除失敗, 發生錯誤" + e.getMessage();
		}
		
	}

}
