package com.myHighSpeedRail.marc.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DisplayMemberTicketOrderDto {
	public List<Integer> ticketOrderIds;
	public String memberToken;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone="GMT+8")
	public List<Date> orderCreateTimes;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm",timezone="GMT+8")
	public List<Date> paymentDeadlines;
	public List<String> orderStatuses;
	public List<Integer> totalPrices;
	public String displayStatus;
}
