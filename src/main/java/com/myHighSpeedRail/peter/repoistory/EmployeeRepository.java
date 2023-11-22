package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("from Employee where employeeAccount = :acc")
	Employee findByEmployeeAccount(@Param("acc") String employeeAccount);
	
	@Query("from Employee as emp left join fetch emp.systemAuthor where emp.employeeId = :id ")
	Employee findByEmployeeIdJoinSystemAuthor(@Param("id") Integer employeeId);
	
}
 