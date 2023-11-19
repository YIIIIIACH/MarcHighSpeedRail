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

@Controller
public class ShoppinCartItemController {
	
	@Autowired
	private ShoppingCartItemService cartService;
	
	@PostMapping("/ShoppinCart/addProductToCart")
	@ResponseBody
	public ResponseEntity<String> addToCart(  // 商品加入購物車
			@RequestParam(value = "id") Integer productId,
			@RequestParam(value = "quntity") Integer quntity,
			@RequestParam(value = "member") String member){
		try {
		cartService.addItemToCart(productId, quntity, member);
			
		return ResponseEntity.ok("商品已成功加入購物車");
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請先登入會員");		
		}
	}
	
	@GetMapping("/ShoppinCart")
	@ResponseBody
	public List<ShoppingCartItem> showAllCartItems(){
		return cartService.showAllCartItems();
	}
	
	@DeleteMapping("/ShoppinCart/delete")
	@ResponseBody
	public ResponseEntity<String> removeItemFromCart(@RequestParam("id") Integer id){
		String result = cartService.removeItemFromCart(id);
		
		if("刪除成功".equals(result))
		{
			return ResponseEntity.ok("商品刪除成功");// 狀態馬需不需要改成noContent
			
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}
	
}
