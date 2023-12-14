package com.myHighSpeedRail.johnny.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myHighSpeedRail.johnny.dto.CreateShoppingOrderRequestDto;
import com.myHighSpeedRail.johnny.model.ShoppingOrder;
import com.myHighSpeedRail.johnny.model.ShoppingOrderDetail;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.service.OrderService;
import com.myHighSpeedRail.johnny.service.ShoppingCartItemService;
import com.myHighSpeedRail.marc.util.PayPalUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppingOrderController {
	
	@Autowired
	private OrderService oService;
	
	@Autowired
	private ShoppingCartItemService cartService;
	
	@Autowired
	private PayPalUtil payPalService;
	
	@PostMapping("/createOrder")
	public ResponseEntity<String> createShoppingOrder(HttpServletRequest req, @RequestBody CreateShoppingOrderRequestDto orderRequest){
		
		List<ShoppingCartItem> cartItems = cartService.findByMemberIdAndItemsId(orderRequest.memberId, orderRequest.cartItemIds);
		
		ShoppingOrder shoppingOrder = new ShoppingOrder();
		shoppingOrder.setMember(orderRequest.memberId);
		shoppingOrder.setOrderStatus("待付款");
		shoppingOrder.setOrderCreationDate(new Date());
		shoppingOrder.setTotalPrice(orderRequest.totalPrice);
		shoppingOrder.setMember(orderRequest.memberId);
		
		List<ShoppingOrderDetail> orderDetails = new ArrayList<>();
		
		for(ShoppingCartItem item : cartItems) {
			
			ShoppingOrderDetail orderDetail = new ShoppingOrderDetail();
			
			orderDetail.setProduct(item.getProduct());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setShoppingOrder(shoppingOrder);
			orderDetail.setCheckoutPrice(orderRequest.totalPrice);
			orderDetails.add(orderDetail);
			
		}
		oService.createOrder(shoppingOrder, orderDetails);
		
		return ResponseEntity.ok("訂單已建立");
	}
	
	
}
	
