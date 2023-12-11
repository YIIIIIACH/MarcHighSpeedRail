package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.Train;
@Repository
public interface TrainRepository extends JpaRepository< Train, Integer>{

}
