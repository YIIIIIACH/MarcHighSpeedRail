package com.myHighSpeedRail.peter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.repoistory.EmployeeLeaveRepository;

@Service
public class EmployeeLeaveService {

	@Autowired
	private EmployeeLeaveRepository elDao;
}
