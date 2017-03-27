package com.smacker.dao;

import com.smacker.bean.Commodity;

public interface CommodityDao {
	public boolean saveCommodity(Commodity cmdty);
	public boolean updateCommodity(Commodity cmdty);
	public boolean deleteCommodity(String cmdtyId);
	public Commodity getCommodityInId(String cmdtyId);
	/**
	 * 将整个Commodity传入，提高灵活性
	 * @param cmdty
	 * @return
	 */
	public Commodity getCommodity(Commodity cmdty);
}
