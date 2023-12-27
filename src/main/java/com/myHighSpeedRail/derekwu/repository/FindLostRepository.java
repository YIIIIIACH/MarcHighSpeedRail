package com.myHighSpeedRail.derekwu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.derekwu.model.FindLost;

@Repository
public interface FindLostRepository extends JpaRepository<FindLost, Integer>{

}
