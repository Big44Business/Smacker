package com.smacker.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_user")
public class User {
	
	@SuppressWarnings("unused")
	private final static String PHOTO_DEFAULT = "";

	private String userId = null;
	private String userNickName = null;//用户昵称
	private String userEmail = null;//用户邮箱
	private String userRealName = null;//用户真实姓名
	private String userPassword = "123456";
	private String userIntroduce = null;//个人简介
	private String userGender = null;
	private Timestamp userBirthday = null;
	private String userPhoto = "";//用户头像
	private String userRealPhoto = "";//用户照片
	private String userAddress = null;//所属高校
	private String userIp = null;
	private String userWeChat = null;
	private String userIdCard = null;
	private Timestamp userCreateDate = null;
	private String isVerify = "0";//是否认证 (0:尚未认证；1:认证成功；2:认证失败；3:正在认证)
	private String userTel = null;//电话
	private String school = null;//学校
	/**
	 * 获取该用户所创建的所有的物品
	 */
	private Set<Commodity> allCommoditys = new HashSet<>();
	/**
	 * 获取该用户所创建的所有的订单
	 */
	private Set<Order> creatorOrders = new HashSet<>();
	/**
	 * 获取该用户所接收的所有的订单
	 */
	private Set<Order> sellerOrders = new HashSet<>();
	
	/**
	 * 获取该用户的购物车
	 */
	private ShopCar shopCar = null;
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserIntroduce() {
		return userIntroduce;
	}
	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public Timestamp getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Timestamp userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserRealPhoto() {
		return userRealPhoto;
	}
	public void setUserRealPhoto(String userRealPhoto) {
		this.userRealPhoto = userRealPhoto;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getUserWeChat() {
		return userWeChat;
	}
	public void setUserWeChat(String userWeChat) {
		this.userWeChat = userWeChat;
	}
	public String getUserIdCard() {
		return userIdCard;
	}
	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}
	public Timestamp getUserCreateDate() {
		return userCreateDate;
	}
	public void setUserCreateDate(Timestamp userCreateDate) {
		this.userCreateDate = userCreateDate;
	}
	public String getIsVerify() {
		return isVerify;
	}
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	/**
	 * 一对多，获取该用户所发布的所用物品
	 * @return
	 */
	@OneToMany(mappedBy="commodityOwner",cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	public Set<Commodity> getAllCommoditys() {
		return allCommoditys;
	}
	public void setAllCommoditys(Set<Commodity> allCommoditys) {
		this.allCommoditys = allCommoditys;
	}
	/**
	 * 一对多，获取该用户所创建的所有订单
	 * @return
	 */
	@OneToMany(mappedBy="userId",cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	public Set<Order> getCreatorOrders() {
		return creatorOrders;
	}
	public void setCreatorOrders(Set<Order> creatorOrders) {
		this.creatorOrders = creatorOrders;
	}
	
	/**
	 * 一对多，获取该用户是卖家身份所接收的所有订单
	 * @return
	 */
	@OneToMany(mappedBy="sellerId",cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	public Set<Order> getSellerOrders() {
		return sellerOrders;
	}
	public void setSellerOrders(Set<Order> sellerOrders) {
		this.sellerOrders = sellerOrders;
	}
	
	@OneToOne(mappedBy="userId",cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	public ShopCar getShopCar() {
		return shopCar;
	}
	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}
	
}
