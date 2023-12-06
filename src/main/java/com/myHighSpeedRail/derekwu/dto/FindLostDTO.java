package com.myHighSpeedRail.derekwu.dto;

import java.util.Date;

import com.myHighSpeedRail.derekwu.model.FindLost;

public class FindLostDTO {
	
	
	public FindLost addNewFindLost(Integer id) {
		FindLost findLost = new FindLost();
		findLost.setFindLostId(id);
		findLost.setFindLostDate(new Date());
		System.out.println(findLost);
		return findLost;
	}

}
