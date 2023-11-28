package com.myHighSpeedRail.peter.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;

@Repository
public interface EmployeeWorkOvertimeRepository extends JpaRepository<EmployeeWorkOvertime, Integer> {

	@Query("from EmployeeWorkOvertime where manager.employeeId = :manId and managerWorkOvertimeAudit is null")
	List<EmployeeWorkOvertime> findByManagerIdWithNoAudit(@Param("manId") Integer managerId);
	
	@Query("from EmployeeWorkOvertime where managerWorkOvertimeAudit = 'success'")
	List<EmployeeWorkOvertime> findBySuccessAudit();
	
//	@Query("from EmployeeWorkOvertime where managerWorkOvertimeAudit = 'success'")

}
