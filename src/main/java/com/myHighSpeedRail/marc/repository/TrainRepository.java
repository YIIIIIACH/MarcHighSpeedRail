package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.Train;

public interface TrainRepository extends JpaRepository< Train, Integer>{

}
