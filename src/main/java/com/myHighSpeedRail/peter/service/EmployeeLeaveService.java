package com.myHighSpeedRail.peter.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.EmployeeLeave;
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

	public List<EmployeeLeave> findUnReadAuditByEmployeeId(Integer id) {
		List<EmployeeLeave> elList = elDao.findUnReadAuditByEmployeeId(id);
		List<EmployeeLeave> failList = elDao.findUnReadFailByEmployeeId(id);
		failList.forEach(f -> {
			elList.add(f);
		});
		return elList;
	}

	public void updateEmployeeReadAudit(Date date, Integer id) {
		elDao.updateEmployeeReadAudit(date, id);
	}

	public Integer findEmployeeLeaveCountByName(String name, Integer id) {
		return elDao.findEmployeeLeaveCountByName(name, id);
	}

}
