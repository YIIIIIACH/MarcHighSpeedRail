package com.myHighSpeedRail.peter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.StationRepository;

public class TestPeterPackageService {
	@Autowired
	private StationRepository sDao;
	
	public List<Station> findAllStation(){
		return sDao.findAll();
	}
}
