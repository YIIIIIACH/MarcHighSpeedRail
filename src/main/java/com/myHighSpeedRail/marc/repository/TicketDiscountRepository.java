package com.myHighSpeedRail.marc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.marc.model.TicketDiscount;
@Repository
public interface TicketDiscountRepository extends JpaRepository<TicketDiscount, Integer>{
	// get all discount type
	@Query(value="select t.ticketDiscountType from TicketDiscount as t group by t.ticketDiscountType")
	public List<String> getAllDiscountType();
	
	@Query( value="from TicketDiscount as t where t.ticketDiscountType=:disType")
	public List<TicketDiscount> findByDiscountType(String disType);
}
