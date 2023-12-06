package com.myHighSpeedRail.peter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.Title;
import com.myHighSpeedRail.peter.repoistory.TitleRepository;

@Service
public class TitleService {

	@Autowired
	private TitleRepository tDao;
	
	public List<Title> findAll(){
		return tDao.findAll();
	}
	
	public Title findByName(String name){
		return tDao.findByName(name);
	}
}
