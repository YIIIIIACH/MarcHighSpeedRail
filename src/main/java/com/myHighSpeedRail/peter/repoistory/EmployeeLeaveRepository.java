package com.myHighSpeedRail.peter.repoistory;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeLeave;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Integer> {

	@Query("from EmployeeLeave as el where el.manager.employeeId = :manId and el.managerLeaveAudit is null")
	List<EmployeeLeave> findByManagerIdWithNoAudit(@Param("manId") Integer managerId);

	@Query("from EmployeeLeave el where el.managerLeaveAudit = 'success' and el.leaveCarryForwardDate is null")
	List<EmployeeLeave> findBySuccessAudit();

	@Query("from EmployeeLeave el where el.leaveAuditResultsSandingDate is not null and el.leaveCarryForwardDate is not null and el.leaveEmployeeConfirmDate is null and el.employee.employeeId =:id")
	List<EmployeeLeave> findUnReadAuditByEmployeeId(@Param("id") Integer Id);
	
	@Query("from EmployeeLeave el where el.leaveAuditResultsSandingDate is not null and el.managerLeaveAudit = 'fail' and el.leaveEmployeeConfirmDate is null and el.employee.employeeId =:id")
	List<EmployeeLeave> findUnReadFailByEmployeeId(@Param("id") Integer Id);

	@Modifying
	@Transactional
	@Query("update EmployeeLeave el set el.leaveEmployeeConfirmDate =:newDate where el.employeeLeaveId = :id")
	void updateEmployeeReadAudit(@Param("newDate") Date newDate, @Param("id") Integer leaveId);
}
