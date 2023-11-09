package com.myHighSpeedRail.peter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	

	public Title() {
	}

}
