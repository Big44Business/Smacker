package com.smacker.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.smacker.bean.Order;
import com.smacker.bean.OrderStatus;
import com.smacker.bean.User;
import com.smacker.bean.UserType;
import com.smacker.dao.OrderDao;
import com.smacker.dao.UserDao;

@SuppressWarnings("serial")
@Component("getOrdersByUserId")
@Scope("prototype")
public class GetOrdersByUserIdAction extends ActionSupport {

	private OrderDao od;
	private UserDao ud;
	public OrderDao getOd() {
		return od;
	}
	@Resource(name="orderDao")
	public void setOd(OrderDao od) {
		this.od = od;
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
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user != null) {
			List<Order> os = od.getOrdersInUserId(user.getUserId(), OrderStatus.NONE_STATUS, UserType.BOTH_TYPE);
			if(os != null) {
				User[] users = new User[os.size()];
				User[] seller = new User[os.size()];
				
				for(int i = 0; i < os.size(); i++) {
					users[i] = ud.getUserInId(os.get(i).getUserId());
					users[i].setUserPassword(null);
					seller[i] = ud.getUserInId(os.get(i).getSellerId());
					seller[i].setUserPassword(null);
				}
				jo.add("os", gson.toJsonTree(os));
				jo.add("users", gson.toJsonTree(users));//买家信息
				jo.add("seller", gson.toJsonTree(seller));//卖家信息
			} else
				reason = "订单为空";
		} else
			reason = "请先进行登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		out.print(jo.toString());
		out.flush();
		out.close();
	}
}
