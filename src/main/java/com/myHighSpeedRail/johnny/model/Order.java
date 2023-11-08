package com.myHighSpeedRail.johnny.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
	
	@Id
	@Column(name = "order_id")
	private Integer orderId;
	

}

/*
CREATE TABLE [order](
order_id int not null primary key identity(1,1),
order_creation_date datetime not null,
order_completion_date datetime,
order_status nvarchar(10) not null,
total_price int not null,
member_id_fk uniqueidentifier not null foreign key references eeit_member(member_id),
station_id_fk int not null foreign key references station(station_id)
);
*/