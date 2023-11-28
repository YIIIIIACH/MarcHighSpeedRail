package com.myHighSpeedRail.derekwu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notify")
public class Notify {
	//通知
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "notify_id")
	private Integer notifyId;
		
	@Column(name = "memeber_uuid")
	private String memberUuid;
	//會員UUID
	
	@Column(name = "shop_order_id")
	private Integer shopOrderId;
	//購物車訂單 order
	
	@Column(name = "ticked_order_id")
	private Integer tickedOrderId;
	//訂票訂單 ticked_order
	//會有「訂票成功」和「付款成功」的事件
	
	@Column(name = "find_lost_id")
	private Integer findLostId;
	//遺失物
	public Notify(Integer notifyId, String memberUuid, Integer shopOrderId, Integer tickedOrderId, Integer findLostId) {
		super();
		this.notifyId = notifyId;
		this.memberUuid = memberUuid;
		this.shopOrderId = shopOrderId;
		this.tickedOrderId = tickedOrderId;
		this.findLostId = findLostId;
	}
	public Notify() {
		super();
	}
	public Integer getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}
	public String getMemberUuid() {
		return memberUuid;
	}
	public void setMemberUuid(String memberUuid) {
		this.memberUuid = memberUuid;
	}
	public Integer getShopOrderId() {
		return shopOrderId;
	}
	public void setShopOrderId(Integer shopOrderId) {
		this.shopOrderId = shopOrderId;
	}
	public Integer getTickedOrderId() {
		return tickedOrderId;
	}
	public void setTickedOrderId(Integer tickedOrderId) {
		this.tickedOrderId = tickedOrderId;
	}
	public Integer getFindLostId() {
		return findLostId;
	}
	public void setFindLostId(Integer findLostId) {
		this.findLostId = findLostId;
	}
	
	
	
	
	

}
