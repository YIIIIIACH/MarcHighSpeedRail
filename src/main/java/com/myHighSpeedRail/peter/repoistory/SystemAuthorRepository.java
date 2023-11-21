package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.SystemAuthor;

@Repository
public interface SystemAuthorRepository extends JpaRepository<SystemAuthor, Integer> {

}
