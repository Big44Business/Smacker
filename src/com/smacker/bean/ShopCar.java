package com.smacker.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_shopCar")
public class ShopCar {
	
	private String shopCarId = null;
	private User userId = null;
	private Commodity commodityId = null;
	
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getShopCarId() {
		return shopCarId;
	}
	public void setShopCarId(String shopCarId) {
		this.shopCarId = shopCarId;
	}

	/**
	 * 获取购物车所属用户，将获取设置为懒加载
	 * @return
	 */
	@OneToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取购物车内的商品，将获取设置为懒加载
	 * @return
	 */
	@OneToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="commodityId")
	public Commodity getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Commodity commodityId) {
		this.commodityId = commodityId;
	}
}
