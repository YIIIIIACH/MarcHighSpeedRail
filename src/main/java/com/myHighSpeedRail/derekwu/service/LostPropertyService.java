package com.myHighSpeedRail.derekwu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

@Service
public class LostPropertyService {
	
	@Autowired
	private LostPropertyRepository LPDao;
	
	public void addLostProperty(LostProperty lp) {
		LPDao.save(lp);
	}
}
