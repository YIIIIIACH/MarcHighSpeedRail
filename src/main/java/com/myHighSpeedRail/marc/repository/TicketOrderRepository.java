package com.myHighSpeedRail.marc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.marc.model.TicketOrder;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Integer>{

}
