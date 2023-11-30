package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeEducationalQualifications;

@Repository
public interface EmployeeEducationalQualificationsRepository
		extends JpaRepository<EmployeeEducationalQualifications, Integer> {

}
