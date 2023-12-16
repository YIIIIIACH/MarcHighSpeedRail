package com.myHighSpeedRail.peter.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeLeave;
import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Integer> {

	@Query("from EmployeeLeave as el where el.manager.employeeId = :manId and el.managerLeaveAudit is null")
	List<EmployeeLeave> findByManagerIdWithNoAudit(@Param("manId") Integer managerId);
	
	@Query("from EmployeeLeave el where el.managerLeaveAudit = 'success' and el.leaveCarryForwardDate is null")
	List<EmployeeLeave> findBySuccessAudit();
}

