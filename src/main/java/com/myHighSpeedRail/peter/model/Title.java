package com.myHighSpeedRail.peter.model;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "title")
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "title_id")
	private Integer titleId;

	@Column(name = "title_name")
	private String titleName;

	@JsonIgnore
//	@JsonBackReference(value = "titEt")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "title", cascade = CascadeType.ALL)
	private List<EmployeeTitle> employeeTitle = new LinkedList<EmployeeTitle>();

	public Title() {
	}
	
	public Title(Integer titleId) {
		super();
		this.titleId = titleId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public List<EmployeeTitle> getEmployeeTitle() {
		return employeeTitle;
	}

	public void setEmployeeTitle(List<EmployeeTitle> employeeTitle) {
		this.employeeTitle = employeeTitle;
	}

	@Override
	public String toString() {
		return "Title [titleId=" + titleId + ", titleName=" + titleName + ", employeeTitle=" + employeeTitle + "]";
	}

}
