
package com.smacker.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionSupport;
import com.smacker.bean.Order;
import com.smacker.bean.OrderStatus;
import com.smacker.bean.User;
import com.smacker.bean.UserType;
import com.smacker.dao.OrderDao;
import com.smacker.dao.UserDao;

@SuppressWarnings("serial")
@Component("deleteOrder")
@Scope("prototype")
public class DeleteOrderAction extends ActionSupport {

	private String userId;
	private String orderId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
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
	
	public void delete() {
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		
		User user = ud.getUserInId(userId);
		if(user != null) {
			
			List<Order> os = od.getOrdersInUserId(user.getUserId(), OrderStatus.NONE_STATUS, UserType.BOTH_TYPE);
			if(os != null && os.size() > 0) {
				if(od.deleteOrder(orderId)) {
					success = true;
				} else
					reason = "删除失败";
			} else
				reason = "暂无订单";
			
		} else 
			reason = "尚未登陆";

		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		out.print(jo.toString());
		out.flush();
		out.close();
	}
	
}
