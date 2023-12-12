package com.myHighSpeedRail.marc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.util.PayPalUtil;
import com.myHighSpeedRail.yuhsin.Models.LoginResponseModel;
import com.myHighSpeedRail.yuhsin.Services.UserService;
import com.myHighSpeedRail.marc.dto.BookingDto;
import com.myHighSpeedRail.marc.dto.DisplayMemberTicketOrderDto;
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
	private StationService stServ;
	@Autowired
	private PayPalUtil paypalServ;
	@Autowired
	private UserService uServ;
	@Value("${server.baseurl}")
	private String SERVER_BASE_URL;
	@Value("${front.end.host}")
	private String FRONT_SERVER_URL;
	
	@PostMapping("/booking")
	public @ResponseBody ResponseEntity<String> doBookingAndMakeEmptyTicket(HttpServletRequest req,@RequestBody BookingDto bookingDto){
		try {
			String loginToken = null;
			Cookie[] cks = req.getCookies();
			if(cks==null) {
				System.out.print("not cookie found");
			}else {
				for( Cookie c: cks) {
					if(c.getName().equals("login-token")) {
						loginToken = c.getValue();
					}
				}
			}
			String token=loginToken;
			System.out.println( token);
			LoginResponseModel userDetail = null;
			userDetail = uServ.tokenlogin(UUID.fromString(token));
			if(userDetail==null) {
				return new ResponseEntity<String> ("member token not valid or other error",HttpStatus.UNAUTHORIZED);
			}
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
			Boolean lockSeatSuccess =schrsServ.updateScheduleRestSeat(sch.getScheduleId(), bookingDto.ticketDiscountId , rrs.getRailRoute().getRailRouteId() , bookingDto.startStationId , bookingDto.endStationId , bookingDto.chooseDiscounts.size() );
			if( lockSeatSuccess==false) {
				return new ResponseEntity<String>("did not have enought seat",HttpStatus.BAD_REQUEST);
			}
			TicketOrder tcko = tkoServ.save(new TicketOrder( userDetail.getMember_id().toString(),new Date(),"未付款",deadline, total));
			if( tcko==null) {
				return new ResponseEntity<String>("booking failed at making order",HttpStatus.BAD_REQUEST);
			}
			
			// set a bunch of booking which 
			//member_token	tcko	sch
			//rail_route_segment(rrs)	seat_id_fk(null)	ticket_discount_id_fk	status(未付款)	ticket_price	ticket_qrcode_url(null)
			for( Integer tdid: bookingDto.chooseDiscounts) {
				TicketDiscount td = tkdServ.findById(tdid);
				bServ.save(new Booking(userDetail.getMember_id().toString(), tcko, sch, rrs,(Seat)null, td, "未分配"
						, ((originPrice*td.getTicketDiscountPercentage())/100)-td.getTicketDiscountAmount(),
						(String)null));
			}
			
			// reduce the amount of schedule_rest_seat 
			return new ResponseEntity<String>("booking success ,ticketOrderId:"+tcko.getTicketOrderId(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/createTicketOrder")
	public @ResponseBody ResponseEntity<String> createPayPalTicketOrder(HttpServletRequest req,@RequestParam Integer ticketOrderId){
		String token = null;
		Cookie[] cks = req.getCookies();
		if(cks==null) {
			System.out.print("not cookie found");
		}else {
			for( Cookie c: cks) {
				if(c.getName().equals("login-token")) {
					token = c.getValue();
				}
			}
		}
		LoginResponseModel userDetail = null;
		if(token==null) {
			// redirect to MemberSystem
			return new ResponseEntity<String> ("failed login token not found",HttpStatus.UNAUTHORIZED);
		}
		else{
			//validate the current login token 
			userDetail = uServ.tokenlogin(UUID.fromString(token));
		}
		if(userDetail==null) {
			return new ResponseEntity<String> ("member token not valid or other error",HttpStatus.UNAUTHORIZED);
		}
		// get info of ticket order
		CreatePayPalOrderDto dto = new CreatePayPalOrderDto();// pack paypal order dto
		List<Booking> bList = bServ.findByTicketOrderId(ticketOrderId);
//		bList.get(0).getTicketOrder();
		// put info into dto
		dto.intent="CAPTURE"; // pack paypal order dto
		// add application context into dto
		AppContext actx= new AppContext();// pack paypal order dto
		//會redirect client 到一隻專門接收user approve 成功資訊的controller
		actx.return_url=SERVER_BASE_URL+"/registAllocateTicketOrderSeats?ticketOrderId="+String.valueOf(ticketOrderId);// pack paypal order dto
		actx.cancel_url= FRONT_SERVER_URL+"/bookSuccess/"+ticketOrderId; // pack paypal order dto
		dto.application_context= actx; // pack paypal order dto
		// add purchase_units into dto
		List<Unit> purUnits= new ArrayList<Unit>();// pack paypal order dto
		Unit u = new Unit();// pack paypal order dto
		u.items= new ArrayList<Item>();// pack paypal order dto
		// add item 
		Integer priceSum=0;// pack paypal order dto
		for(int i=0; i<bList.size(); i++) {
			Booking b= bList.get(i);
			Item tmp = new Item();// pack paypal order dto
			tmp.name= "MarcHSR_Ticket";// pack paypal order dto
			tmp.description=  b.getRailRouteSegment().getStartStation().getStationName()+"-"+b.getRailRouteSegment().getEndStation().getStationName()+b.getTicketDiscount().getTicketDiscountName();// pack paypal order dto
			tmp.quantity="1";// pack paypal order dto
			priceSum+= b.getTicketPrice();// pack paypal order dto
			UnitAmount ua= new UnitAmount("TWD", String.valueOf(b.getTicketPrice())+".00");// pack paypal order dto
			tmp.unit_amount=ua;// pack paypal order dto
			u.items.add(tmp);// pack paypal order dto
		}
		//create Amount
		Amount am = new Amount();// pack paypal order dto
		am.currency_code="TWD";// pack paypal order dto
		am.value= String.valueOf(priceSum)+".00";// pack paypal order dto
		am.breakdown= new Breakdown("TWD", priceSum);// pack paypal order dto
		u.amount= am;// pack paypal order dto
		purUnits.add(u);// pack paypal order dto
		dto.purchase_units=purUnits;// pack paypal order dto
		return paypalServ.createOrderUtil(dto);
	}
	
	@GetMapping("registAllocateTicketOrderSeats")
	public String allocateTicketOrdeSeat(Model model,HttpServletRequest req,@RequestParam Integer ticketOrderId,@RequestParam(value="token") String paypalOrderId){
		if(!paypalServ.captureOrderUtil(paypalOrderId)) {
			return "checkOutFail";
		}
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
		model.addAttribute("msg","恭喜付款成功");
		return "checkOutTicketReturn";
	}
	
	@PostMapping("/createBuinessTicketOrder/{ststid}/{edstid}/{amount}/{schid}")
	public @ResponseBody ResponseEntity<String> createPayPalBuinessTicketOrder(HttpServletRequest req,@PathVariable Integer ststid,@PathVariable Integer edstid, @PathVariable Integer amount , @PathVariable Integer schid,@RequestBody SeatListDto pickSeatIdList){
		String token = null;
		Cookie[] cks = req.getCookies();
		if(cks==null) {
			System.out.print("not cookie found");
		}else {
			for( Cookie c: cks) {
				if(c.getName().equals("login-token")) {
					token = c.getValue();
				}
			}
		}
		LoginResponseModel userDetail = null;
		if(token==null) {
			// redirect to MemberSystem
			return new ResponseEntity<String> ("failed login token not found",HttpStatus.UNAUTHORIZED);
		}
		else{
			//validate the current login token 
			userDetail = uServ.tokenlogin(UUID.fromString(token));
		}
		if(userDetail==null) {
			return new ResponseEntity<String> ("member token not valid or other error",HttpStatus.UNAUTHORIZED);
		}
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
		// 先檢查 剩餘座位數量
		Boolean lockSeatSuccess =schrsServ.updateScheduleRestSeat(schid, tckd.getTicketDiscountId() , rrs.getRailRoute().getRailRouteId() , ststid , edstid , amount );
		if( lockSeatSuccess==false) {
			return new ResponseEntity<String>("did not have enought seat",HttpStatus.BAD_REQUEST);
		}
		schssServ.registBookedBuinessSeat(schid, mask, schssList);//selectSchSeatList
		
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
		TicketOrder tcko = tkoServ.save(new TicketOrder( userDetail.getMember_id().toString(),new Date(),"未付款",deadline, priceSum));
		// create a bunch of not allocate seat for ticket order
		for( int i=0 ; i< schssList.size(); i++) {
			bServ.save(new Booking(userDetail.getMember_id().toString(), tcko,schArr.getSchedule(), rrs,schssList.get(i).getSeat(),tckd, "暫時鎖定座位"
					, ((rrs.getRailRouteSegmentTicketPrice()*tckd.getTicketDiscountPercentage())/100)-tckd.getTicketDiscountAmount(),
					(String)null));
		}	
		//
		AppContext actx= new AppContext();
		actx.return_url=SERVER_BASE_URL+"/newBookBuinessSeat?ticketOrderId="+String.valueOf(tcko.getTicketOrderId());
		actx.cancel_url= FRONT_SERVER_URL+"/bookSuccess/"+tcko.getTicketOrderId(); 
		//會redirect client 到一隻專門接收user approve 成功資訊的controller
		dto.application_context= actx;
		return paypalServ.createOrderUtil(dto);
	}
	@GetMapping(value="/newBookBuinessSeat")
	public String newBookBuinessSeat(Model model,HttpServletRequest req,@RequestParam Integer ticketOrderId,@RequestParam(value="token") String paypalOrderId){
		// 可能需要先檢查是否 paypal ticket order is approve
		if( !paypalServ.captureOrderUtil( String.valueOf(paypalOrderId))) {
			return "checkOutFail";
		}
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
//		List<ScheduleSeatStatus> selectSchSeatList = schssServ.findBySeatSchedule(sch, tmp);
		// check seat Available
		for( ScheduleSeatStatus schss: schssList ) { // 原本是 ：selectSchSeatList
			if( (schss.getScheduleStatus() & mask) > 0) {
				model.addAttribute("msg","座位分配失敗請重新嘗試");
				return "checkOutFail";
			}
		}
		
		// update scheduleSeatStatus
		//策略改動在會員付款前要先分配好座位
//		registBookedSeat( Integer schid , Long mask , Integer amt) {registBookedSeat( Integer schid , Long mask , Integer amt) {
//		schssServ.registBookedBuinessSeat(sch.getScheduleId(), mask, schssList);//selectSchSeatList
		// update scheduleRestSeat [Ignore now ]
		//策略改動在會員付款前要先分配好座位
		//schrsServ.updateScheduleRestSeat( sch.getScheduleId(), tkdid , sch.getRailRoute().getRailRouteId() ,stst.getStationId() ,edst.getStationId(), schssList.size() );
		
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
		tkoServ.save(tOrder);
		// update booking list
		for( Booking b: bList) {
			b.setStatus("已分配座位");
		}
		bServ.saveAll(bList);
		model.addAttribute("msg","座位分配成功");

		return "checkOutTicketReturn";
		
	}
	@GetMapping("/getAllMemberTicketOrder")
	public @ResponseBody DisplayMemberTicketOrderDto getAllMemberTicketOrder(HttpServletRequest req) {
		Cookie []cookies = req.getCookies();
		String token=null;
		LoginResponseModel userDetail = null;
		for( Cookie ck: cookies) {
			if( ck.getName().equals("login-token")) {
				token = ck.getValue();
			}
		}
		if(token==null) {
			DisplayMemberTicketOrderDto res = new DisplayMemberTicketOrderDto();
			res.displayStatus="查無 login-token";
			return res;
		}
		else{
			//validate the current login token 
			userDetail = uServ.tokenlogin(UUID.fromString(token));
		}
		if(userDetail==null) {
			DisplayMemberTicketOrderDto res = new DisplayMemberTicketOrderDto();
			res.displayStatus="登入token失效";
			return res;
		}
		List<TicketOrder> tckorList  = tkoServ.findByMember(userDetail.getMember_id().toString());
		DisplayMemberTicketOrderDto res = new DisplayMemberTicketOrderDto();
		res.orderCreateTimes= new ArrayList<Date>();
		res.orderStatuses = new ArrayList<String>();
		res.paymentDeadlines= new ArrayList<Date>();
		res.ticketOrderIds = new ArrayList<Integer>();
		res.totalPrices= new ArrayList<Integer>();
		res.memberToken= userDetail.getMember_id().toString();
		for( TicketOrder tckod : tckorList) {
			res.orderCreateTimes.add(tckod.getTicketOrderCreateTime());
			res.orderStatuses.add(tckod.getStatus());
			res.paymentDeadlines.add( tckod.getPaymentDeadline());
			res.ticketOrderIds.add(tckod.getTicketOrderId());
			res.totalPrices.add(tckod.getTotalPrice());
			res.displayStatus= "查詢成功";
		}
		return res;
	}
}
