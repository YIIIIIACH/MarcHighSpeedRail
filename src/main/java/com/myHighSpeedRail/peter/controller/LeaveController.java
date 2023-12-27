package com.myHighSpeedRail.peter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.peter.dto.EmployeeLeaveApplyDTO;
import com.myHighSpeedRail.peter.dto.EmployeeReadLeaveDTO;
import com.myHighSpeedRail.peter.dto.LeaveAuditDTO;
import com.myHighSpeedRail.peter.dto.LeaveCarryForwardDTO;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.EmployeeLeave;
import com.myHighSpeedRail.peter.model.Leave;
import com.myHighSpeedRail.peter.service.EmployeeLeaveService;
import com.myHighSpeedRail.peter.vo.SessionLoginEmployee;

import jakarta.servlet.http.HttpSession;

@RestController
public class LeaveController {

	@Autowired
	private EmployeeLeaveService elService;

	@PostMapping("/employee/leave")
	public ResponseEntity<?> employeeLeaveApply(@RequestBody EmployeeLeaveApplyDTO ela, HttpSession httpSession) {

		SessionLoginEmployee sessionEmp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");

		if (sessionEmp == null) {
			System.out.println("session attribute 空的");
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		}

		EmployeeLeave el = new EmployeeLeave();
		Employee emp = new Employee();
		emp.setEmployeeId(sessionEmp.getEmpId());
		Employee man = new Employee();
		man.setEmployeeId(ela.getManagerId());
		Leave leave = elService.findByLeaveName(ela.getEmployeeLeaveKind());
		System.out.println("leave: " + leave.getLeaveId());

		el.setEmployee(emp);
		el.setManager(man);
		el.setEmployeeLeaveEndTime(ela.getEmployeeLeaveEndTime());
		el.setEmployeeLeaveReason(ela.getEmployeeLeaveReason());
		el.setEmployeeLeaveStartTime(ela.getEmployeeLeaveStartTime());
		el.setLeave(leave);

		elService.employeeLeaveApplication(el);

		return ResponseEntity.status(HttpStatus.OK).body("申請成功");

	}

	@ResponseBody
	@GetMapping("/employee/leave/audit")
	public List<LeaveAuditDTO> managerGetLeave(@RequestParam("id") Integer managerId) {

		LinkedList<LeaveAuditDTO> laList = new LinkedList<LeaveAuditDTO>();

		List<EmployeeLeave> elList = elService.findNoAuditWithManagerId(managerId);

		for (EmployeeLeave el : elList) {
			LeaveAuditDTO la = new LeaveAuditDTO();
			la.setEmployeeId(el.getEmployee().getEmployeeId());
			la.setEmployeeLeaveEndTime(el.getEmployeeLeaveEndTime());
			la.setEmployeeLeaveId(el.getEmployeeLeaveId());
			la.setEmployeeLeaveReason(el.getEmployeeLeaveReason());
			la.setEmployeeLeaveStartTime(el.getEmployeeLeaveStartTime());
			la.setLeaveAuditResultsSandingDate(el.getLeaveAuditResultsSandingDate());
			la.setManagerId(el.getManager().getEmployeeId());
			la.setManagerLeaveAudit(el.getManagerLeaveAudit());
			la.setEmployeeLeaveKind(el.getLeave().getLeaveName());
			la.setDiscription(el.getLeave().getAvailableLeaveDaysDescription());
			Integer i = elService.findEmployeeLeaveCountByName(el.getLeave().getLeaveName(),
					el.getEmployee().getEmployeeId());
			la.setCount(i);
			laList.add(la);
		}

		return laList;
	}

//	@ResponseBody
//	@GetMapping("/employee/leave/all")
//	public List<WorkOvertimeGetDTO> testfindAll() {
//
//		List<WorkOvertimeGetDTO> woList = new LinkedList<WorkOvertimeGetDTO>();
//
//		List<EmployeeWorkOvertime> findAllEWO = ewoService.findAllEWO();
//		for (EmployeeWorkOvertime ewo : findAllEWO) {
//			WorkOvertimeGetDTO wo = new WorkOvertimeGetDTO();
//			wo.setEmployeeId(ewo.getEmployee().getEmployeeId());
//			wo.setEmployeeWorkOvertimeEndTime(ewo.getEmployeeWorkOvertimeEndTime());
//			wo.setEmployeeWorkOvertimeReason(ewo.getEmployeeWorkOvertimeReason());
//			wo.setEmployeeWorkOvertimeStartTime(ewo.getEmployeeWorkOvertimeStartTime());
//			wo.setManagerId(ewo.getManager().getEmployeeId());
//			wo.setManagerWorkOvertimeAudit(ewo.getManagerWorkOvertimeAudit());
//			wo.setWorkOvertimeAuditResultsSandingDate(ewo.getWorkOvertimeAuditResultsSandingDate());
//			wo.setWorkOvertimeCarryForwardDate(ewo.getWorkOvertimeCarryForwardDate());
//			wo.setWorkOvertimeEmployeeConfirmDate(ewo.getWorkOvertimeEmployeeConfirmDate());
//			woList.add(wo);
//		}
//		return woList;
//	}

	@ResponseBody
	@PutMapping("/employee/leave/audit")
	public void managerAuditLeave(@RequestBody LeaveAuditDTO laDTO) {

		EmployeeLeave el = new EmployeeLeave();

		Employee emp = new Employee();
		emp.setEmployeeId(laDTO.getEmployeeId());
		Employee man = new Employee();
		man.setEmployeeId(laDTO.getManagerId());
		Leave leave = elService.findByLeaveName(laDTO.getEmployeeLeaveKind());

		el.setEmployee(emp);
		el.setEmployeeLeaveEndTime(laDTO.getEmployeeLeaveEndTime());
		el.setEmployeeLeaveId(laDTO.getEmployeeLeaveId());
		el.setEmployeeLeaveReason(laDTO.getEmployeeLeaveReason());
		el.setEmployeeLeaveStartTime(laDTO.getEmployeeLeaveStartTime());
		el.setLeave(leave);
		el.setLeaveAuditResultsSandingDate(laDTO.getLeaveAuditResultsSandingDate());
		el.setManager(man);
		el.setManagerLeaveAudit(laDTO.getManagerLeaveAudit());

		elService.setAudit(el);
	}

	@ResponseBody
	@GetMapping("/employee/leave/carry-forward")
	public List<LeaveCarryForwardDTO> getLeaveCarryForward() {

//		List<WorkOvertimeCarryForwardDTO> wocfList = new LinkedList<WorkOvertimeCarryForwardDTO>();
//
//		List<EmployeeWorkOvertime> findSuccessAudit = ewoService.findSuccessAudit();
//		for (EmployeeWorkOvertime ewo : findSuccessAudit) {
//			WorkOvertimeCarryForwardDTO wo = new WorkOvertimeCarryForwardDTO();
//			wo.setEmployeeWorkOvertimeId(ewo.getEmployeeWorkOvertimeId());
//			wo.setEmployeeId(ewo.getEmployee().getEmployeeId());
//			wo.setEmployeeWorkOvertimeEndTime(ewo.getEmployeeWorkOvertimeEndTime());
//			wo.setEmployeeWorkOvertimeReason(ewo.getEmployeeWorkOvertimeReason());
//			wo.setEmployeeWorkOvertimeStartTime(ewo.getEmployeeWorkOvertimeStartTime());
//			wo.setManagerId(ewo.getManager().getEmployeeId());
//			wo.setManagerWorkOvertimeAudit(ewo.getManagerWorkOvertimeAudit());
//			wo.setWorkOvertimeAuditResultsSandingDate(ewo.getWorkOvertimeAuditResultsSandingDate());
//			wo.setWorkOvertimeCarryForwardDate(ewo.getWorkOvertimeCarryForwardDate());
//			wocfList.add(wo);
//		}
//		return wocfList;

		List<LeaveCarryForwardDTO> lcfList = new LinkedList<LeaveCarryForwardDTO>();

		List<EmployeeLeave> findSuccessAudit = elService.findSuccessAudit();
		for (EmployeeLeave el : findSuccessAudit) {
			LeaveCarryForwardDTO l = new LeaveCarryForwardDTO();
			l.setEmployeeLeaveId(el.getEmployeeLeaveId());
			l.setEmployeeId(el.getEmployee().getEmployeeId());
			l.setEmployeeLeaveEndTime(el.getEmployeeLeaveEndTime());
			l.setEmployeeLeaveReason(el.getEmployeeLeaveReason());
			l.setEmployeeLeaveStartTime(el.getEmployeeLeaveStartTime());
			l.setLeaveAuditResultsSandingDate(el.getLeaveAuditResultsSandingDate());
			l.setLeaveCarryForwardDate(el.getLeaveCarryForwardDate());
			l.setManagerId(el.getManager().getEmployeeId());
			l.setManagerLeaveAudit(el.getManagerLeaveAudit());
			l.setEmployeeLeaveKind(el.getLeave().getLeaveName());
			lcfList.add(l);
		}

		return lcfList;
	}

	@ResponseBody
	@PutMapping("/employee/leave/carry-forward")
	public void leaveCarryForward(@RequestBody LeaveCarryForwardDTO lcfDTO) {

		EmployeeLeave el = new EmployeeLeave();

		Employee emp = new Employee();
		emp.setEmployeeId(lcfDTO.getEmployeeId());
		Employee man = new Employee();
		man.setEmployeeId(lcfDTO.getManagerId());
		Leave leave = elService.findByLeaveName(lcfDTO.getEmployeeLeaveKind());

		el.setEmployee(emp);
		el.setEmployeeLeaveEndTime(lcfDTO.getEmployeeLeaveEndTime());
		el.setEmployeeLeaveId(lcfDTO.getEmployeeLeaveId());
		el.setEmployeeLeaveReason(lcfDTO.getEmployeeLeaveReason());
		el.setEmployeeLeaveStartTime(lcfDTO.getEmployeeLeaveStartTime());
		el.setLeave(leave);
		el.setLeaveAuditResultsSandingDate(lcfDTO.getLeaveAuditResultsSandingDate());
		el.setLeaveCarryForwardDate(lcfDTO.getLeaveCarryForwardDate());
		el.setManager(man);
		el.setManagerLeaveAudit(lcfDTO.getManagerLeaveAudit());

		elService.setAudit(el);
	}

	@ResponseBody
	@GetMapping("/leave/all")
	public List<Leave> getAllLeaveLeaves() {
		return elService.findAllLeaves();
	}

	@ResponseBody
	@GetMapping("/employee/leave/unread")
	public ResponseEntity<?> getUnReadAuditByEmployeeId(HttpSession httpSession) {

		SessionLoginEmployee emp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");

		if (emp == null) {
			System.out.println("session attribute 空的");
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		}

		List<EmployeeLeave> elList = elService.findUnReadAuditByEmployeeId(emp.getEmpId());

		List<EmployeeReadLeaveDTO> erlList = new ArrayList<EmployeeReadLeaveDTO>();

		for (EmployeeLeave el : elList) {
			EmployeeReadLeaveDTO erlDTO = new EmployeeReadLeaveDTO();
			erlDTO.setEmployeeId(emp.getEmpId());
			erlDTO.setEmployeeLeaveEndTime(el.getEmployeeLeaveEndTime());
			erlDTO.setEmployeeLeaveId(el.getEmployeeLeaveId());
			erlDTO.setEmployeeLeaveReason(el.getEmployeeLeaveReason());
			erlDTO.setEmployeeLeaveStartTime(el.getEmployeeLeaveStartTime());
			erlDTO.setManagerLeaveAudit(el.getManagerLeaveAudit());
			erlList.add(erlDTO);
		}

		return ResponseEntity.status(HttpStatus.OK).body(erlList);
	}

	@ResponseBody
	@PutMapping("/employee/leave/read")
	public ResponseEntity<?> employeeReadAudit(HttpSession httpSession) {

		SessionLoginEmployee emp = (SessionLoginEmployee) httpSession.getAttribute("loginEmployee");

		if (emp == null) {
			System.out.println("session attribute 空的");
			return new ResponseEntity<String>("session attribute null", HttpStatus.UNAUTHORIZED); // 401
		}

		List<EmployeeLeave> elList = elService.findUnReadAuditByEmployeeId(emp.getEmpId());
		for (EmployeeLeave el : elList) {

			Date date = new Date();
			elService.updateEmployeeReadAudit(date, el.getEmployeeLeaveId());

		}

		return ResponseEntity.status(HttpStatus.OK).body("狀態更新成功");
	}

	@ResponseBody
	@GetMapping("/employee/leave/count")
	public ResponseEntity<?> findEmployeeLeaveCountByLeaveName() {

		Integer i = elService.findEmployeeLeaveCountByName("普通傷病假", 1006);

		return ResponseEntity.status(HttpStatus.OK).body(i);
	}

}
