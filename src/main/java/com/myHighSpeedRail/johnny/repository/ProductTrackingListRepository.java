package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.johnny.model.ProductTrackingList;

import jakarta.transaction.Transactional;

public interface ProductTrackingListRepository extends JpaRepository<ProductTrackingList, Integer> {
	
	@Query("from ProductTrackingList where memberId = :mId")
	public List<ProductTrackingList> findByMemberId(String mId);
	
	@Modifying
	@Query(value = "DELETE FROM product_tracking_list WHERE member_uuid = :mId AND product_tracking_list_id = :tId", nativeQuery = true)
	@Transactional
	public void deleteByMemberIdAndTrackingId(String mId, Integer tId);
}
