package com.myHighSpeedRail.marc.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="seat")
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seat_id",nullable=false)
	private Integer seatId;
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="train_id_fk", nullable=false)
	private Train train;
	
	@Column(name="carriage", nullable=false)
	private Integer carriage;
	
	@Column(name="train_seat_sequence_id", nullable=false)
	private Integer trainSeatSequenece;
	
	@Column(name="seat_code",nullable=false)
	private String seatCode;
	
	@Column(name="seat_description", nullable=false)
	private String seatDescirption;
	public Seat() {;}
	public Seat(Train train, Integer carriage, Integer trainSeatSequenece, String seatCode, String seatDescirption) {
		super();
		this.train = train;
		this.carriage = carriage;
		this.trainSeatSequenece = trainSeatSequenece;
		this.seatCode = seatCode;
		this.seatDescirption = seatDescirption;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Integer getCarriage() {
		return carriage;
	}

	public void setCarriage(Integer carriage) {
		this.carriage = carriage;
	}

	public Integer getTrainSeatSequenece() {
		return trainSeatSequenece;
	}

	public void setTrainSeatSequenece(Integer trainSeatSequenece) {
		this.trainSeatSequenece = trainSeatSequenece;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}

	public String getSeatDescirption() {
		return seatDescirption;
	}

	public void setSeatDescirption(String seatDescirption) {
		this.seatDescirption = seatDescirption;
	}
	@Override
	public int hashCode() {
		return Objects.hash(seatId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(seatId, other.seatId);
	}
}
