package com.myHighSpeedRail.johnny.model;

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
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_cart_item_id")
	private Integer shoppingCartItemId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id_fk", nullable = false)
	private Product product;
	
    @Column(name = "member_uuid", nullable = false)
	private String member;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(Integer shoppingCartItemId, Integer quantity, Product product, String member) {
		super();
		this.shoppingCartItemId = shoppingCartItemId;
		this.quantity = quantity;
		this.product = product;
		this.member = member;
	}
	public ShoppingCartItem(Integer quantity, Product product, String member) {
		this.quantity = quantity;
		this.product = product;
		this.member = member;
	}

	public Integer getShoppingCartItemId() {
		return shoppingCartItemId;
	}

	public void setShoppingCartItemId(Integer shoppingCartItemId) {
		this.shoppingCartItemId = shoppingCartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}  
    
}

