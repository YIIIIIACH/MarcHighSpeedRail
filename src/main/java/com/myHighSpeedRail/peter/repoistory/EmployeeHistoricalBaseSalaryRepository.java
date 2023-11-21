package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeEducationalQualification;

@Repository
public interface EmployeeHistoricalBaseSalaryRepository
		extends JpaRepository<EmployeeEducationalQualification, Integer> {

}
