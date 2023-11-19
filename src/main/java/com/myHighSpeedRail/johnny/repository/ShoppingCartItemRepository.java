package com.myHighSpeedRail.johnny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.johnny.model.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {

}
