package com.myHighSpeedRail.peter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
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

	public Long findSystemCount() {
		return sDao.count();
	}

	public List<Systems> findAllSystems() {
		return sDao.findAll();		
	}
	
	public List<SystemAuthor> findAllSystemAuthors(){
		return saDao.findAll();
	}
	
	public SystemAuthor findSystemAuthorByEmployeeId(Integer id) {
		return saDao.findByEmployeeId(id);
	}
	
	public SystemAuthor findSystemAuthorByDepartmentId(Integer id) {
		return saDao.findByDepartmentId(id);
	}
	
	public void updateEmployeeSystemAuthor(SystemAuthor sa) {
		String authorJson = sa.getAuthorJson();
		Integer id = sa.getEmployee().getEmployeeId();
		saDao.updateByEmployeeId(id,authorJson);
	}
	
	public void updateDepartmentSystemAuthor(SystemAuthor sa) {
		String authorJson = sa.getAuthorJson();
		Integer id = sa.getDepartment().getDepartmentId();
		saDao.updateByDepartmentId(id, authorJson);;
	}

}
