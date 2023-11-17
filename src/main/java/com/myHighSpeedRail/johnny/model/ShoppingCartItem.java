package com.myHighSpeedRail.johnny.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCartItem {
	
	@Id
	@Column(name = "shopping_cart_item_id")
	private Integer shoppingCartItemId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@OneToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL )
	private List<Product> product;
	
    @Column(name = "member_uuid", nullable = false)
	private String member;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(Integer shoppingCartItemId, Integer quantity, List<Product> product, String member) {
		super();
		this.shoppingCartItemId = shoppingCartItemId;
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

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
	
	
    
    
}

