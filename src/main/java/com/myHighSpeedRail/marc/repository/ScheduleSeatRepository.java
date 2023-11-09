package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;

public interface ScheduleSeatRepository extends JpaRepository<ScheduleSeatStatus, Integer>{

}
