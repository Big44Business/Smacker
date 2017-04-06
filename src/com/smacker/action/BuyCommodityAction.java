package com.smacker.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.smacker.bean.Commodity;
import com.smacker.bean.Order;
import com.smacker.bean.User;
import com.smacker.dao.CommodityDao;
import com.smacker.dao.OrderDao;

@SuppressWarnings("serial")
@Component("buyCommodity")
@Scope("prototype")
public class BuyCommodityAction extends ActionSupport {

	private String[] commodityId = null;
	private String[] unitPrice = null;
	private int[] commodityCount  = null;
	private String[] addr = null;
	
	public String[] getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String[] commodityId) {
		this.commodityId = commodityId;
	}
	public String[] getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String[] unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int[] getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(int[] commodityCount) {
		this.commodityCount = commodityCount;
	}
	public String[] getAddr() {
		return addr;
	}
	public void setAddr(String[] addr) {
		this.addr = addr;
	}
	
	private CommodityDao cd;
	private OrderDao od;
	public CommodityDao getCd() {
		return cd;
	}
	@Resource(name="commodityDao")
	public void setCd(CommodityDao cd) {
		this.cd = cd;
	}
	public OrderDao getOd() {
		return od;
	}
	@Resource(name="orderDao")
	public void setOd(OrderDao od) {
		this.od = od;
	}
	/**
	 * 直接购买
	 */
	public void buy() {
		
		/**
		 * 
		 * private Commodity commodityId = null;	//商品
		private String unitPrice = "0.00";				//单价
		private int commodityCount = 0;			//个数
		private User userId = null;				//买方
		private User sellerId = null;			//卖方
		private String addr = null;				//地址
		private String status = "0";	
		 */
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null) {
			
			if(commodityId.length > 0) {
				
				for(int i = 0; i < commodityId.length; i++) {
					Order o = new Order();
					Commodity c = cd.getCommodityInId(commodityId[i]);
					o.setCommodityId(commodityId[i]);
					o.setSellerId(c.getCommodityOwner());
					o.setAddr(addr[i]);
					o.setUnitPrice(unitPrice[i]);
					o.setCommodityCount(commodityCount[i]);
					o.setUserId(user.getUserId());
					o.setStatus("1");
					o.setOrderDate(new Timestamp(System.currentTimeMillis()));
					
					if(!od.saveOrder(o)) {
						success = false;
						reason = "订单创建失败";
						break;
					} else
						success = true;
				}
			} else
				reason = "请选择商品";
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		out.print(jo.toString());
		out.flush();
		out.close();
	}
	/**
	 * 加入购物车
	 */
	public void addToCar() {}
}
