package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmployeeTitle;

@Repository
public interface EmployeeTitleRepository extends JpaRepository<EmployeeTitle, Integer> {

}
