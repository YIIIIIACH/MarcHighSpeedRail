package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeWorkOvertime;

@Repository
public interface EmployeeWorkOvertimeRepository extends JpaRepository<EmployeeWorkOvertime, Integer> {

}
