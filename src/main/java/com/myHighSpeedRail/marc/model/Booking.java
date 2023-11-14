package com.myHighSpeedRail.marc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
	@Id
	@Column(name="bookding_id",nullable=false)
	private Integer bookingId;
	
	@Column(name="member_token",nullable=true)
	private String memberToken;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_order_id_fk",nullable=false)
	private TicketOrder ticketOrder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="schedule_id_fk",nullable=false)
	private Schedule schedule;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rai_route_segment_id_fk",nullable=false)
	private RailRouteSegment railRouteSegment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seat_id_fk",nullable=true)
	private Seat seat;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ticket_discount_id_fk",nullable=false)
	private TicketDiscount ticketDiscount;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Column(name="ticket_price",nullable=false)
	private Integer ticketPrice;
	
	@Column(name="ticket_qrcode_url",nullable=true)
	private String ticketQrcodeUrl;
	

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(String memberToken, TicketOrder ticketOrder, Schedule schedule,
			RailRouteSegment railRouteSegment, Seat seat, TicketDiscount ticketDiscount, String status,
			int ticketPrice, String ticketQrcodeUrl) {
		this.memberToken = memberToken;
		this.ticketOrder = ticketOrder;
		this.schedule = schedule;
		this.railRouteSegment = railRouteSegment;
		this.seat = seat;
		this.ticketDiscount = ticketDiscount;
		this.status = status;
		this.ticketPrice = ticketPrice;
		this.ticketQrcodeUrl = ticketQrcodeUrl;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	
	public String getMemberToken() {
		return memberToken;
	}

	public void setMemberToken(String memberToken) {
		this.memberToken = memberToken;
	}

	public TicketOrder getTicketOrder() {
		return ticketOrder;
	}

	public void setTicketOrder(TicketOrder ticketOrder) {
		this.ticketOrder = ticketOrder;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public RailRouteSegment getRailRouteSegment() {
		return railRouteSegment;
	}

	public void setRailRouteSegment(RailRouteSegment railRouteSegment) {
		this.railRouteSegment = railRouteSegment;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public TicketDiscount getTicketDiscount() {
		return ticketDiscount;
	}

	public void setTicketDiscount(TicketDiscount ticketDiscount) {
		this.ticketDiscount = ticketDiscount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getTicketQrcodeUrl() {
		return ticketQrcodeUrl;
	}

	public void setTicketQrcodeUrl(String ticketQrcodeUrl) {
		this.ticketQrcodeUrl = ticketQrcodeUrl;
	}
}
