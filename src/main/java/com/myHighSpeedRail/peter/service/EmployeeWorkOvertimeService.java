package com.myHighSpeedRail.peter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;
import com.myHighSpeedRail.peter.repoistory.EmployeeWorkOvertimeRepository;

@Service
public class EmployeeWorkOvertimeService {

	@Autowired
	private EmployeeWorkOvertimeRepository ewotDao;

	public void employeeWorkOvertimeApplication(EmployeeWorkOvertime e) {
		ewotDao.save(e);
	}

	public List<EmployeeWorkOvertime> findNoAuditWithManagerId(Integer managerId) {
		return ewotDao.findByManagerIdWithNoAudit(managerId);
	}

	public List<EmployeeWorkOvertime> findAllEWO() {
		return ewotDao.findAll();
	}

	public void setAudit(EmployeeWorkOvertime ewo) {
		ewotDao.save(ewo);
	}

	public List<EmployeeWorkOvertime> findSuccessAudit() {
		return ewotDao.findBySuccessAudit();
	}

}
