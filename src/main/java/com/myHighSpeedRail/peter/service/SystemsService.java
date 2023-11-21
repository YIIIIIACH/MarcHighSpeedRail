package com.myHighSpeedRail.peter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.model.Systems;
import com.myHighSpeedRail.peter.repoistory.SystemAuthorRepository;
import com.myHighSpeedRail.peter.repoistory.SystemsRepository;

@Service
public class SystemsService {

	@Autowired
	private SystemAuthorRepository saDao;

	@Autowired
	private SystemsRepository sDao;

	public void addSystem(Systems sys) {
		sDao.save(sys);
	}

	public void addSystemAuthor(SystemAuthor sysau) {
		saDao.save(sysau);
	}

	public void deleteById(Integer id) {
		sDao.deleteById(id);
	}

	public Systems findSystemById(Integer sysId) {

		Optional<Systems> optional = sDao.findById(sysId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;

	}

	public Systems findSystemByName(String name) {
		Optional<Systems> optional = sDao.findBysystemName(name);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
