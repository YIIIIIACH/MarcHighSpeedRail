package com.myHighSpeedRail.peter.repoistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;

@Repository
public interface EmployeeHistoricalDepartmentRepository extends JpaRepository<EmployeeHistoricalDepartment, Integer> {

	@Query(value = "select top(1) * from [MarcHighSpeedRailDB].[dbo].[employee_historical_department] order by [department_effective_date] desc where [employee_id_fk] = :id", nativeQuery = true)
	EmployeeHistoricalDepartment findEmployeeLatestDepartmentById(@Param("id") Integer employeeId);
}
