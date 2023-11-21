package com.myHighSpeedRail.peter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.repoistory.DepartmentRepository;
import com.myHighSpeedRail.peter.repoistory.EmployeeHistoricalDepartmentRepository;

@Service
public class DepartmentService {

	public DepartmentRepository dDao;

	public EmployeeHistoricalDepartmentRepository ehdDao;

	public SystemAuthor getDepartmentSystemAuthor(Integer id) {
		Department dept = dDao.findByDepartmentIdJoinSystemAuthor(id);
		List<SystemAuthor> authorList = dept.getSystemAuthor();
		return authorList.get(0);
	}

}
