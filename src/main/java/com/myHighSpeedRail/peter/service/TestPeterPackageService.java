package com.myHighSpeedRail.peter.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.StationRepository;
import com.myHighSpeedRail.marc.service.StationService;

@Service
public class TestPeterPackageService {
	@Autowired
	private StationService sSer;
	
	public List<Station> findAllStation(){
		return sSer.getAllStation();
	}
}
