package com.myHighSpeedRail.derekwu.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "find_lost")
public class FindLost {
	//遺失物領取紀錄
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="find_lost_id",nullable = false)
	private Integer findLostId;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lost_property_id_fk",nullable = false)
	private LostProperty lostPropertyId;
	
	@JsonFormat(pattern="yyyy/MM/dd HH:mm:ss" ,timezone= "GMT+8")//傳JSON字串時會用到的固定時間格式
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss") // 轉換前端 String 日期到 Java 端日期格式
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="find_lost_date",nullable=false)
	private Date findLostDate;
	
	public FindLost(Integer findLostId, LostProperty lostPropertyId, Date findLostDate) {
		this.findLostId = findLostId;
		this.lostPropertyId = lostPropertyId;
		this.findLostDate = findLostDate;
	}
	public FindLost() {
	}
	public Integer getFindLostId() {
		return findLostId;
	}
	public void setFindLostId(Integer findLostId) {
		this.findLostId = findLostId;
	}
	public LostProperty getLostPropertyId() {
		return lostPropertyId;
	}
	public void setLostPropertyId(LostProperty lostPropertyId) {
		this.lostPropertyId = lostPropertyId;
	}
	public Date getFindLostDate() {
		return findLostDate;
	}
	public void setFindLostDate(Date findLostDate) {
		this.findLostDate = findLostDate;
	}
	
	
	//Pre在物件轉換到Persist之前做甚麼事，例如下面在new之前
		@PrePersist
		public void onCreate() {
			if(findLostDate==null) {
				findLostDate = new Date();
			}
		}
	

}

