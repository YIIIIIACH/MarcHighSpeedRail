package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteSegment;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Schedule;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.RailRouteSegmentRepository;

@Service
public class RailRouteSegmentService {
	@Autowired
	private RailRouteSegmentRepository rrsDao;
	
	@Autowired
	private RailRouteStopStationService rrsss;
	@Autowired
	private RailRouteService rrs;
	
	public void createAllSegmentByRouteId(Integer rId) {
		List<RailRouteStopStation> rrssl= rrsss.findByRouteId(rId);
		int s1=0;
		if( rrssl.size()<2)return;
		for(; s1 <rrssl.size()-1; s1++) {
			for( int s2=s1+1; s2<rrssl.size(); s2++) {
				Station st1 = rrssl.get(s1).getStopStation();
				Station st2 = rrssl.get(s2).getStopStation();
				rrsDao.save( new RailRouteSegment(
							rrs.rrFindById(rId).get(),
							st1,
							st2,
							100,
							rrsss.findByRouteIdStationId(rId, st2.getStationId()).get(0).getCostTimeMinute() -rrsss.findByRouteIdStationId(rId, st1.getStationId()).get(0).getCostTimeMinute()
						)
					);
				
				
			}
		}
	}
	
	public List<RailRouteSegment> findAll(){
		return rrsDao.findAll();
	}
	public Optional<RailRouteSegment> findByStopStationId(Integer railRouteId , Integer startStationId ,Integer stopStationId){
		return rrsDao.findByStopStationId(railRouteId,startStationId ,stopStationId);
	}
	public List<RailRouteSegment> findByRailRouteId(Integer rrid){
		return rrsDao.findByRailRouteId(rrid);
	}
	
	public List<RailRoute> findScheduleByStartStationEndStation(Integer stStId,Integer edStId){
		return rrsDao.findByStartStationEndStation(stStId, edStId);
	}
	public List<RailRouteSegment> findByRailRouteIdStartStationEndStation(Integer rrId, Integer stStId, Integer edStId){
		return rrsDao.findByRailRouteIdStartStationEndStation(rrId, stStId, edStId);
	}
	public List<RailRouteSegment> findByRouteIdStartStEndStRange(Integer  rrid,List<RailRouteStopStation> endStRange,List<RailRouteStopStation> startStRange){
		List<Integer> tmp1 = new ArrayList<Integer>();
		List<Integer> tmp2 = new ArrayList<Integer>();
		for( RailRouteStopStation rrss: endStRange) {
			tmp1.add(rrss.getRailRouteStopStationSequence());
		}
		for( RailRouteStopStation rrss: startStRange) {
			tmp2.add(rrss.getRailRouteStopStationSequence());
		}
		return rrsDao.findByStartEndStationSeqRange(rrid, tmp1, tmp2);
	}
}