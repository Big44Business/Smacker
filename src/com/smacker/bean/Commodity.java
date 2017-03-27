package com.smacker.bean;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_commodity")
public class Commodity {
	
@SuppressWarnings("unused")
private final static String COMMODITY_DEFAULT = "";
	
	private String commodityId = null;
	private String commodityName = null;//物品名称
	private String commodityCategary = null;//物品类别
	private String isOrder = null;//是否被定(0:正在出售,1:已被订购,2:已出售)
	private String commodityPicture = null;//物品图片
	private String commodityDescribe = null;//物品描述
	private Integer commodityCount = -1;//物品数量
	private Integer commodityOldNewLevel = -1;//新旧程度
	private String commodityNewPrice = null;//原价
	private String commodityOldPrice = null;//二手价
	private User commodityOwner = null;//拥有者
	private Integer commodityDownDay = -1;//下架天数
	private Timestamp commodifyCreateDate = null;//创建时间
	private Integer wantShopCount = 0;//欲购物人数
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(String isOrder) {
		this.isOrder = isOrder;
	}
	public String getCommodityCategary() {
		return commodityCategary;
	}
	public void setCommodityCategary(String commodityCategary) {
		this.commodityCategary = commodityCategary;
	}
	public String getCommodityPicture() {
		return commodityPicture;
	}
	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}
	public String getCommodityDescribe() {
		return commodityDescribe;
	}
	public void setCommodityDescribe(String commodityDescribe) {
		this.commodityDescribe = commodityDescribe;
	}
	public Integer getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(Integer commodityCount) {
		this.commodityCount = commodityCount;
	}
	public Integer getCommodityOldNewLevel() {
		return commodityOldNewLevel;
	}
	public void setCommodityOldNewLevel(Integer commodityOldNewLevel) {
		this.commodityOldNewLevel = commodityOldNewLevel;
	}
	public String getCommodityNewPrice() {
		return commodityNewPrice;
	}
	public void setCommodityNewPrice(String commodityNewPrice) {
		this.commodityNewPrice = commodityNewPrice;
	}
	public String getCommodityOldPrice() {
		return commodityOldPrice;
	}
	public void setCommodityOldPrice(String commodityOldPrice) {
		this.commodityOldPrice = commodityOldPrice;
	}
	
	/**
	 * 多对一，获取该物品的拥有者
	 * @return
	 */
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="commodityOwnerId")
	public User getCommodityOwner() {
		return commodityOwner;
	}
	public void setCommodityOwner(User commodityOwner) {
		this.commodityOwner = commodityOwner;
	}
	public Integer getCommodityDownDay() {
		return commodityDownDay;
	}
	public void setCommodityDownDay(Integer commodityDownDay) {
		this.commodityDownDay = commodityDownDay;
	}
	public Timestamp getCommodifyCreateDate() {
		return commodifyCreateDate;
	}
	public void setCommodifyCreateDate(Timestamp commodifyCreateDate) {
		this.commodifyCreateDate = commodifyCreateDate;
	}
	public Integer getWantShopCount() {
		return wantShopCount;
	}
	public void setWantShopCount(Integer wantShopCount) {
		this.wantShopCount = wantShopCount;
	}
}
