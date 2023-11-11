package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.ScheduleSeatCmpsPK;
import com.myHighSpeedRail.marc.model.ScheduleSeatStatus;

public interface ScheduleSeatStatusRepository extends JpaRepository<ScheduleSeatStatus, ScheduleSeatCmpsPK>{

}
