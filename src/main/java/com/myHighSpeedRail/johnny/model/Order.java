package com.myHighSpeedRail.johnny.model;

import java.util.Date;

import com.myHighSpeedRail.marc.model.Station;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private Integer orderId;
	
	@Column(name = "order_creation_date", nullable = false)
	private Date orderCreationDate;
	
	@Column(name = "order_completion_date")
	private Date orderCompletionDate;
	
	@Column(name = "order_status", nullable = false)
	private String orderStatus;
	
	@Column(name = "total_price", nullable = false)
	private Integer totalPrice;
	
	@Column(name = "member_uuid", nullable = false)
	private String member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "station_id_fk", nullable = false)
	private	Station station;

	public Order() {
		super();
	}

	public Order(Integer orderId, Date orderCreationDate, Date orderCompletionDate, String orderStatus,
			Integer totalPrice, String member, Station station) {
		super();
		this.orderId = orderId;
		this.orderCreationDate = orderCreationDate;
		this.orderCompletionDate = orderCompletionDate;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.member = member;
		this.station = station;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getOrderCreationDate() {
		return orderCreationDate;
	}

	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}

	public Date getOrderCompletionDate() {
		return orderCompletionDate;
	}

	public void setOrderCompletionDate(Date orderCompletionDate) {
		this.orderCompletionDate = orderCompletionDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
	
	
	
}
