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

//多方
@Entity
@Table(name="product_buying_method")
public class ProductBuyingMethod {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_buying_method_id", nullable=false)
	private Integer ProductBuyingMethodId;
	
	@Column(name="buying_method",nullable=false)
	private String buyingMethod;
	
	//宣告1方
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id_fk",nullable=false)
	private Product product; 

	public ProductBuyingMethod() {
	}

	public ProductBuyingMethod(Integer productBuyingMethodId, String buyingMethod, Product product) {
		this.ProductBuyingMethodId = productBuyingMethodId;
		this.buyingMethod = buyingMethod;
		this.product = product;
	}

	public Integer getProductBuyingMethodId() {
		return ProductBuyingMethodId;
	}

	public void setProductBuyingMethodId(Integer productBuyingMethodId) {
		ProductBuyingMethodId = productBuyingMethodId;
	}

	public String getBuyingMethod() {
		return buyingMethod;
	}

	public void setBuyingMethod(String buyingMethod) {
		this.buyingMethod = buyingMethod;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

/*
CREATE TABLE product_buying_method(
product_buying_method_id int not null primary key identity(1,1),
buying_method nvarchar(10) not null,
product_id_fk int not null foreign key references product(product_id)
);
*/