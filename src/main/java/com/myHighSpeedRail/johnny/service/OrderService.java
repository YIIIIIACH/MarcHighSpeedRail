package com.myHighSpeedRail.johnny.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.ShoppingOrder;
import com.myHighSpeedRail.johnny.model.ShoppingOrderDetail;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.repository.ShoppingOrderDetailRepository;
import com.myHighSpeedRail.johnny.repository.ShoppingOrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private ShoppingOrderRepository orderDao;
	
	@Autowired
    private ShoppingOrderDetailRepository orderDetailDao;
	
	
	public void createOrder(ShoppingOrder order, List<ShoppingOrderDetail> orderDetails) {
		
		orderDao.save(order);
		
		orderDetailDao.saveAll(orderDetails);
		
	}
}
