package com.myHighSpeedRail.johnny.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.repository.ProductRepository;
import com.myHighSpeedRail.johnny.repository.ShoppingCartItemRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ShoppingCartItemService {
	
	@Autowired
	private ShoppingCartItemRepository cartDao;
	
	@Autowired
	private ProductRepository pDao;
	
//	private String getTokenFromRequest(HttpServletRequest req) {
//		return "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
//	}
//	
	//商品加入購物車
	public ShoppingCartItem addItemToCartWithQuantity(Integer productId, String memberID, Integer quantity) {
//		String token = getTokenFromRequest(req);	
//		Member m = mDao.findMemberById(memberID);
		
		Optional<Product> optional = pDao.findById(productId);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			ShoppingCartItem cart = new ShoppingCartItem();
			cart.setProduct(product);
			cart.setmemberId(memberID);
			cart.setQuantity(quantity);
			
			return cartDao.save(cart);
			
		}else {
			throw new RuntimeException("商品不存在");
		}
	}
	
	public ShoppingCartItem addItemToCart(Integer productId, String memberID) {
		
		Optional<Product> optional = pDao.findById(productId);
		
		if(optional.isPresent()) {
			Product product = optional.get();
			
			ShoppingCartItem cart = new ShoppingCartItem();
			cart.setProduct(product);
			cart.setmemberId(memberID);
			cart.setQuantity(1);
			
			return cartDao.save(cart);
			
		}else {
			throw new RuntimeException("商品不存在");
		}
	}
	
	
	
//	public ShoppingCartItem save( ShoppingCartItem shoppingCartItem) {
//		return cartDao.save(shoppingCartItem);
//	}
	
	//	展示會員所有購物車商品
	public List<ShoppingCartItem> showAllCartItems(String memberId){
		List<ShoppingCartItem> items = cartDao.findAllShoppingCartItemByMemberId(memberId);
		return items;	
	}
	
	//  刪除單一購物車品項
	public String removeItemFromCart(Integer cartItemId, String memberId) {
			cartDao.deleteShoppingCartItem(memberId, cartItemId);
			return "刪除成功";
		
	}
	//	刪除全部購物車品項
	public String deleteAllItems(String memberId) {
		cartDao.deleteAllShoppingCartItemByMemberId(memberId);
		return "已清空購物車";
	}
	
	// 	更新購物車品項數量
	public String updateQuantity( String memberId, Integer quantity, Integer cartItemId) {
		cartDao.updateCartItemQuantity(memberId, cartItemId, quantity);
		return "更新成功";
	}
	
	
	public boolean isProductInCart(String memberId, Integer productId) {
		
		Optional<ShoppingCartItem> shoppingCartItem = cartDao.findByProductIdAndMemberId(memberId, productId);
		
		return shoppingCartItem.isPresent();
	}
}
