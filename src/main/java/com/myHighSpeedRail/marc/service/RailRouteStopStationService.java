package com.myHighSpeedRail.marc.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.model.RailRouteStopStation;
import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.RailRouteRepository;
import com.myHighSpeedRail.marc.repository.RailRouteStopStationRepository;

@Service
public class RailRouteStopStationService {
	@Autowired
	private RailRouteStopStationRepository rrssDao;
	@Autowired
	private RailRouteService rrS;
	@Autowired
	private StationService sSer;
	public List<RailRouteStopStation> insertStopStation(Integer rid,String sname,Integer seq,Integer costTime){
		Optional<RailRoute> rr = rrS.rrFindById(rid);
		List<Station> ssl = sSer.findByUsingName(sname);
		if ( rr.isPresent() && ssl !=null && ssl.get(0)!=null) {			
			rrssDao.save( new RailRouteStopStation( rr.get(), seq, ssl.get(0),costTime)) ;
		}else {
			System.err.println("station Name not found or route not found");
		}
		return getAllStopStation();
	}
	
	public List<RailRouteStopStation> getAllStopStation(){
		Map<Integer, Station> smap = sSer.getAllStationMap();
		List<RailRouteStopStation> rrssl= rrssDao.findAll();
		for(RailRouteStopStation rrss: rrssl) {
			rrss.setStopStation(new Station(14, smap.get(14).getStationName()));
		}
		return rrssl;
	}
	
	public List<RailRouteStopStation> getStopStationByRouteId(Integer rid){
		return rrssDao.findByRouteId(rid);
	}
	
	public Optional<RailRoute> rrFindById( Integer rid) {
		return rrS.rrFindById(rid);
	}
	public List<Station> findByUsingName(String name){
		return sSer.findByUsingName(name);
	}
	public void save(RailRouteStopStation rrss) {
		rrssDao.save(rrss);
	}
	public List<RailRouteStopStation> findByRouteId(Integer rid){
		return rrssDao.findByRouteId(rid);
	}
	public List<RailRouteStopStation> findAll(){
		return rrssDao.findAll();
	}
	public List<RailRouteStopStation> findByRouteIdStationId(Integer rid, Integer sid){
		return rrssDao.findbyRouteIdStationId(rid, sid);
	}
}
