package com.marc.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketDiscountRepository extends JpaRepository<TicketDiscount, Integer>{
	// get all discount type
	@Query(value="select t.ticketDiscountType from TicketDiscount as t group by t.ticketDiscountType")
	public List<String> getAllDiscountType();
}
