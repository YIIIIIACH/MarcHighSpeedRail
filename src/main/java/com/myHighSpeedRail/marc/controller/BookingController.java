package com.myHighSpeedRail.marc.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.AllocateTicketDto;
import com.myHighSpeedRail.marc.dto.DisplayMemberBookingTicketDto;
import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.model.TicketOrder;
import com.myHighSpeedRail.marc.service.BookingService;
import com.myHighSpeedRail.marc.service.TicketOrderService;

@Controller
public class BookingController {
	@Autowired
	private BookingService bServ;
	@Autowired
	private TicketOrderService tckodServ;
	
	@GetMapping("/getBookingByTicketOrder/{memuuid}/{tckodId}")// 用作查詢訂票紀錄使用
	public @ResponseBody DisplayMemberBookingTicketDto getBookingByTicketOrder(@PathVariable String memuuid, @PathVariable Integer tckodId) {
		// verify memuuid and tckodId
		TicketOrder tckod = tckodServ.findById(tckodId);
		if( !tckod.getMemberToken().equals(memuuid)) {
			 return new DisplayMemberBookingTicketDto();
		}
		
		List<Booking> bList = bServ.findByTicketOrderId(tckodId);
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
	
	@GetMapping("/getMemberOwnBooking/{memuuid}")  //用作在會員中心點擊車票訂單 就可以查看該訂單所購買的車票
	public @ResponseBody DisplayMemberBookingTicketDto getMemeberOwnedBooking( @PathVariable String memuuid) {
		List<Booking> bList  = bServ.findByMember(memuuid);
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
	public @ResponseBody ResponseEntity<String> allocateBooking(@RequestBody AllocateTicketDto dto){
		// check desMemberToken is valid;
		 // to be continue
		//
		int resStatus = bServ.allocateBooking(dto.srcMemberToken, dto.desMemberToken, dto.bookingId);
		if( resStatus > 0) {
			return new ResponseEntity<String> ("allocate success",HttpStatus.OK);			
		}else if (resStatus< 0) {
			return new ResponseEntity<String>("server error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("target booking or user not found",HttpStatus.OK);
	}
}
