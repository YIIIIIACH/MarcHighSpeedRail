package com.myHighSpeedRail.marc.controller;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.dto.BookingDto;
import com.myHighSpeedRail.marc.model.Booking;
import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.Seat;
import com.myHighSpeedRail.marc.model.TicketDiscount;
import com.myHighSpeedRail.marc.model.TicketOrder;
import com.myHighSpeedRail.marc.service.BookingService;
import com.myHighSpeedRail.marc.service.RailRouteSegmentService;
import com.myHighSpeedRail.marc.service.RailRouteStopStationService;
import com.myHighSpeedRail.marc.service.ScheduleArriveService;
import com.myHighSpeedRail.marc.service.ScheduleRestSeatService;
import com.myHighSpeedRail.marc.service.ScheduleService;
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
	@PostMapping("/booking")
	public @ResponseBody ResponseEntity<String> doBookingAndMakeEmptyTicket(HttpServletRequest req,@RequestBody BookingDto bookingDto){
		//Cookie cookie = new Cookie("login-token", "e7039cb4-ee63-47fa-8f79-3585bd4c73a2");
		Cookie []cookies = req.getCookies();
		String token=null;
		for( Cookie ck: cookies) {
			if( ck.getName().equals("login-token")) {
				token = ck.getValue();
			}
		}
		if(token==null) {
			// redirect to MemberSystem
			;
		}else {
			//validate the current login token 
			;
		}
		// temporary treatment
		token = "e7039cb4-ee63-47fa-8f79-3585bd4c73a2";
		
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
		return new ResponseEntity<String>("booking success",HttpStatus.OK);
	}
}
