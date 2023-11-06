package com.peter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_data")
public class Employee {

	private Integer employee_id;
}
