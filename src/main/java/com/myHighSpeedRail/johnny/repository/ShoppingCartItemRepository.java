package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myHighSpeedRail.johnny.model.ShoppingCartItem;

import jakarta.transaction.Transactional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Integer> {
	
	@Query("From ShoppingCartItem WHERE memberId = :memberId")
	public List<ShoppingCartItem> findAllShoppingCartItemByMemberId(String memberId);
	
	@Modifying
	@Query(value="DELETE FROM shopping_cart_item WHERE member_uuid = :memberId AND shopping_cart_item_id = :itemId", nativeQuery = true)
	@Transactional
	public void deleteShoppingCartItem(@Param("memberId") String memberId, @Param("itemId") Integer itemId);
	
	@Modifying
	@Query(value="DELETE FROM shopping_cart_item WHERE member_uuid = :memberId", nativeQuery = true)
	@Transactional
	public void deleteAllShoppingCartItemByMemberId(String memberId);
	
	@Modifying
	@Query(value="UPDATE shopping_cart_item SET quantity = :quantity WHERE member_uuid = :memberId AND shopping_cart_item_id = :itemId", nativeQuery = true)
	@Transactional
	public void updateCartItemQuantity(String memberId, Integer itemId, Integer quantity);
}
