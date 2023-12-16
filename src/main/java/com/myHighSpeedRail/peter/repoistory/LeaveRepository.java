package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	@Query("from Leave as l where l.leaveName = :name")
	Leave findByLeaveName(@Param("name") String name);
}
