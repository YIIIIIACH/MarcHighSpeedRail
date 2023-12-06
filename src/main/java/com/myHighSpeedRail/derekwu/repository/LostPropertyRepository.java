package com.myHighSpeedRail.derekwu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myHighSpeedRail.derekwu.model.LostProperty;

@Repository
public interface LostPropertyRepository extends JpaRepository<LostProperty, Long> {

	@Query(value = ":detail_outward", nativeQuery = true)
	public List<LostProperty> ssearchLosttProperty(@Param("detail_outward") String detailOutward);

	
	// 遺失物依照詳細外觀模糊查詢(員工)
	@Query(value = "select * from lost_property where detail_outward like %:detail_outward%", nativeQuery = true)
	public List<LostProperty> searchLosttProperty(@Param("detail_outward") String detailOutward);

	// 遺失物依照名稱模糊查詢(訪客)
	@Query(value = "select * from lost_property where simple_outward like %:simple_outward%", nativeQuery = true)
	public List<LostProperty> searchSimpleLosttProperty(@Param("simple_outward") String simpleOutward);
	
	// 照LostPropertyId刪除遺失物
	@Transactional
	@Modifying
	public Integer deleteByLostPropertyId(Integer LostPropertyId);
	
	// 修改(update)遺失物資料或狀態
	@Query(value = "select * from lost_property where lost_property_id = :lost_property_id", nativeQuery = true)
	public LostProperty findByLostPropertyId(@Param("lost_property_id") Integer LostPropertyId);
	


}
