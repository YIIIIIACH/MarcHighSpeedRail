package com.myHighSpeedRail.johnny.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.Order;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderDao;
	
	
	public Order createOrder(List<ShoppingCartItem> cartItems) {
		
		Order o = new Order();
		
		for(ShoppingCartItem cartItem : cartItems) {
			o.setMember(cartItem.getMember());
			o.setOrderCreationDate(new Date());
			o.setOrderStatus("Pending");
//			o.setStation(cartItem.getProduct());

			o.setTotalPrice(cartItem.getProduct().getProductPrice() * cartItem.getQuantity());
		}
		
		return o;
	}
}
