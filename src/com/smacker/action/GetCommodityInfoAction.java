package com.smacker.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.smacker.bean.Commodity;
import com.smacker.dao.CommodityDao;

@SuppressWarnings("serial")
@Component("getCommodityInfo")
@Scope("prototype")
public class GetCommodityInfoAction extends ActionSupport {

	private CommodityDao cd;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CommodityDao getCd() {
		return cd;
	}
	@Resource(name="commodityDao")
	public void setCd(CommodityDao cd) {
		this.cd = cd;
	}
	
	/**
	 * 通过商品Id获取商品信息
	 */
	public void getCommodityInfoById() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		Gson gson = new Gson();
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		Commodity c = cd.getCommodityInId(id);
		if(c != null) {
			/**
			 * 将不需要的字段 隐藏
			 */
			c.getCommodityOwner().setAllCommoditys(null);
			c.getCommodityOwner().setCreatorOrders(null);
			c.getCommodityOwner().setSellerOrders(null);
			c.getCommodityOwner().setShopCar(null);
			
			success = true;
			jo.add("commodity", gson.toJsonTree(c));
		} else
			reason = "该商品不存在";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		out.flush();
		out.close();
	}
}
