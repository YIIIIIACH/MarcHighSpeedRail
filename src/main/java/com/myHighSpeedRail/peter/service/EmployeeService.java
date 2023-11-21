package com.myHighSpeedRail.peter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.peter.model.Employee;
import com.myHighSpeedRail.peter.model.SystemAuthor;
import com.myHighSpeedRail.peter.repoistory.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository eDao;

	@Autowired
	private PasswordEncoder pwdEncoder;

	public void employeeAddSystem(Employee e) {
		eDao.save(e);
	}

	public Employee checklogin(String empAccount, String pwd) {
		Employee emp = eDao.findByEmployeeAccount(empAccount);

		if (emp != null) {
			if (pwdEncoder.matches(pwd, emp.getEmployeePassword())) {
				return emp;
			}
		}

		return null;

	}

	public void employeeAdd(Employee e) {
		eDao.save(e);
	}

	public SystemAuthor getEmployeeSystemAuthor(Integer id) {
		Employee emp = eDao.findByEmployeeIdJoinSystemAuthor(id);
		List<SystemAuthor> authorList = emp.getSystemAuthor();
		if (authorList.isEmpty()) {//如果沒有找到匹配的權限
			return null;
		}
		return authorList.get(0);
	}

	public Employee findEmployeeById(Integer id) {
		Optional<Employee> emp = eDao.findById(id);
		if (emp.isPresent()) {
			return emp.get();
		}
		return null;
	}
}
