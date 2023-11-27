package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.service.ShoppingCartItemService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppinCartItemController {
	
	@Autowired
	private ShoppingCartItemService cartService;
	
	@PostMapping("/ShoppinCart/addProductToCart")
	@ResponseBody
	public ResponseEntity<String> addToCart(  // 商品加入購物車
			@RequestParam(value = "id") Integer productId,
			@RequestParam(value = "quantity") Integer quantity,
			HttpServletRequest req){
		
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
		cartService.addItemToCart(productId, quantity, req);			
		return ResponseEntity.ok("商品已成功加入購物車");
	
	}
	
	@GetMapping("/ShoppinCart")
	@ResponseBody
	public List<ShoppingCartItem> showAllCartItems(){
		return cartService.showAllCartItems();
	}
	
	@DeleteMapping("/ShoppinCart/delete")
	public ResponseEntity<String> removeItemFromCart(@RequestParam("id") Integer id){
		String result = cartService.removeItemFromCart(id);
		
		if("刪除成功".equals(result))
		{
			return ResponseEntity.ok("商品刪除成功");// 狀態碼需不需要改成noContent
			
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
}
