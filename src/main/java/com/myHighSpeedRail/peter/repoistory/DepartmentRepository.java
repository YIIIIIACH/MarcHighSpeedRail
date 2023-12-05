package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("from Department as dept left join fetch dept.systemAuthor where dept.departmentId = :id ")
	Department findByDepartmentIdJoinSystemAuthor(@Param("id") Integer departmentId);
	
	@Query("from Department as dept where dept.departmentName = :name ")
	Department findBydepartmentName(@Param("name") String departmentName);
}
