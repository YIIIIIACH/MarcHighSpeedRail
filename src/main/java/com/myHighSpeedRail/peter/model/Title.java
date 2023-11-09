package com.myHighSpeedRail.peter.model;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="title")
public class Title {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="title_id")
	private Integer titleId;
	
	@Column(name="title_name")	
	private String titleName;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "title",cascade = CascadeType.ALL)
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

	public Title() {
	}

}
