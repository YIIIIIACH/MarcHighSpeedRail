package com.myHighSpeedRail.johnny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.johnny.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
