package com.myHighSpeedRail.marc.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.StationRepository;
@Service
public class StationService {
	@Autowired
	public StationRepository sDao;
	
	public List<Station> getAllStation() {
		return sDao.findAll();
	}
	
	public List<Station> insert(String name){
		sDao.save(new Station(name));
		return sDao.findAll();
	}
	public Station save(Station s) {
		return sDao.save(s);
	}
	
	public List<Station> findByUsingName(String name){
		return sDao.findByName(name);
	}
	
	public Optional<Station> findById(Integer id){
		return sDao.findById(id);
	}
	public Map<Integer, Station> getAllStationMap(){
		List<Station> ss = sDao.findAll();
		Map<Integer, Station> smap = new TreeMap<Integer, Station>();
		for( Station st : ss) {
			smap.put(st.getStationId(), st);
		}
		return smap;
	}
	
}
