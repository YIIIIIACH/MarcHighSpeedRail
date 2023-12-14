package com.myHighSpeedRail.johnny.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.dto.ShoppingOrderWithDetailResponseDto;
import com.myHighSpeedRail.johnny.model.Product;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.model.ShoppingOrder;
import com.myHighSpeedRail.johnny.model.ShoppingOrderDetail;
import com.myHighSpeedRail.johnny.repository.ProductRepository;
import com.myHighSpeedRail.johnny.repository.ShoppingOrderDetailRepository;
import com.myHighSpeedRail.johnny.repository.ShoppingOrderRepository;

@Service
public class ShoppingOrderService {

	@Autowired
	private ShoppingOrderRepository orderDao;

	@Autowired
	private ShoppingOrderDetailRepository orderDetailDao;

	@Autowired
	private ProductRepository productDao;
	
	public void createOrder(ShoppingOrder order, List<ShoppingOrderDetail> orderDetails) {

		orderDao.save(order);

		orderDetailDao.saveAll(orderDetails);

	};

	public List<ShoppingOrder> findOrderByMemberId(String memberId) {
		return orderDao.findByMemberId(memberId);
	};

	public List<ShoppingOrderWithDetailResponseDto> findOrderAndDetailByMemberId(String memberId) {
		// 會員訂單基本資訊清單
		List<ShoppingOrder> orders = orderDao.findByMemberId(memberId);
		// 要回應的Dto
		List<ShoppingOrderWithDetailResponseDto> resList = new ArrayList<>();

		for (ShoppingOrder order : orders) {
			ShoppingOrderWithDetailResponseDto temp = new ShoppingOrderWithDetailResponseDto();
			temp.orderStatus = order.getOrderStatus();
			temp.totalPrice = order.getTotalPrice();
			temp.orderCreationDate = order.getOrderCreationDate();
			temp.orderCompletionDate = order.getOrderCompletionDate();
			temp.member = order.getMember();
			temp.orderNumber = order.getOrderNumber();

			// 透過訂單找到訂單細項清單
			List<ShoppingOrderDetail> orderDetails = order.getOrderDetails();

			if (orderDetails != null && !orderDetails.isEmpty()) {
				List<Product> products = new ArrayList<>();

				for (int i = 0 ; i < orderDetails.size() ; i++) {
					ShoppingOrderDetail detail = orderDetails.get(i);
					
					Product p = productDao.findById(detail.getProduct().getProductId()).orElse(null);
					p.getPhotoSegment().sort((a,b) -> a.getSequence()-b.getSequence());
					
					StringBuilder sb = new StringBuilder();
					for(ProductPhotoSegment pps: p.getPhotoSegment()) {
						sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
					}
					products.add(p);
					temp.quantity = detail.getQuantity();
				}
				temp.products = products;
			}
			resList.add(temp);
		}
		return resList;
	};

}
