package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeHistoricalDepartment;

@Repository
public interface EmployeeHistoricalDepartmentRepository extends JpaRepository<EmployeeHistoricalDepartment, Integer> {

}
