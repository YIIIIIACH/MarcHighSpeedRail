package com.myHighSpeedRail.peter.repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.Systems;

@Repository
public interface SystemsRepository extends JpaRepository<Systems, Integer> {

	@Query("from Systems where systemName = :name")
	Optional<Systems> findBysystemName(@Param("name")String name);
	
	long count();
}
