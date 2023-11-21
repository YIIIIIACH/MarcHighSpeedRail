package com.myHighSpeedRail.peter.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myHighSpeedRail.peter.model.EmergencyContactPerson;

@Repository
public interface EmergencyContactPersonRepository extends JpaRepository<EmergencyContactPerson, Integer> {

}
