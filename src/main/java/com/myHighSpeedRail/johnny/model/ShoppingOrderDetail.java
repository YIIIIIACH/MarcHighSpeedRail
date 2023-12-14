package com.myHighSpeedRail.johnny.model;

import jakarta.persistence.CascadeType;
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
@Table(name = "shopping_order_detail")
public class ShoppingOrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id", nullable = false)
	private Integer orderDetailId;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "checkout_price", nullable = false)
	private Integer checkoutPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id_fk", nullable = false)
	private ShoppingOrder shoppingOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id_fk", nullable = false)
	private Product product;

	public ShoppingOrderDetail() {
		super();
	}

	public ShoppingOrderDetail(Integer orderDetailId, Integer quantity, Integer checkoutPrice, ShoppingOrder shoppingOrder, Product product) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.checkoutPrice = checkoutPrice;
		this.shoppingOrder = shoppingOrder;
		this.product = product;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCheckoutPrice() {
		return checkoutPrice;
	}

	public void setCheckoutPrice(Integer checkoutPrice) {
		this.checkoutPrice = checkoutPrice;
	}

	public ShoppingOrder getShoppingOrder() {
		return shoppingOrder;
	}

	public void setShoppingOrder(ShoppingOrder shoppingOrder) {
		this.shoppingOrder = shoppingOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	
}
