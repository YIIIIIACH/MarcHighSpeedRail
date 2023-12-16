package com.myHighSpeedRail.johnny.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.CreatePaidShoppingOrderDto;
import com.myHighSpeedRail.johnny.dto.CreateUnpaidShoppingOrderRequestDto;
import com.myHighSpeedRail.johnny.dto.ForCreatePaypalResDto;
import com.myHighSpeedRail.johnny.dto.ShoppingOrderWithDetailResponseDto;
import com.myHighSpeedRail.johnny.model.ShoppingOrder;
import com.myHighSpeedRail.johnny.model.ShoppingOrderDetail;
import com.myHighSpeedRail.johnny.model.ShoppingCartItem;
import com.myHighSpeedRail.johnny.service.ShoppingOrderService;
import com.myHighSpeedRail.johnny.service.ShoppingCartItemService;
import com.myHighSpeedRail.marc.dto.paypalapi.Amount;
import com.myHighSpeedRail.marc.dto.paypalapi.AppContext;
import com.myHighSpeedRail.marc.dto.paypalapi.Breakdown;
import com.myHighSpeedRail.marc.dto.paypalapi.CreatePayPalOrderDto;
import com.myHighSpeedRail.marc.dto.paypalapi.Item;
import com.myHighSpeedRail.marc.dto.paypalapi.Unit;
import com.myHighSpeedRail.marc.dto.paypalapi.UnitAmount;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.util.PayPalUtil;
import com.myHighSpeedRail.yuhsin.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShoppingOrderController {
	
	@Autowired
	private ShoppingOrderService oService;
	
	@Autowired
	private ShoppingCartItemService cartService;
	@Autowired
	private PayPalUtil paypalServ;
	@Value("${server.baseurl}")
	private String SERVER_BASE_URL;
	@Value("${front.end.host}")
	private String FRONT_SERVER_URL;
	@Value("${remote.front.end.host}")
	private String REMOTE_FRONT_SERVER_URL;
	
	
	
	@PostMapping("/createOrder")
	public ResponseEntity<String> createUnpaidShoppingOrder(HttpServletRequest req, @RequestBody CreateUnpaidShoppingOrderRequestDto orderRequest){
			
		List<ShoppingCartItem> cartItems = cartService.findByMemberIdAndItemsId(orderRequest.memberId, orderRequest.cartItemIds);
		
		ShoppingOrder shoppingOrder = new ShoppingOrder();
		shoppingOrder.setMember(orderRequest.memberId);
		shoppingOrder.setOrderStatus("待付款");
//		shoppingOrder.setOrderCreationDate(new Date());
		shoppingOrder.setTotalPrice(orderRequest.totalPrice);
		
		List<ShoppingOrderDetail> orderDetails = new ArrayList<>();
		
		for(ShoppingCartItem item : cartItems) {
			
			ShoppingOrderDetail orderDetail = new ShoppingOrderDetail();
			
			orderDetail.setProduct(item.getProduct());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setShoppingOrder(shoppingOrder);
			orderDetail.setCheckoutPrice(orderRequest.totalPrice);
			orderDetails.add(orderDetail);
			
		}
		ShoppingOrder createdOrder = oService.createOrder(shoppingOrder, orderDetails);
		Integer orderId = createdOrder.getOrderId();
		CreatePaidShoppingOrderDto temp = new CreatePaidShoppingOrderDto();
		temp.memberId = orderRequest.memberId;
		temp.orderId = orderId;
		temp.totalPrice = orderRequest.totalPrice;
		return createPaypalOrder(temp);
//		return ResponseEntity.ok("訂單已建立");
	}
	
	

@PostMapping("/createPaypalOrder")
public ResponseEntity<String> createPaypalOrder( @RequestBody CreatePaidShoppingOrderDto orderRequest){
	CreatePayPalOrderDto dto = new CreatePayPalOrderDto();
	// put info into dto
	dto.intent="CAPTURE";
	// add purchase_units into dto
	List<Unit> purUnits= new ArrayList<Unit>();
	Unit u = new Unit();
	u.items= new ArrayList<Item>();
	// add item 
//		Integer priceSum=0;
//		Station stst = stServ.findById(ststid).get();
//		Station edst = stServ.findById(edstid).get();
	
	Item item = new Item();
	item.name= "MarcHSR_product_all";
	item.description= "高鐵商品";
	item.quantity="1";
//			priceSum+= oneTicketPrice;
	UnitAmount ua= new UnitAmount("TWD", String.valueOf(orderRequest.totalPrice)+".00");
	item.unit_amount=ua;
	u.items.add(item);
	//create Amount
	Amount am = new Amount();
	am.currency_code="TWD";
	am.value= String.valueOf(orderRequest.totalPrice)+".00";
	am.breakdown= new Breakdown("TWD",orderRequest.totalPrice);
	u.amount= am;
	purUnits.add(u);
	dto.purchase_units=purUnits;
	AppContext actx = new AppContext();
//	actx.return_url = SERVER_BASE_URL+"";// your vue return url
	actx.return_url = REMOTE_FRONT_SERVER_URL+"/orderPaymentSuccess/"+orderRequest.orderId;
	actx.cancel_url = FRONT_SERVER_URL;// your payout failed return url, 
	//會redirect client 到一隻專門接收user approve 成功資訊的controller
	dto.application_context= actx;
	return paypalServ.createProductOrderUtil(dto);
}


@PostMapping("/changeOrderStatus")
public @ResponseBody ResponseEntity<String> changeOrderStatus(@RequestBody Integer orderId, @RequestParam String paypalOrderId){
	//
	// use ticketOrderId to get paypal order id
		// 可能需要先檢查是否 paypal ticket order is approve
		if( !paypalServ.captureOrderUtil( String.valueOf(paypalOrderId), Integer.valueOf(orderId))) {
			return new ResponseEntity<String> ("failed", HttpStatus.BAD_REQUEST);
		}
		// set order status to 已付款
		if(oService.registPayedOrder(orderId) == false) {
			return new ResponseEntity<String> ("failed",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String> ("success",HttpStatus.OK);
	}

//	@GetMapping("/OrderHistory")
//	@ResponseBody
//	public List<ShoppingOrder> findOrderByMemberId(HttpServletRequest req, @RequestParam("memberId") String memberId){
//		return oService.findOrderByMemberId(memberId);
//	}

@GetMapping("/OrderHistory")
@ResponseBody
public List<ShoppingOrderWithDetailResponseDto> findOrderByMemberId(HttpServletRequest req, @RequestParam("memberId") String memberId){
	return oService.findOrderAndDetailByMemberId(memberId);
	
}



}