package com.myHighSpeedRail.marc.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket_discount")
public class TicketDiscount {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_discount_id" , nullable=false)
	private Integer ticketDiscountId;
	@Column(name="ticket_discount_type",nullable=false)
	private String ticketDiscountType; 
	@Column(name="ticket_discount_name",nullable=false)
	private String ticketDiscountName;
	@Column(name="ticket_discount_percentage",nullable=true)
	private Integer ticketDiscountPercentage;
	@Column(name="ticket_discount_amount", nullable=true)
	private Integer ticketDiscountAmount;
	@Column(name="purchase_early_limit_day",nullable=false)
	private Integer purchaseEarlyLimitDay;
	
	public TicketDiscount() {;}
	public TicketDiscount(String tDicType, String tDicName, Integer tDicPerc,Integer tDicAm, Integer purEarlyDay) {
		this.ticketDiscountId= null;
		this.ticketDiscountType= tDicType;
		this.ticketDiscountName= tDicName;
		this.ticketDiscountPercentage= tDicPerc;
		this.ticketDiscountAmount= tDicAm;
		this.purchaseEarlyLimitDay = purEarlyDay;
	}
	
	public Integer getTicketDiscountId() {
		return ticketDiscountId;
	}
	public void setTicketDiscountId(Integer ticketDiscountId) {
		this.ticketDiscountId = ticketDiscountId;
	}
	public String getTicketDiscountType() {
		return ticketDiscountType;
	}
	public void setTicketDiscountType(String ticketDiscountType) {
		this.ticketDiscountType = ticketDiscountType;
	}
	public String getTicketDiscountName() {
		return ticketDiscountName;
	}
	public void setTicketDiscountName(String ticketDiscountName) {
		this.ticketDiscountName = ticketDiscountName;
	}
	public Integer getTicketDiscountPercentage() {
		return ticketDiscountPercentage;
	}
	public void setTicketDiscountPercentage(Integer ticketDiscountPercentage) {
		this.ticketDiscountPercentage = ticketDiscountPercentage;
	}
	public Integer getTicketDiscountAmount() {
		return ticketDiscountAmount;
	}
	public void setTicketDiscountAmount(Integer ticketDiscountAmount) {
		this.ticketDiscountAmount = ticketDiscountAmount;
	}
	public Integer getPurchaseEarlyLimitDay() {
		return purchaseEarlyLimitDay;
	}
	public void setPurchaseEarlyLimitDay(Integer purchaseEarlyLimitDay) {
		this.purchaseEarlyLimitDay = purchaseEarlyLimitDay;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ticketDiscountId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketDiscount other = (TicketDiscount) obj;
		return Objects.equals(ticketDiscountId, other.ticketDiscountId);
	}
	
	
}
