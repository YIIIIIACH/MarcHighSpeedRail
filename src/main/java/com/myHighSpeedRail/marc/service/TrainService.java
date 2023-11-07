package com.myHighSpeedRail.marc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.marc.model.Train;
import com.myHighSpeedRail.marc.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	private TrainRepository tDao;
	
	public List<Train> insertTrain(Train t){
		tDao.save(t);
		return findAll();
	}
	
	public List<Train> findAll(){
		return tDao.findAll();
	}
	
	public Train findById(Integer tid) {
		return tDao.findById(tid).get();
	}
	
	public void save(Train t) {
		tDao.save(t);
	}
}
