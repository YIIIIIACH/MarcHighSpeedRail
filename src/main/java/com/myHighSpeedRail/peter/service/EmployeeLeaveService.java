package com.myHighSpeedRail.peter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.EmployeeLeave;
import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;
import com.myHighSpeedRail.peter.model.Leave;
import com.myHighSpeedRail.peter.repoistory.EmployeeLeaveRepository;
import com.myHighSpeedRail.peter.repoistory.LeaveRepository;

@Service
public class EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveRepository elDao;
	
	@Autowired
	private LeaveRepository lDao;

	public void employeeLeaveApplication(EmployeeLeave e) {
		elDao.save(e);
	}

	public List<EmployeeLeave> findNoAuditWithManagerId(Integer managerId) {
		return elDao.findByManagerIdWithNoAudit(managerId);
	}
	
	public Leave findByLeaveName(String name) {
		return lDao.findByLeaveName(name);
	}
	
	public void setAudit(EmployeeLeave el) {
		elDao.save(el);
	}
	
	public List<EmployeeLeave> findSuccessAudit() {
		return elDao.findBySuccessAudit();
	}
	
	public List<Leave> findAllLeaves() {
		return lDao.findAll();
	}
}
