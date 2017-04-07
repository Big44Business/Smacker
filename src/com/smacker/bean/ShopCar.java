package com.smacker.bean;

import java.util.Vector;

/**
 * 购物车
 * 装userId和其对应的所有产品Id
 */
public class ShopCar {
	
	private User userId = null;
	private Vector<String> productIds = null;
	
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Vector<String> getProductIds() {
		return productIds;
	}
	public void setProductIds(Vector<String> productIds) {
		this.productIds = productIds;
	}
}