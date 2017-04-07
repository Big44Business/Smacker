package com.smacker.dao;

import com.smacker.bean.ShopCar;

public interface ShopCarDao {

	public boolean saveShopCar(ShopCar sc);
	/**
	 * 删除用户的购物车中的某几个订单
	 * @param userId
	 * @param commodityIds
	 * @return
	 */
	public boolean deleteCommodityInShopCar(String userId,  String[] commodityIds);
	
	/**
	 * 通过用户Id获取该用户的购物车里面的所有产品
	 * @param usreId
	 * @return
	 */
	public ShopCar getShopCar(String userId);
	/**
	 * 将存入购物车的用户Id和商品id对应的进行存储
	 * @param userId
	 * @param commodityId
	 * @return
	 */
	public boolean saveUserIdCommodityId(String userId, String commodityId);
}
