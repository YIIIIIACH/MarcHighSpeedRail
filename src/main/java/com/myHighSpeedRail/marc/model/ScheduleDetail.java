package com.myHighSpeedRail.marc.model;

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
@Table(name="schedule_detail")
public class ScheduleDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="schedule_detail_id")
	private Integer scheduleDetailId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="schedule_id_fk",nullable=false)
	private Schedule schedule;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usable_discount_id_fk",nullable=false)
	private TicketDiscount ticketDiscount;
	
	@Column(name="seat_range_start",nullable =false)
	private Integer seatRangeStart;
	
	@Column(name="seat_range_end",nullable=false)
	private Integer seatRangeEnd;
	public ScheduleDetail() {;}
	public ScheduleDetail(Schedule schedule, TicketDiscount ticketDiscount, Integer seatRangeStart,
			Integer seatRangeEnd) {
		super();
		this.schedule = schedule;
		this.ticketDiscount = ticketDiscount;
		this.seatRangeStart = seatRangeStart;
		this.seatRangeEnd = seatRangeEnd;
	}

	public Integer getScheduleDetailId() {
		return scheduleDetailId;
	}

	public void setScheduleDetailId(Integer scheduleDetailId) {
		this.scheduleDetailId = scheduleDetailId;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public TicketDiscount getTicketDiscount() {
		return ticketDiscount;
	}

	public void setTicketDiscount(TicketDiscount ticketDiscount) {
		this.ticketDiscount = ticketDiscount;
	}

	public Integer getSeatRangeStart() {
		return seatRangeStart;
	}

	public void setSeatRangeStart(Integer seatRangeStart) {
		this.seatRangeStart = seatRangeStart;
	}

	public Integer getSeatRangeEnd() {
		return seatRangeEnd;
	}

	public void setSeatRangeEnd(Integer seatRangeEnd) {
		this.seatRangeEnd = seatRangeEnd;
	}
}
