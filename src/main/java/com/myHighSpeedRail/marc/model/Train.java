package com.myHighSpeedRail.marc.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="train")
public class Train {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="train_id",nullable=false)
	public Integer trainId;
	@Column(name="train_type",nullable=false)
	public String trainType;
	@Column(name="train_description",nullable=false)
	public String trainDescription;
	public Integer getTrainId() {
		return trainId;
	}
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public String getTrainType() {
		return trainType;
	}
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	public String getTrainDescription() {
		return trainDescription;
	}
	public void setTrainDescription(String trainDescription) {
		this.trainDescription = trainDescription;
	}
	public Train(String trainType, String trainDescription) {
		this.trainType = trainType;
		this.trainDescription = trainDescription;
	}
	public Train() {;}
	@Override
	public int hashCode() {
		return Objects.hash(trainId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return Objects.equals(trainId, other.trainId);
	}
	
}
