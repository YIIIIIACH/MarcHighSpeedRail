package com.myHighSpeedRail.marc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.TicketOrder;
@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Integer>{
	@Query("from TicketOrder where memberToken=:memuuid")
	public List<TicketOrder> findByMember(String memuuid);
}
