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
	
	public Boolean createAllSegment( List<Integer> priceList ,List<RailRouteStopStation> rrssList) {
		// check slist len == priceList len
		RailRoute rr = rrssList.get(0).getRailRoute();
		List<Station> sList = rrssList.stream().map((spst)->spst.getStopStation()).toList();
		List<RailRouteSegment> rrsList = new ArrayList<>();
		for( int i=0; i< sList.size()-1; i++) {
			for(int j=i+1; j < sList.size(); j++) {
				rrsList.add(new RailRouteSegment(
						rr,
						sList.get(i),
						sList.get(j),
						segmengPriceCal(i,j, priceList),
						 rrssList.get(j).getCostTimeMinute()- rrssList.get(i).getCostTimeMinute()));
				System.out.println(i+" "+j + " "+ segmengPriceCal(i,j,priceList) +" "+ (rrssList.get(j).getCostTimeMinute()- rrssList.get(i).getCostTimeMinute()) );
			}
		}
		return true;
	}
	private int segmengPriceCal(Integer i, Integer j, List<Integer>priceList) {
		int acc=0; 
		for( ; i<j ;i++) {
			acc+= priceList.get(i);
		}
		return acc;
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
	public List<RailRouteSegment> findByScheduleIdStstEdst(Integer schid,Integer  stid,Integer edid){
		return rrsDao.findByScheduleIdStstEdst(schid, stid ,edid);
	}
	public List<RailRouteSegment> findByRouteIdStartStEndStRange(Integer  rrid,List<RailRouteStopStation> endStRange,List<RailRouteStopStation> startStRange){
		List<Integer> backStIdList = new ArrayList<Integer>();
		List<Integer> frontStIdList = new ArrayList<Integer>();
		for( RailRouteStopStation rrss: endStRange) {  //14
			backStIdList.add(rrss.getStopStation().getStationId());
		}
		for( RailRouteStopStation rrss: startStRange) {  //20 25
			frontStIdList.add(rrss.getStopStation().getStationId());
		}
		return rrsDao.findByStartEndStationExcludeRange(rrid, frontStIdList, backStIdList);
	}
	public Optional<RailRouteSegment> findById( Integer rrsId){
		return rrsDao.findById(rrsId);
	}
}