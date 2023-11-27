package com.myHighSpeedRail.derekwu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myHighSpeedRail.derekwu.model.LostProperty;

public interface LostPropertyRepository extends JpaRepository<LostProperty, Long> {

	// 遺失物依照詳細外觀模糊查詢(員工)
	@Query(value = "select * from lost_property where detail_outward like %:detail_outward%", nativeQuery = true)
	public LostProperty searchLosttProperty(@Param("detail_outward") String detailOutward);

	// 遺失物依照名稱模糊查詢(訪客)
	@Query(value = "select station_name,find_date,stay_station,simple_outward from lost_property where simple_outward like %:simple_outward%", nativeQuery = true)
	public LostProperty searchSimpleLosttProperty(@Param("simple_outward") String simpleOutward);

}
