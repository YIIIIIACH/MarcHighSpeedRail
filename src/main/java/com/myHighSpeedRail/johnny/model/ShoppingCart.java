package com.myHighSpeedRail.johnny.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
	
	@Id
	@Column(name = "shopping_cart_id")
	private Integer shoppingCartId;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	
	
//	QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ
	
//  @JoinColumn(name = "member_id_fk", nullable = false);
//	private Member member;
}

/*CREATE TABLE shopping_cart(
shopping_cart_id int not null primary key identity(1,1),
quantity int not null,
product_id_fk int not null foreign key references product(product_id),
member_id_fk uniqueidentifier not null foreign key references eeit_member(member_id)
);*/