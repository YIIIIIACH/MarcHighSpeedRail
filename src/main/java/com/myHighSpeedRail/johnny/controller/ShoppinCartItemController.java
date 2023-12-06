package com.myHighSpeedRail.johnny.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.ShoppingCartResponseDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.service.ProductService;
import com.myHighSpeedRail.johnny.service.ShoppingCartItemService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppinCartItemController {
	
	@Autowired
	private ShoppingCartItemService cartService;
	@Autowired
	private ProductService pServ;
	
	//加入商品到購物車
	@PostMapping("/ShoppinCart/addProductToCart")
	@ResponseBody
	public ResponseEntity<String> addToCart(  // 商品加入購物車
			@RequestParam(value = "productId") Integer productId,
			@RequestParam(value = "memberId") String memberId 
			,HttpServletRequest req){		
//		Cookie cookie = new Cookie("login-token", "e7039cb4-ee63-47fa-8f79-3585bd4c73a2");
//		Cookie []cookies = req.getCookies();
//		String token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
//		for(Cookie ck : cookies) {
//			if(ck.getName().equals("login-token")) {
//				token = ck.getValue();
//			}
//		}
//		if(token==null) {
//			// redirect to MemberSystem
//		}
		// 驗證會員token存在不 by member system
//		cartService.addItemToCart(productId);
		/*
		 * private Integer shoppingCartItemId;
			private Integer quantity;
			private Product product;
			private String member;
		 */
//		String memberUUID = "xxxxxxxxxxxxxxxxxxxx";
		
		Optional<Product> optional = pServ.findById(productId);
		
		if(optional.isPresent()) {	
			Product product = optional.get();
			ShoppingCartItem cartItem = new ShoppingCartItem(1, product, memberId);
			if( cartService.save(cartItem) == null) {
				return new ResponseEntity<String> ("failed",HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseEntity.ok("商品已成功加入購物車");	
	}
	
	//顯示會員所有購物車品項
	@GetMapping("/ShoppinCart")
	@ResponseBody
	public ShoppingCartResponseDto  showAllCartItems(@RequestParam("memberId") String memberId){
		
		ShoppingCartResponseDto res = new ShoppingCartResponseDto();
		
		List<ShoppingCartItem> cartItems = cartService.showAllCartItems(memberId);
		if(cartItems != null) {
			res.cartItems = cartItems;
		}else {
			res.message = "您的購物車是空的。";
		}
		return res;
			
	}
	
	//刪除會員的單一購物車品項
	@DeleteMapping("/ShoppinCart/delete")
	public ResponseEntity<String> removeCartItem(
			@RequestParam("memberId") String memberId, 
			@RequestParam("itemId") Integer itemId){
		String result = cartService.removeItemFromCart(itemId,memberId);
		
		if("刪除成功".equals(result))
		{
			return ResponseEntity.ok("商品刪除成功");// 狀態碼需不需要改成noContent
			
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
	//清空會員的所有購物車品項
	@DeleteMapping("/ShoppingCart/deleteAll")
	public ResponseEntity<String> removeAllCartItem(@RequestParam("memberId") String memberId){
		String result = cartService.deleteAllItems(memberId);
		if("全部刪除成功".equals(result))
		{
			return ResponseEntity.ok("已清空購物車");	
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
	//更新品項數量
	@PutMapping("/ShoppinCart/updataQuantity")
	@ResponseBody
	public String updateQuantity(
			@RequestParam("memberId") String memberId, 
			@RequestParam("quantity") Integer quantity,
			@RequestParam("itemId") Integer itemId) {
		return cartService.updateQuantity(memberId, quantity, itemId);
	}
}
