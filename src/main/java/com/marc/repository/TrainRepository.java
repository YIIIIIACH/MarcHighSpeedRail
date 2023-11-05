package com.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marc.model.Train;

public interface TrainRepository extends JpaRepository< Train, Integer>{

}
