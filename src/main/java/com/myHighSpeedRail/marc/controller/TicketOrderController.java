package com.myHighSpeedRail.marc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.myHighSpeedRail.marc.dto.EmailPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.util.PayPalUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myHighSpeedRail.marc.dto.BookingDto;
import com.myHighSpeedRail.marc.dto.DisplayMemberBookingTicketDto;
import com.myHighSpeedRail.marc.dto.DisplayMemberTicketOrderDto;
import com.myHighSpeedRail.marc.dto.MakeOrderBuinessSeatDto;
import com.myHighSpeedRail.marc.dto.SeatListDto;
import com.myHighSpeedRail.marc.dto.paypalapi.AppContext;
import com.myHighSpeedRail.marc.dto.paypalapi.CreatePayPalOrderDto;
import com.myHighSpeedRail.marc.dto.paypalapi.Unit;
import com.myHighSpeedRail.marc.dto.paypalapi.*;
import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;
import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.model.TicketOrder;
import com.myHighSpeedRail.marc.service.BookingService;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.ScheduleArriveService;
import com.myHighSpeedRail.marc.service.ScheduleRestSeatService;
import com.myHighSpeedRail.marc.service.ScheduleSeatStatusService;
import com.myHighSpeedRail.marc.service.ScheduleService;
import com.myHighSpeedRail.marc.service.SeatService;
import com.myHighSpeedRail.marc.service.StationService;
import com.myHighSpeedRail.marc.service.TicketDiscountService;
import com.myHighSpeedRail.marc.service.TicketOrderService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TicketOrderController {
	@Autowired
	private ScheduleArriveService schArrServ;
	@Autowired
	private TicketOrderService tkoServ;
	@Autowired
	private TicketDiscountService tkdServ;
	@Autowired
	private RailRouteSegmentService rrsServ;
	@Autowired
	private ScheduleService schServ;
	@Autowired
	private BookingService bServ;
	@Autowired
	private ScheduleRestSeatService schrsServ;
	@Autowired
	private RailRouteStopStationService schrrssServ;
	@Autowired
	private ScheduleSeatStatusService schssServ;
	@Autowired
	private SeatService seatServ;
	@Autowired
	private StationService stServ;
	@Autowired
	private PayPalUtil paypalServ;
	
	@Value("${server.baseurl}")
	private String SERVER_BASE_URL;
	@Value("${front.end.host}")
	private String FRONT_SERVER_URL;
	
	@PostMapping("/booking")
	public @ResponseBody ResponseEntity<String> doBookingAndMakeEmptyTicket(HttpServletRequest req,@RequestBody BookingDto bookingDto){
		//Cookie cookie = new Cookie("login-token", "e7039cb4-ee63-47fa-8f79-3585bd4c73a2");
//		Cookie []cookies = req.getCookies();
		String token=null;
		token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
//		for( Cookie ck: cookies) {
//			if( ck.getName().equals("login-token")) {
//				token = ck.getValue();
//			}
//		}
//		if(token==null) {
//			// redirect to MemberSystem
//			;
//		}else {
//			//validate the current login token 
//		ResponseEntity<String> resEntity = restTemplate.getForEntity( 羽忻會員系統的url ,  String.class);
		// String uuid = resEntity.getBody()
//		if( uuid != null) {
			// token is verify and get a member uuid
//		}
//		}
		// temporary treatment
		
		//create an ticket order and set order status to 未付款
		Integer paymentEarlyDay = tkdServ.findById(bookingDto.ticketDiscountId).getPurchaseEarlyLimitDay();
		Date deadline = schArrServ.findByScheduleIdStationId(bookingDto.scheduleId, bookingDto.startStationId).getArriveTime();
		deadline = Date.from( deadline.toInstant().minusSeconds(paymentEarlyDay* 24*60*60));
		Schedule sch =schServ.findById(bookingDto.scheduleId);
		RailRouteSegment rrs =rrsServ.findByStopStationId(sch.getRailRoute().getRailRouteId()
				, bookingDto.startStationId, bookingDto.endStationId).get();
		Integer originPrice =rrs.getRailRouteSegmentTicketPrice();
		// member_token ticket_order_create_time status payment_dealine total_price
		Integer total= 0;
		for( Integer tdid: bookingDto.chooseDiscounts) { // to be fixed
			 TicketDiscount td = tkdServ.findById( tdid) ;
			total+= ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount();
		}
		TicketOrder tcko = tkoServ.save(new TicketOrder( token,new Date(),"未付款",deadline, total));
		if( tcko==null) {
			return new ResponseEntity<String>("booking failed at making order",HttpStatus.BAD_REQUEST);
		}
		
		// set a bunch of booking which 
		//member_token	tcko	sch
		//rail_route_segment(rrs)	seat_id_fk(null)	ticket_discount_id_fk	status(未付款)	ticket_price	ticket_qrcode_url(null)
		for( Integer tdid: bookingDto.chooseDiscounts) {
			TicketDiscount td = tkdServ.findById(tdid);
			bServ.save(new Booking(token, tcko, sch, rrs,(Seat)null, td, "未分配"
					, ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount(),
					(String)null));
		}
		
		// reduce the amount of schedule_rest_seat 
		schrsServ.updateScheduleRestSeat(sch.getScheduleId(), bookingDto.ticketDiscountId , rrs.getRailRoute().getRailRouteId() , bookingDto.startStationId , bookingDto.endStationId , bookingDto.chooseDiscounts.size() );
		return new ResponseEntity<String>("booking success ,ticketOrderId:"+tcko.getTicketOrderId(),HttpStatus.OK);
	}
	
	@PostMapping("/createTicketOrder")
	public @ResponseBody ResponseEntity<String> createPayPalTicketOrder(@RequestParam Integer ticketOrderId){
		// get info of ticket order
		CreatePayPalOrderDto dto = new CreatePayPalOrderDto();
		List<Booking> bList = bServ.findByTicketOrderId(ticketOrderId);
//		bList.get(0).getTicketOrder();
		// put info into dto
		dto.intent="CAPTURE";
		// add application context into dto
		AppContext actx= new AppContext();
		//會redirect client 到一隻專門接收user approve 成功資訊的controller
		actx.return_url=SERVER_BASE_URL+"/registAllocateTicketOrderSeats?ticketOrderId="+String.valueOf(ticketOrderId);
		actx.cancel_url= FRONT_SERVER_URL+"/bookSuccess/"+ticketOrderId; 
		dto.application_context= actx;
		// add purchase_units into dto
		List<Unit> purUnits= new ArrayList<Unit>();
		Unit u = new Unit();
		u.items= new ArrayList<Item>();
		// add item 
		Integer priceSum=0;
		for(int i=0; i<bList.size(); i++) {
			Booking b= bList.get(i);
			Item tmp = new Item();
			tmp.name= "MarcHSR_Ticket";
			tmp.description=  b.getRailRouteSegment().getStartStation().getStationName()+"-"+b.getRailRouteSegment().getEndStation().getStationName()+b.getTicketDiscount().getTicketDiscountName();
			tmp.quantity="1";
			priceSum+= b.getTicketPrice();
			UnitAmount ua= new UnitAmount("TWD", String.valueOf(b.getTicketPrice())+".00");
			tmp.unit_amount=ua;
			u.items.add(tmp);
		}
		//create Amount
		Amount am = new Amount();
		am.currency_code="TWD";
		am.value= String.valueOf(priceSum)+".00";
		am.breakdown= new Breakdown("TWD", priceSum);
		u.amount= am;
		purUnits.add(u);
		dto.purchase_units=purUnits;
		
//		return dto;
//		return new ResponseEntity<String>("still in progress",HttpStatus.OK);
		return paypalServ.createOrderUtil(dto);
	}
	
	@GetMapping("registAllocateTicketOrderSeats")
	public String allocateTicketOrdeSeat(@RequestParam Integer ticketOrderId,@RequestParam(value="token") String paypalOrderId){
		
		List<Booking> bList = bServ.findByTicketOrderId(ticketOrderId);
		//List<RailRouteStopStation> findByRouteIdStationId(Integer rid, Integer sid){
		RailRouteStopStation stopst1 = schrrssServ.findByRouteIdStationId(
				bList.get(0).getRailRouteSegment().getRailRoute().getRailRouteId()
				, bList.get(0).getRailRouteSegment().getStartStation().getStationId()).get(0);
		RailRouteStopStation stopst2 = schrrssServ.findByRouteIdStationId(
				bList.get(0).getRailRouteSegment().getRailRoute().getRailRouteId()
				, bList.get(0).getRailRouteSegment().getEndStation().getStationId()).get(0);
		Integer stseq = stopst1.getRailRouteStopStationSequence();
		Integer edseq = stopst2.getRailRouteStopStationSequence();
		List<Booking> newBookingList = new ArrayList<Booking>();
		Long mask = 0L;
		mask |= (1L << edseq)-1;
		mask >>= stseq;
		mask <<= stseq;
		List<ScheduleSeatStatus> schssList =schssServ.getAvaibleSeat(bList.get(0).getSchedule().getScheduleId(), mask, bList.size());
		//schssList.get(0).getSeat().getSeatId();
		for( int i=0; i<schssList.size(); i++) {
			Booking tmp = bList.get(i);
			tmp.setSeat(schssList.get(i).getSeat());
			tmp.setStatus("已分配座位");
			newBookingList.add(tmp);
		}
		bServ.saveAll(newBookingList);
		schssServ.registBookedSeat(bList.get(0).getSchedule().getScheduleId(), mask, bList.size());
		TicketOrder tmp = bList.get(0).getTicketOrder();
		tmp.setStatus("已付款");
		tkoServ.save(tmp );
		//go capture the paypal token
		paypalServ.captureOrderUtil(paypalOrderId);
		return "checkOutTicketReturn";
	}
	
	@PostMapping("/createBuinessTicketOrder/{ststid}/{edstid}/{amount}/{schid}")
	public @ResponseBody ResponseEntity<String> createPayPalBuinessTicketOrder(@PathVariable Integer ststid,@PathVariable Integer edstid, @PathVariable Integer amount , @PathVariable Integer schid,@RequestBody SeatListDto pickSeatIdList){
		String token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
		// check seat was not booked by other member
		for( Integer sid : pickSeatIdList.seatList) {
			System.out.println(sid);
		}
		ScheduleArrive schArr = schArrServ.findByScheduleIdStationId(schid, ststid);
		RailRouteSegment rrs = rrsServ.findByScheduleIdStstEdst(schid, ststid, edstid).get(0);
		List<ScheduleSeatStatus> schssList = schssServ.findBySchidInSeatid(schid,pickSeatIdList.seatList);
		System.out.println( schssList.size());
		RailRouteStopStation rrss1 = schrrssServ.findByRouteIdStationId(rrs.getRailRoute().getRailRouteId(),ststid).get(0);
		RailRouteStopStation rrss2 = schrrssServ.findByRouteIdStationId(rrs.getRailRoute().getRailRouteId(),edstid).get(0);
		// get segment mask
		Long mask = 0L;
		mask |= (1L << rrss2.getRailRouteStopStationSequence())-1;
		mask >>= rrss1.getRailRouteStopStationSequence();
		mask <<= rrss1.getRailRouteStopStationSequence();
		//get selected scheduleSeatStatus;
		List<Seat> tmp = new ArrayList<Seat>();
		for( ScheduleSeatStatus schss: schssList ) {
			tmp.add(schss.getSeat());
		}
		List<ScheduleSeatStatus> selectSchSeatList = schssServ.findBySeatSchedule(schArr.getSchedule(), tmp);
		// check seat Available
		for( ScheduleSeatStatus schss: selectSchSeatList) {
			if( (schss.getScheduleStatus() & mask) > 0) {
				return new ResponseEntity<String>("seat was Booked",HttpStatus.OK);
			}
		}
		// end check seat was not booked by other member
		TicketDiscount tckd = tkdServ.findByDiscountType("商務票").get(0);
		Date deadline = Date.from( schArr.getArriveTime().toInstant().minusSeconds(tckd.getPurchaseEarlyLimitDay()* 24*60*60));
		Integer oneTicketPrice= (rrs.getRailRouteSegmentTicketPrice()*tckd.getTicketDiscountPercentage())/100-tckd.getTicketDiscountAmount();
		// require startStationName endStationName ticketPrice price sum
		// get info of ticket order
		CreatePayPalOrderDto dto = new CreatePayPalOrderDto();
		// put info into dto
		dto.intent="CAPTURE";
		// add purchase_units into dto
		List<Unit> purUnits= new ArrayList<Unit>();
		Unit u = new Unit();
		u.items= new ArrayList<Item>();
		// add item 
		Integer priceSum=0;
		Station stst = stServ.findById(ststid).get();
		Station edst = stServ.findById(edstid).get();
		for(int i=0; i< amount; i++) {
//			Booking b= bList.get(i);
			Item item = new Item();
			item.name= "MarcHSR_Ticket";
			item.description=  stst.getStationName()+"-"+edst.getStationName()+"商務票";
			item.quantity="1";
			priceSum+= oneTicketPrice;
			UnitAmount ua= new UnitAmount("TWD", String.valueOf(oneTicketPrice)+".00");
			item.unit_amount=ua;
			u.items.add(item);
		}
		//create Amount
		Amount am = new Amount();
		am.currency_code="TWD";
		am.value= String.valueOf(priceSum)+".00";
		am.breakdown= new Breakdown("TWD", priceSum);
		u.amount= am;
		purUnits.add(u);
		dto.purchase_units=purUnits;
		// add application context into dto
		TicketOrder tcko = tkoServ.save(new TicketOrder( token,new Date(),"未付款",deadline, priceSum));
		// create a bunch of not allocate seat for ticket order
		for( int i=0 ; i< schssList.size(); i++) {
			bServ.save(new Booking(token, tcko,schArr.getSchedule(), rrs,schssList.get(i).getSeat(),tckd, "暫時鎖定座位"
					, ((rrs.getRailRouteSegmentTicketPrice()*tckd.getTicketDiscountPercentage())/100)-tckd.getTicketDiscountAmount(),
					(String)null));
		}	
		//
		AppContext actx= new AppContext();
		actx.return_url=SERVER_BASE_URL+"/newBookBuinessSeat?ticketOrderId="+String.valueOf(tcko.getTicketOrderId());
		actx.cancel_url= FRONT_SERVER_URL+"/bookSuccess/"+tcko.getTicketOrderId(); 
		//會redirect client 到一隻專門接收user approve 成功資訊的controller
		dto.application_context= actx;
//		return dto;
//		return new ResponseEntity<String>("still in progress",HttpStatus.OK);
		return paypalServ.createOrderUtil(dto);
		
//		return new ResponseEntity<String>("test test ",HttpStatus.OK);
	}
	@GetMapping(value="/newBookBuinessSeat")
	public String newBookBuinessSeat(@RequestParam Integer ticketOrderId){
		//Cookie cookie = new Cookie("login-token", "e7039cb4-ee63-47fa-8f79-3585bd4c73a2");
//		Cookie []cookies = req.getCookies();
		String token=null;
//		for( Cookie ck: cookies) {
//			if( ck.getName().equals("login-token")) {
//				token = ck.getValue();
//			}
//		}
//		if(token==null) {
//			// redirect to MemberSystem
//			;
//		}else {
//			//validate the current login token 
//			;
//		}
		// temporary treatment
		token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
		// use ticket order id to get BookList
		List<Booking> bList = bServ.findByTicketOrderId(ticketOrderId);
		TicketOrder tOrder = tkoServ.findById(ticketOrderId);
		Station stst = bList.get(0).getRailRouteSegment().getStartStation();
		Station edst = bList.get(0).getRailRouteSegment().getEndStation();
		// get ticketDisc id
		Integer tkdid = tkdServ.findByDiscountType("商務票").get(0).getTicketDiscountId();
		Schedule sch = bList.get(0).getSchedule();
		List<ScheduleSeatStatus> schssList = schssServ.findBySchidInSeatid(sch.getScheduleId(), bList.stream().map((b)->b.getSeat().getSeatId()).toList());
		RailRouteStopStation rrss1 = schrrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(),stst.getStationId()).get(0);
		RailRouteStopStation rrss2 = schrrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(),edst.getStationId()).get(0);
		// get segment mask
		Long mask = 0L;
		mask |= (1L << rrss2.getRailRouteStopStationSequence())-1;
		mask >>= rrss1.getRailRouteStopStationSequence();
		mask <<= rrss1.getRailRouteStopStationSequence();
		//get selected scheduleSeatStatus;
		List<Seat> tmp = new ArrayList<Seat>();
		for( ScheduleSeatStatus schss: schssList ) {
			tmp.add(schss.getSeat());
		}
		List<ScheduleSeatStatus> selectSchSeatList = schssServ.findBySeatSchedule(sch, tmp);
		// check seat Available
		for( ScheduleSeatStatus schss: selectSchSeatList) {
			if( (schss.getScheduleStatus() & mask) > 0) {
//				return new ResponseEntity<String>("seat was Booked",HttpStatus.OK);
				return "checkOutFail";
			}
		}
		
		// update scheduleSeatStatus
//		registBookedSeat( Integer schid , Long mask , Integer amt) {registBookedSeat( Integer schid , Long mask , Integer amt) {
		schssServ.registBookedBuinessSeat(sch.getScheduleId(), mask, selectSchSeatList);
		// update scheduleRestSeat [Ignore now ]
		schrsServ.updateScheduleRestSeat( sch.getScheduleId(), tkdid , sch.getRailRoute().getRailRouteId() ,stst.getStationId() ,edst.getStationId(), schssList.size() );
		
		// create order
		Integer paymentEarlyDay = tkdServ.findById(tkdid).getPurchaseEarlyLimitDay();
		Date deadline = schArrServ.findByScheduleIdStationId(sch.getScheduleId(),stst.getStationId()).getArriveTime();
		deadline = Date.from( deadline.toInstant().minusSeconds(paymentEarlyDay* 24*60*60));
		RailRouteSegment rrs =rrsServ.findByStopStationId(sch.getRailRoute().getRailRouteId()
				, stst.getStationId(),edst.getStationId()).get();
		Integer originPrice =rrs.getRailRouteSegmentTicketPrice();
		// member_token ticket_order_create_time status payment_dealine total_price
		Integer total= 0;
		TicketDiscount td = tkdServ.findById( tkdid) ;
		for( int i=0 ; i< schssList .size(); i++) {
			total+= ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount();			
		}
		tOrder.setStatus("已付款");
		TicketOrder tcko = tkoServ.save(tOrder);
		// create Booking
		for( int i=0 ; i< schssList.size(); i++) {
			bServ.save(new Booking(token, tcko, sch, rrs,schssList.get(i).getSeat(),td, "已分配座位"
					, ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount(),
					(String)null));
		}	
//		return new ResponseEntity<String>("book buiness",HttpStatus.OK);
		return "checkOutTicketReturn";
		
	}
	
	@PostMapping(value="/bookBuinessSeat")
	public @ResponseBody ResponseEntity<String> bookBuinessSeat(@RequestBody MakeOrderBuinessSeatDto boDto){
		//Cookie cookie = new Cookie("login-token", "e7039cb4-ee63-47fa-8f79-3585bd4c73a2");
//		Cookie []cookies = req.getCookies();
		String token=null;
//		for( Cookie ck: cookies) {
//			if( ck.getName().equals("login-token")) {
//				token = ck.getValue();
//			}
//		}
//		if(token==null) {
//			// redirect to MemberSystem
//			;
//		}else {
//			//validate the current login token 
//			;
//		}
		// temporary treatment
		token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
		// get ticketDisc id
		Integer tkdid = tkdServ.findByDiscountType(boDto.ticketDiscountName).get(0).getTicketDiscountId();
		Schedule sch = schServ.findById(boDto.scheduleId);
		List<ScheduleSeatStatus> schssList = schssServ.findBySchidInSeatid(boDto.scheduleId, boDto.orderSeatIdList);
		RailRouteStopStation rrss1 = schrrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), boDto.startStation.getStationId()).get(0);
		RailRouteStopStation rrss2 = schrrssServ.findByRouteIdStationId(sch.getRailRoute().getRailRouteId(), boDto.endStation.getStationId()).get(0);
		// get segment mask
		Long mask = 0L;
		mask |= (1L << rrss2.getRailRouteStopStationSequence())-1;
		mask >>= rrss1.getRailRouteStopStationSequence();
		mask <<= rrss1.getRailRouteStopStationSequence();
		//get selected scheduleSeatStatus;
		List<Seat> tmp = new ArrayList<Seat>();
		for( ScheduleSeatStatus schss: schssList ) {
			tmp.add(schss.getSeat());
		}
		List<ScheduleSeatStatus> selectSchSeatList = schssServ.findBySeatSchedule(sch, tmp);
		// check seat Available
		for( ScheduleSeatStatus schss: selectSchSeatList) {
			if( (schss.getScheduleStatus() & mask) > 0) {
				return new ResponseEntity<String>("seat was Booked",HttpStatus.OK);
			}
		}
		
		// update scheduleSeatStatus
//		registBookedSeat( Integer schid , Long mask , Integer amt) {registBookedSeat( Integer schid , Long mask , Integer amt) {
		schssServ.registBookedBuinessSeat(sch.getScheduleId(), mask, selectSchSeatList);
		// update scheduleRestSeat [Ignore now ]
		schrsServ.updateScheduleRestSeat( sch.getScheduleId(), tkdid , sch.getRailRoute().getRailRouteId() , boDto.startStation.getStationId() , boDto.endStation.getStationId() , schssList.size() );
		
		// create order
		Integer paymentEarlyDay = tkdServ.findById(tkdid).getPurchaseEarlyLimitDay();
		Date deadline = schArrServ.findByScheduleIdStationId(sch.getScheduleId(), boDto.startStation.getStationId()).getArriveTime();
		deadline = Date.from( deadline.toInstant().minusSeconds(paymentEarlyDay* 24*60*60));
		RailRouteSegment rrs =rrsServ.findByStopStationId(sch.getRailRoute().getRailRouteId()
				, boDto.startStation.getStationId(), boDto.endStation.getStationId()).get();
		Integer originPrice =rrs.getRailRouteSegmentTicketPrice();
		// member_token ticket_order_create_time status payment_dealine total_price
		Integer total= 0;
		TicketDiscount td = tkdServ.findById( tkdid) ;
		for( int i=0 ; i< schssList .size(); i++) {
			total+= ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount();			
		}
		TicketOrder tcko = tkoServ.save(new TicketOrder( token,new Date(),"已付款",deadline, total));
		// create Booking
		for( int i=0 ; i< schssList.size(); i++) {
			bServ.save(new Booking(token, tcko, sch, rrs,schssList .get(i).getSeat(),td, "已分配座位"
					, ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount(),
					(String)null));
		}	
		//checkOutTicketReturn
		
		return new ResponseEntity<String>("book buiness",HttpStatus.OK);
		
	}
	@GetMapping("/getAllMemberTicketOrder/{memuuid}")
	public @ResponseBody DisplayMemberTicketOrderDto getAllMemberTicketOrder(@PathVariable String memuuid) {
		List<TicketOrder> tckorList  = tkoServ.findByMember(memuuid);
		DisplayMemberTicketOrderDto res = new DisplayMemberTicketOrderDto();
		res.orderCreateTimes= new ArrayList<Date>();
		res.orderStatuses = new ArrayList<String>();
		res.paymentDeadlines= new ArrayList<Date>();
		res.ticketOrderIds = new ArrayList<Integer>();
		res.totalPrices= new ArrayList<Integer>();
		res.memberToken= memuuid;
		for( TicketOrder tckod : tckorList) {
			res.orderCreateTimes.add(tckod.getTicketOrderCreateTime());
			res.orderStatuses.add(tckod.getStatus());
			res.paymentDeadlines.add( tckod.getPaymentDeadline());
			res.ticketOrderIds.add(tckod.getTicketOrderId());
			res.totalPrices.add(tckod.getTotalPrice());
		}
		return res;
	}
	
//	@GetMapping("/verifyToken")
//	public @ResponseBody ResponseEntity<String> verifyToken( ){
//		try {
//			// 192.168.60.95: ?? / ???
//		URL endpoint = new URL("http://localhost:8080/MarcHighSpeedRail/getAllStation");
//		EmailPassword ep = new EmailPassword();
//		ep.email = "test";
//		ep.password = "qwer";
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestData = objectMapper.writeValueAsString(ep);
//        HttpURLConnection httpUrlConn = (HttpURLConnection) endpoint.openConnection();
//        httpUrlConn.setDoOutput(true);
//        httpUrlConn.setRequestMethod("GET");//有修改過
//        httpUrlConn.setRequestProperty("Content-Type", "application/json");
//        OutputStream outputStream = httpUrlConn.getOutputStream();
//        byte[] input = requestData.getBytes("utf-8");
//	        outputStream.write(input, 0, input.length);
//	        int statusCode = httpUrlConn.getResponseCode();
//	        InputStream inputStream = null;
//	        if (statusCode == 200) {
//	            inputStream = httpUrlConn.getInputStream();
//	        } else {
//	            inputStream = httpUrlConn.getErrorStream();
//	        }
//	        BufferedReader bufferedReader;
//			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
//			StringBuilder response = new StringBuilder();
//			String responseLine = null;
//			while ((responseLine = bufferedReader.readLine()) != null) {
//				response.append(responseLine.trim());
//			}
//			System.out.println(response.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return new ResponseEntity<String>("failed ",HttpStatus.OK);
//		}
//        // response.toString()
//		return new ResponseEntity<String>("go go ",HttpStatus.OK);
//	}
}
