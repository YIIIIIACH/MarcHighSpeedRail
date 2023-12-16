package com.myHighSpeedRail.johnny.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.CreateUnpaidShoppingOrderRequestDto;
import com.myHighSpeedRail.johnny.dto.ProductAndPhotoSegmentDto;
import com.myHighSpeedRail.johnny.dto.ShoppingCartQuantityUpdateDto;
import com.myHighSpeedRail.johnny.dto.ShoppingCartResponseDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.service.ProductService;
import com.myHighSpeedRail.johnny.service.ShoppingCartItemService;
import com.myHighSpeedRail.yuhsin.Models.LoginResponseModel;
import com.myHighSpeedRail.yuhsin.Services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppinCartItemController {
	
	@Autowired
	private ShoppingCartItemService cartService;
	@Autowired
	private ProductService pService;
	@Autowired
	private UserService uService;
	
	//加入商品到購物車(指定數量)
	@PostMapping("/ShoppingCart/addProducts")
	@ResponseBody
	public ResponseEntity<String> addToCart(
			@RequestParam(value = "productId") Integer productId,
			@RequestParam(value = "memberId") String memberId, 
			@RequestParam(value = "quantity") Integer quantity
			,HttpServletRequest req){	
		
		String loginToken = null;
		String uuid = null;
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null) {
			//如找不到 cookie 則 print 找不到
			System.out.println("not cookie found");
		}else {		
			//如找到 cookies 則遍歷, 並判斷cookie 的 key 是否等於字串 login-token
			// 如 判斷為 true, 則取 value
			for( Cookie cookie: cookies) {
				if( cookie.getName().equals("login-token")) {
					loginToken = cookie.getValue();
				}
			}
		} 
        String token = loginToken;
        System.out.println(token);
        
        LoginResponseModel userDetail = null;
        userDetail = uService.tokenlogin(UUID.fromString(token));
        
        if(userDetail == null) {
			return new ResponseEntity<String> ("member token not valid or other error",HttpStatus.UNAUTHORIZED);
		}	
		//判斷商品是否已存在購物車
		boolean isProductInCart = cartService.isProductInCart(memberId, productId);
		
		if(isProductInCart) {
			//如存在,則回傳已存在
			return new ResponseEntity<String> ("商品已在購物車內",HttpStatus.OK);
		}else {
			cartService.addItemToCartWithQuantity(productId, memberId, quantity);
			return ResponseEntity.ok("商品已成功加入購物車");
		}
			
	}
	
	//加入商品到購物車(數量預設1)
	@PostMapping("/ShoppingCart/addProduct")
	@ResponseBody
	public ResponseEntity<String> addToCart(  // 商品加入購物車
			@RequestParam(value = "productId") Integer productId,
			@RequestParam(value = "memberId") String memberId, 
			HttpServletRequest req){		
		
		
		boolean isProductInCart = cartService.isProductInCart(memberId, productId);
		
		if(isProductInCart) {
			//如存在,則回傳已存在
			return new ResponseEntity<String> ("商品已在購物車中。",HttpStatus.OK);
		}else {
			cartService.addItemToCart(productId, memberId);
			return ResponseEntity.ok("商品成功加入購物車。");
		}
			
	}
	
//	@GetMapping("/ShoppinCart")
//	@ResponseBody
//	public List<ShoppingCartItem> showAllCartItems(@RequestParam("memberId") String memberId){
//		return cartService.showAllCartItems(memberId);
//	}
	
	//顯示會員所有購物車品項
	@GetMapping("/ShoppingCart")
	@ResponseBody
	public List<ShoppingCartResponseDto> showAllCartItems(@RequestParam("memberId") String memberId){
		
		//要回應的內容
		List<ShoppingCartResponseDto> res = new ArrayList<>();
		
		// cartItems 會員的所有購物車品項
		List<ShoppingCartItem> cartItems = cartService.showAllCartItems(memberId);
		
			for(ShoppingCartItem item : cartItems) {
				
				ShoppingCartResponseDto temp = new ShoppingCartResponseDto();
				
				temp.productId = item.getProduct().getProductId();
				temp.shoppingCartItemId = item.getShoppingCartItemId();
				temp.productName = item.getProduct().getProductName();
				temp.productDescription = item.getProduct().getProductDescription();
				temp.productPrice = item.getProduct().getProductPrice();
				temp.productType = item.getProduct().getProductType();
				temp.quantity = item.getQuantity();
				temp.totalPrice = item.getQuantity() * item.getProduct().getProductPrice();
				temp.isSelected = false;
				item.getProduct().getPhotoSegment().sort((a,b)-> a.getSequence()-b.getSequence());
				
				StringBuilder sb = new StringBuilder();
				for(ProductPhotoSegment pps: item.getProduct().getPhotoSegment()) {
					sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
				}
				temp.photoData = sb.toString();	
				res.add(temp);
			}
			return res;
	}
	
	//刪除會員的單一購物車品項
	@DeleteMapping("/ShoppingCart/delete")
	public ResponseEntity<String> removeCartItem(
			@RequestParam("memberId") String memberId, 
			@RequestParam("itemId") Integer itemId){
		String result = cartService.removeItemFromCart(itemId,memberId);
		
		if("刪除成功".equals(result))
		{
			return ResponseEntity.ok("商品刪除成功");
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("商品已不存在");
		}
	}
	
	//清空會員的所有購物車品項
	@DeleteMapping("/ShoppingCart/deleteAll")
	public ResponseEntity<String> removeAllCartItem(@RequestParam("memberId") String memberId){
		String result = cartService.deleteAllItems(memberId);	
		return ResponseEntity.ok(result);		
	}
	
	//清除會員指定的購物車品項
	@DeleteMapping("/ShoppingCart/deleteByItemIds")
	public ResponseEntity<String> removeCartItemsByItemsId(@RequestParam String memberId, @RequestParam List<Integer> itemIds){
		String result = cartService.deleteByMemberIdAndItemsId(memberId, itemIds);
		
		return ResponseEntity.ok(result);
		
	}
	
	//更新品項數量
	@PutMapping("/ShoppingCart/updata")
	@ResponseBody
	public String updateQuantity(
			@RequestParam("memberId") String memberId, 
			@RequestParam("quantity") Integer quantity,
			@RequestParam("itemId") Integer itemId) {
		return cartService.updateQuantity(memberId, quantity, itemId);
	}
	
//	//更新品項數量
//	@PutMapping("/ShoppingCart/updataQuantity")
//	@ResponseBody
//	public String updateQuantity(@RequestBody ShoppingCartQuantityUpdateDto scquDto) {
//		return cartService.updateQuantity(scquDto);
//	}
	
}
