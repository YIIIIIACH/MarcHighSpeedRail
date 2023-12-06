package com.myHighSpeedRail.derekwu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.myHighSpeedRail.derekwu.model.FindLost;
import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.FindLostRepository;

@Service
public class FindLostService {
	@Autowired
	private FindLostRepository flRepo;

	public void addNewFindLost(Integer id, @RequestBody LostProperty lp) {
		FindLost findLost = new FindLost();
		findLost.setFindLostId(id);
		findLost.setFindLostDate(new Date());
		findLost.setLostProperty(lp);
		System.out.println(findLost);

		flRepo.save(findLost);

	}
}
