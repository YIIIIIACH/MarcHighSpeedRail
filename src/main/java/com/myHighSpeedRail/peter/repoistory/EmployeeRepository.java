package com.myHighSpeedRail.peter.repoistory;

import java.math.BigDecimal;
import java.util.Date;

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
		
//	@Query("update Employee e set e.employeeName=:employeeName, e.employeeGender=:employeeGender, e.employeeBirth=:employeeBirth, e.employeeIdNumber=:employeeIdNumber, e.employeePhoneNumber=:employeePhoneNumber, e.employeeContactNumber=:employeeContactNumber, e.employeeEmail=:employeeEmail, e.employeeResidenceAddress=:employeeResidenceAddress, e.employeeContactAddress=:employeeContactAddress, e.employeePhoto=:employeePhoto, e.employeeArrivalDate=:employeeArrivalDate, e.employeeSalaryKind=:employeeSalaryKind, e.employeeBasicSalary=:employeeBasicSalary where e.employeeId=:employeeId")
//	void updateEmployeeDetailDTO(
//			@Param("employeeName") String employeeName,
//            @Param("employeeGender") String employeeGender,
//            @Param("employeeBirth") Date employeeBirth,
//            @Param("employeeIdNumber") String employeeIdNumber,
//            @Param("employeePhoneNumber") String employeePhoneNumber,
//            @Param("employeeContactNumber") String employeeContactNumber,
//            @Param("employeeEmail") String employeeEmail,
//            @Param("employeeResidenceAddress") String employeeResidenceAddress,
//            @Param("employeeContactAddress") String employeeContactAddress,
//            @Param("employeePhoto") String employeePhoto,
//            @Param("employeeArrivalDate") Date employeeArrivalDate,
//            @Param("employeeSalaryKind") String employeeSalaryKind,
//            @Param("employeeBasicSalary") BigDecimal employeeBasicSalary,
//            @Param("employeeId") Integer employeeId
//			);
}
 