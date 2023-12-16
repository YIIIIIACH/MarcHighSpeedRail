package com.myHighSpeedRail.marc.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="ticket_order")
public class TicketOrder {
	@Id
	@Column(name="ticket_order_id",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ticketOrderId;
	
	@Column( name="member_token",nullable=true)
	private String memberToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="ticket_order_create_time",nullable=false)
	private Date ticketOrderCreateTime;
	
	@Column(name="status",nullable=false)
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name="payment_deadline",nullable=false)
	private Date paymentDeadline;
	
	@Column(name="total_price",nullable=false)
	private Integer totalPrice;

	@Column(name="paypal_order_id")
	private String paypalOrderId;
	public TicketOrder() {;}

	public TicketOrder(String  memberToken, Date ticketOrderCreateTime, String status,
			Date paymentDeadline, Integer totalPrice) {
		super();
		this.ticketOrderId=null;
		this.memberToken = memberToken;
		this.ticketOrderCreateTime = ticketOrderCreateTime;
		this.status = status;
		this.paymentDeadline = paymentDeadline;
		this.totalPrice = totalPrice;
		this.paypalOrderId=null;
	}

	
	public String getPaypalOrderId() {
		return paypalOrderId;
	}
	
	
	public void setPaypalOrderId(String paypalOrderId) {
		this.paypalOrderId = paypalOrderId;
	}
	public String getMemberToken() {
		return memberToken;
	}


	public void setMemberToken(String memberToken) {
		this.memberToken = memberToken;
	}


	public Integer getTicketOrderId() {
		return ticketOrderId;
	}

	public void setTicketOrderId(Integer ticketOrderId) {
		this.ticketOrderId = ticketOrderId;
	}

	public Date getTicketOrderCreateTime() {
		return ticketOrderCreateTime;
	}

	public void setTicketOrderCreateTime(Date ticketOrderCreateTime) {
		this.ticketOrderCreateTime = ticketOrderCreateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPaymentDeadline() {
		return paymentDeadline;
	}

	public void setPaymentDeadline(Date paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
