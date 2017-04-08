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
import com.smacker.bean.ShopCar;
import com.smacker.bean.User;
import com.smacker.dao.ShopCarDao;
import com.smacker.dao.UserDao;

@SuppressWarnings("serial")
@Component("getShopCarInfo")
@Scope("prototype")
public class GetShopCarInfoAction extends ActionSupport {

	private String userId;
	private ShopCarDao scd;
	private UserDao ud;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ShopCarDao getScd() {
		return scd;
	}
	@Resource(name="shopCarDao")
	public void setScd(ShopCarDao scd) {
		this.scd = scd;
	}
	public UserDao getUd() {
		return ud;
	}
	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	
	public void get() {
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
		User u = ud.getUserInId(userId);
		if(u != null) {
			ShopCar sc = scd.getShopCar(userId);
			if(sc != null) {
				success = true;
				jo.add("shopCar", gson.toJsonTree(sc));
			} else
				reason = "获取失败";
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		out.print(jo.toString());
		out.flush();
		out.close();
	}
	
}
