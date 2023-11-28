package com.myHighSpeedRail.peter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.peter.dto.EmployeeLeaveApplyDTO;
import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;
import com.myHighSpeedRail.peter.service.EmployeeLeaveService;

@RestController
public class LeaveController {

	@Autowired
	private EmployeeLeaveService elService;
	
	@PostMapping("/employee/leave")
	public void employeeLeaveApply(@RequestBody EmployeeLeaveApplyDTO ewoa) {

//		EmployeeWorkOvertime ewo = new EmployeeWorkOvertime();
//		Employee emp = new Employee();
//		emp.setEmployeeId(ewoa.getEmployeeId());
//		Employee man = new Employee();
//		man.setEmployeeId(ewoa.getManagerId());
//
//		ewo.setEmployee(emp);
//		ewo.setManager(man);
//		ewo.setEmployeeWorkOvertimeEndTime(ewoa.getEmployeeWorkOvertimeEndTime());
//		ewo.setEmployeeWorkOvertimeReason(ewoa.getEmployeeWorkOvertimeReason());
//		ewo.setEmployeeWorkOvertimeStartTime(ewoa.getEmployeeWorkOvertimeStartTime());

//		ewoService.employeeWorkOvertimeApplication(ewo);
//		System.out.println("ewo: " + ewo);
	}

//	@ResponseBody
//	@GetMapping("/employee/workovertime/audit")
//	public List<WorkOvertimeAuditDTO> managerGetWorkOvertime(@RequestParam("id") Integer managerId) {
//
//		List<WorkOvertimeAuditDTO> woaList = new LinkedList<WorkOvertimeAuditDTO>();
//
//		List<EmployeeWorkOvertime> ewoList = ewoService.findNoAuditWithManagerId(managerId);
//
//		for (EmployeeWorkOvertime ewo : ewoList) {
//			WorkOvertimeAuditDTO woa = new WorkOvertimeAuditDTO();
//			woa.setEmployeeId(ewo.getEmployee().getEmployeeId());
//			woa.setEmployeeWorkOvertimeEndTime(ewo.getEmployeeWorkOvertimeEndTime());
//			woa.setEmployeeWorkOvertimeReason(ewo.getEmployeeWorkOvertimeReason());
//			woa.setEmployeeWorkOvertimeStartTime(ewo.getEmployeeWorkOvertimeStartTime());
//			woa.setManagerId(ewo.getManager().getEmployeeId());
//			woa.setManagerWorkOvertimeAudit(ewo.getManagerWorkOvertimeAudit());
//			woa.setEmployeeWorkOvertimeId(ewo.getEmployeeWorkOvertimeId());
//			woaList.add(woa);
//		}
//		return woaList;
//	}
//
//	@ResponseBody
//	@GetMapping("/employee/workovertime/all")
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
//
//	@ResponseBody
//	@PutMapping("/employee/workovertime/audit")
//	public void managerAuditWorkOvertime(@RequestBody WorkOvertimeAuditDTO woaDTO) {
//
//		EmployeeWorkOvertime ewo = new EmployeeWorkOvertime();
//
//		Employee emp = new Employee();
//		emp.setEmployeeId(woaDTO.getEmployeeId());
//		Employee man = new Employee();
//		man.setEmployeeId(woaDTO.getManagerId());
//
//		ewo.setEmployee(emp);
//		ewo.setEmployeeWorkOvertimeEndTime(woaDTO.getEmployeeWorkOvertimeEndTime());
//		ewo.setEmployeeWorkOvertimeReason(woaDTO.getEmployeeWorkOvertimeReason());
//		ewo.setEmployeeWorkOvertimeStartTime(woaDTO.getEmployeeWorkOvertimeStartTime());
//		ewo.setManager(man);
//		ewo.setManagerWorkOvertimeAudit(woaDTO.getManagerWorkOvertimeAudit());
//		ewo.setWorkOvertimeAuditResultsSandingDate(woaDTO.getWorkOvertimeAuditResultsSandingDate());
//		ewo.setEmployeeWorkOvertimeId(woaDTO.getEmployeeWorkOvertimeId());
//
//		ewoService.setAudit(ewo);
//	}
//
//	@ResponseBody
//	@GetMapping("/employee/workovertime/carry-forward")
//	public List<WorkOvertimeCarryForwardDTO> getWorkOvertimeCarryForward() {
//
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
//	}
//
//	@ResponseBody
//	@PutMapping("/employee/workovertime/carry-forward")
//	public void workOvertimeCarryForward(@RequestBody WorkOvertimeCarryForwardDTO wocfDTO) {
//
//		EmployeeWorkOvertime ewo = new EmployeeWorkOvertime();
//
//		Employee emp = new Employee();
//		emp.setEmployeeId(wocfDTO.getEmployeeId());
//		Employee man = new Employee();
//		man.setEmployeeId(wocfDTO.getManagerId());
//
//		ewo.setEmployee(emp);
//		ewo.setEmployeeWorkOvertimeEndTime(wocfDTO.getEmployeeWorkOvertimeEndTime());
//		ewo.setEmployeeWorkOvertimeReason(wocfDTO.getEmployeeWorkOvertimeReason());
//		ewo.setEmployeeWorkOvertimeStartTime(wocfDTO.getEmployeeWorkOvertimeStartTime());
//		ewo.setManager(man);
//		ewo.setManagerWorkOvertimeAudit(wocfDTO.getManagerWorkOvertimeAudit());
//		ewo.setWorkOvertimeAuditResultsSandingDate(wocfDTO.getWorkOvertimeAuditResultsSandingDate());
//		ewo.setEmployeeWorkOvertimeId(wocfDTO.getEmployeeWorkOvertimeId());
//		ewo.setWorkOvertimeCarryForwardDate(wocfDTO.getWorkOvertimeCarryForwardDate());
//
//		ewoService.setAudit(ewo);
//	}
}
