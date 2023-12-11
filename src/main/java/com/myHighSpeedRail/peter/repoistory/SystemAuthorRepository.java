package com.myHighSpeedRail.peter.repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.SystemAuthor;

import jakarta.transaction.Transactional;

@Repository
public interface SystemAuthorRepository extends JpaRepository<SystemAuthor, Integer> {

	@Query("from SystemAuthor as sa where sa.employee.employeeId = :id")
	SystemAuthor findByEmployeeId(@Param("id")Integer id);
	
	@Modifying
	@Transactional
	@Query("update SystemAuthor as sa set sa.authorJson = :json where sa.employee.employeeId = :id")
	void updateByEmployeeId(@Param("id")Integer id,@Param("json") String json);
	
	@Query("from SystemAuthor as sa where sa.department.departmentId = :id")
	SystemAuthor findByDepartmentId(@Param("id")Integer id);
	
	@Modifying
	@Transactional
	@Query("update SystemAuthor as sa set sa.authorJson = :json where sa.department.departmentId = :id")
	void updateByDepartmentId(@Param("id")Integer id,@Param("json") String json);
	
}
