package com.myHighSpeedRail.marc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.dto.RailRouteDto;
import com.myHighSpeedRail.marc.model.RailRoute;
import com.myHighSpeedRail.marc.repository.RailRouteRepository;
import com.myHighSpeedRail.marc.repository.StationRepository;

@Service
public class RailRouteService {
	@Autowired
	private RailRouteRepository rrDao;
	@Autowired
	private StationRepository sDao;
	
	public List<RailRouteDto> getAll(){
		List<RailRoute> rrl = rrDao.findAll();
		
		List<RailRouteDto> rrdl = new ArrayList<RailRouteDto>();
		for( RailRoute rr : rrl) {
			rrdl.add( new RailRouteDto(rr.getRailRouteId(), rr.getDepartStation(), rr.getDestinateStation(), rr.getStopStationCount()));
		}
		return rrdl;
	}
	public RailRoute getById(Integer id) throws Exception{
		return rrDao.findById(id).get();
	}
	public List<RailRouteDto> insertRoute(Integer depStationId,Integer desStationid,Integer stopStationCnt
			){
		try {
			
			rrDao.save(new RailRoute( sDao.findById(depStationId).get(), sDao.findById(desStationid).get(), stopStationCnt));
		}catch(Exception e){
			System.err.println("[Error] occur in RailRouteController or Failed of Find Station by Id");
		}
		return getAll();
	}
	public Optional<RailRoute> rrFindById( Integer rid) {
		return rrDao.findById(rid);
	}
	
	public List<RailRoute>getAllByHQL(){
		return rrDao.getAllRailRouteByHQL();
	}
}
