package com.myHighSpeedRail.johnny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.johnny.model.ShoppingOrder;

public interface ShoppingOrderRepository extends JpaRepository<ShoppingOrder, Integer> { 
}
