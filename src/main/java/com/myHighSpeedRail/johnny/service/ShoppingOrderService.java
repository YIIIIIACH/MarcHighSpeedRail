package com.myHighSpeedRail.johnny.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	public ShoppingOrder createOrder(ShoppingOrder order, List<ShoppingOrderDetail> orderDetails) {
		ShoppingOrder so = orderDao.save(order);
		orderDetailDao.saveAll(orderDetails);
		return so;
		
	};

	public List<ShoppingOrder> findOrderByMemberId(String memberId) {
		return orderDao.findByMemberId(memberId);
	};

	public List<ShoppingOrderWithDetailResponseDto> findOrderAndDetailByMemberId(String memberId) {
		// 會員訂單資訊清單
		List<ShoppingOrder> orders = orderDao.findByMemberId(memberId);
		System.out.println("order size"+orders.size());
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
			temp.orderId = order.getOrderId();
//			System.out.println(temp.orderStatus);
//			System.out.println(temp.totalPrice);
//			System.out.println(temp.orderCreationDate);
//			System.out.println(temp.orderCompletionDate);
//			System.out.println(temp.member);
//			System.out.println(temp.orderNumber);
//			System.out.println(temp.orderId);
			
			// 透過訂單找到訂單細項清單
			List<ShoppingOrderDetail> orderDetails = order.getOrderDetails();
			System.out.println( orderDetails.size());
			
			if (orderDetails != null && !orderDetails.isEmpty()) {
				List<Product> products = new ArrayList<>();
				List<String> photoData = new ArrayList<>();
				temp.quantity = new ArrayList<Integer>();
				
				for (int i = 0 ; i < orderDetails.size() ; i++) {
					String pd = new String();
					ShoppingOrderDetail detail = orderDetails.get(i);
					System.out.println("pDetail pname:"+ detail.getProduct().getProductName());
					Optional<Product> pOpt = productDao.findById(detail.getProduct().getProductId());
					pOpt.get().getPhotoSegment().sort((a,b) -> a.getSequence()-b.getSequence());
					
					StringBuilder sb = new StringBuilder();
					for(ProductPhotoSegment pps: pOpt.get().getPhotoSegment()) {
						sb.append( new String(pps.getPhotoSegment(),0,pps.getPhotoSegment().length, StandardCharsets.UTF_8));
					}
					pd = sb.toString();
					if( pOpt.isPresent()) {
						Product pTmp =  pOpt.get();
						products.add(new  Product(pTmp.getProductId(),pTmp.getProductName(), pTmp.getProductPrice(), pTmp.getProductDescription(),pTmp.getProductType(), pTmp.getProductInventory()));
						temp.quantity.add(detail.getQuantity());
					}
					photoData.add(pd);
				}
				temp.photoData = photoData;
				temp.products = products;
			}
			resList.add(temp);
			
		}
		return resList;
	};

	public Boolean registPayedOrder(Integer orderId) {
		Optional<ShoppingOrder> sOrder = orderDao.findById(orderId);
		if( sOrder.isEmpty()) {
			return false;
		}
		
		ShoppingOrder sod = sOrder.get();
		sod.setOrderStatus("已付款");
		sod.setOrderCompletionDate(new Date());
		orderDao.save(sod);
		return true;	
	}
	
//	public ShoppingOrder 

	
}
