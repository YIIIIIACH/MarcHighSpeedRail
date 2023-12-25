package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myHighSpeedRail.johnny.model.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> {
	
	@Query("from ShoppingOrder where member = :memberId")
	public List<ShoppingOrder> findByMemberId(@Param("memberId") String memberId);
	
	@Query("from ShoppingOrder where member = :mId and orderNumber = :oNum")
	public ShoppingOrder findByMemberIdAndOrderNumber(@Param("mId") String memberId, @Param("oNum") String orderNumber);
}
