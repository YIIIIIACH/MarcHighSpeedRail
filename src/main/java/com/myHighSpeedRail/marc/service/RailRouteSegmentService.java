package com.myHighSpeedRail.marc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.repository.RailRouteSegmentRepository;

@Service
public class RailRouteSegmentService {
	@Autowired
	private RailRouteSegmentRepository rrsDao;
	
	@Autowired
	private RailRouteStopStationService rrsss;
	@Autowired
	private StationService ss;
	@Autowired
	private RailRouteService rrs;
	
	public void createAllSegmentByRouteId(Integer rId) {
		List<RailRouteStopStation> rrssl= rrsss.findByRouteId(rId);
		int s1=0;
		if( rrssl.size()<2)return;
		for(; s1 <rrssl.size()-1; s1++) {
			for( int s2=s1+1; s2<rrssl.size(); s2++) {
				rrsDao.save( new RailRouteSegment(
							rrs.rrFindById(rId).get(),
							rrssl.get(s1).getStopStation(),
							rrssl.get(s2).getStopStation(),
							100,
							100
						)
					);
				
				
			}
		}
	}
	
	public List<RailRouteSegment> findAll(){
		return rrsDao.findAll();
	}
}