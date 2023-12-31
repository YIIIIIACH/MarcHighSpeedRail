package com.myHighSpeedRail.marc.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.AllocateTicketDto;
import com.myHighSpeedRail.marc.dto.CreateBookingQRDto;
import com.myHighSpeedRail.marc.dto.DisplayMemberBookingTicketDto;
import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.model.TicketOrder;
import com.myHighSpeedRail.marc.service.BookingService;
import com.myHighSpeedRail.marc.service.ScheduleArriveService;
import com.myHighSpeedRail.marc.service.TicketOrderService;
import com.myHighSpeedRail.yuhsin.Models.LoginResponseModel;
import com.myHighSpeedRail.yuhsin.Services.UserService;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookingController {
	@Autowired
	private ScheduleArriveService scharrServ;
	@Autowired
	private BookingService bServ;
	@Autowired
	private TicketOrderService tckodServ;
	@Autowired
	private UserService uServ;
	@GetMapping("/getBookingByTicketOrder/{tckodId}")// 用作查詢訂票紀錄使用
	public @ResponseBody DisplayMemberBookingTicketDto getBookingByTicketOrder(@PathVariable Integer tckodId,HttpServletRequest req) {
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
			return new DisplayMemberBookingTicketDto();
		}
		else{
			//validate the current login token 
			userDetail = uServ.tokenlogin(UUID.fromString(token));
		}
		if(userDetail==null) {
			return new DisplayMemberBookingTicketDto();
		}
		// verify memuuid and tckodId
		TicketOrder tckod = tckodServ.findById(tckodId);
		if( !tckod.getMemberToken().equals(userDetail.getMember_id().toString() )) {
			 return new DisplayMemberBookingTicketDto();
		}
		List<Booking> bList = bServ.findByTicketOrderId(tckodId);
		DisplayMemberBookingTicketDto res = new DisplayMemberBookingTicketDto();
		res.startArrive = scharrServ.findByScheduleIdStationId(bList.get(0).getSchedule().getScheduleId(), bList.get(0).getRailRouteSegment().getStartStation().getStationId());
		res.endArrive= scharrServ.findByScheduleIdStationId(bList.get(0).getSchedule().getScheduleId(), bList.get(0).getRailRouteSegment().getEndStation().getStationId());
		res.ticketRailRouteSegment=bList.get(0).getRailRouteSegment();
		res.ticketSchedule= bList.get(0).getSchedule();
		
		System.out.println(bList.get(0).getSchedule().getDepartTime());
		res.bookingIdList = new ArrayList<>();
		res.seatList = new ArrayList<>();
		res.ticketDiscountList = new ArrayList<>();
		res.ticketPriceList = new ArrayList<>();
		res.ticketQRcodeList = new ArrayList<>();
		res.ticketStatusList = new ArrayList<>();
		for( Booking b: bList) {
			res.bookingIdList.add(b.getBookingId());
			res.seatList.add(b.getSeat());
			res.ticketDiscountList.add(b.getTicketDiscount());
			res.ticketPriceList.add(b.getTicketPrice());
			res.ticketQRcodeList.add(b.getTicketQrcodeUrl());
			res.ticketStatusList.add(b.getStatus());
		}
		return res; 
	}
	
	@GetMapping("/getMemberOwnBooking")  //用作在會員中心點擊車票訂單 就可以查看該訂單所購買的車票
	public @ResponseBody DisplayMemberBookingTicketDto getMemeberOwnedBooking(HttpServletRequest req) {
		Cookie []cookies = req.getCookies();
		String token=null;
		LoginResponseModel userDetail = null;
		if(cookies==null) {
			return new DisplayMemberBookingTicketDto();
		}
		for( Cookie ck: cookies) {
			if( ck.getName().equals("login-token")) {
				token = ck.getValue();
			}
		}
		if(token==null) {
			// redirect to MemberSystem
			return new DisplayMemberBookingTicketDto();
		}
		else{
			//validate the current login token 
			userDetail = uServ.tokenlogin(UUID.fromString(token));
		}
		if(userDetail==null) {
			return new DisplayMemberBookingTicketDto();
		}
		List<Booking> bList  = bServ.findByMember( userDetail.getMember_id().toString() );
		DisplayMemberBookingTicketDto res = new DisplayMemberBookingTicketDto();
		res.ticketRailRouteSegment=bList.get(0).getRailRouteSegment();
		res.ticketSchedule= bList.get(0).getSchedule();
		res.bookingIdList = new ArrayList<>();
		res.seatList = new ArrayList<>();
		res.ticketDiscountList = new ArrayList<>();
		res.ticketPriceList = new ArrayList<>();
		res.ticketQRcodeList = new ArrayList<>();
		res.ticketStatusList = new ArrayList<>();
		for( Booking b: bList) {
			res.bookingIdList.add(b.getBookingId());
			res.seatList.add(b.getSeat());
			res.ticketDiscountList.add(b.getTicketDiscount());
			res.ticketPriceList.add(b.getTicketPrice());
			res.ticketQRcodeList.add(b.getTicketQrcodeUrl());
			res.ticketStatusList.add(b.getStatus());
		}
		return res;
	}
	
	@PostMapping("/allocateBooking")
	public @ResponseBody ResponseEntity<String> allocateBooking(@RequestBody AllocateTicketDto dto,HttpServletRequest req){
		String srcToken=dto.srcMemberToken;
		String desToken =dto.desMemberToken;
		LoginResponseModel srcUserDetail = null;
		LoginResponseModel desUserDetail = null;
		if(srcToken==null) {
			// redirect to MemberSystem
			return new ResponseEntity<String>("查無login-token",HttpStatus.UNAUTHORIZED);
		}
		else{
			//validate the current login token 
			srcUserDetail = uServ.tokenlogin(UUID.fromString(srcToken));
		}
		if(srcUserDetail==null) {
			return new ResponseEntity<String>("des login-token驗證失敗",HttpStatus.UNAUTHORIZED);
		}
		if(desToken==null) {
			// redirect to MemberSystem
			return new ResponseEntity<String>("查無login-token",HttpStatus.UNAUTHORIZED);
		}
		else{
			//validate the current login token 
			desUserDetail = uServ.tokenlogin(UUID.fromString(desToken));
		}
		if(desUserDetail==null) {
			return new ResponseEntity<String>("des login-token驗證失敗",HttpStatus.UNAUTHORIZED);
		}
		try {
			bServ.allocateBooking(dto.srcMemberToken, dto.desMemberToken, dto.bookingId);
			return new ResponseEntity<String> ("success",HttpStatus.OK);			
		}catch( PersistenceException pex) {
			pex.printStackTrace();
		}
		return new ResponseEntity<String>("failed",HttpStatus.OK);
	}
	
	@PostMapping("/createTicketQRCode")
	public @ResponseBody ResponseEntity<String> createTicketQRCode(@RequestBody CreateBookingQRDto dto){
		try {
			bServ.createBookingQRcode(dto.bookingId, dto.url);
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}catch( PersistenceException pex) {
			pex.printStackTrace();
		}
		return new ResponseEntity<String>("failed",HttpStatus.CONFLICT);
	}
	
	@PostMapping("/verifyTicketQRcode")
	public @ResponseBody ResponseEntity<String> verifyTicketQRcode(@RequestBody Integer bookingid){
		if( bServ.verifyBookingQRcode(bookingid)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("failed",HttpStatus.BAD_REQUEST);
	}
}