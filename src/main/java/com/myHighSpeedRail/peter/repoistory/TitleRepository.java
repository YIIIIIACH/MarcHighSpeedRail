package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.Department;
import com.myHighSpeedRail.peter.model.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

	@Query("from Title as t where t.titleName = :name ")
	Title findByName(@Param("name") String titleName);
}
