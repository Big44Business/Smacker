package com.smacker.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.smacker.bean.Order;
import com.smacker.bean.OrderStatus;
import com.smacker.bean.UserType;
import com.smacker.dao.OrderDao;

@Component("orderDao")
public class OrderDaoImpl implements OrderDao {
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean saveOrder(Order order) {
		try {
			hibernateTemplate.save(order);
			return true;
		} catch (DataAccessException e) {
			System.out.println("在OrderDaoImpl中，保存Order时出错!");
			return false;
		}
	}

	@Override
	public boolean deleteOrder(final String orderId) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<Boolean>() {
				@Override
				public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "delete Order where orderId=?";
					Query q = session.createQuery(hql);
					q.setParameter("orderId", orderId);
					q.executeUpdate();
					return false;
				}

			});
		} catch (Exception e) {
			System.out.println("在OrderDaoImpl中，删除Order信息时出现异常！");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOrder(Order order) {
		try {
			hibernateTemplate.update(order);
			return true;
		} catch (DataAccessException e) {
			System.out.println("在Orderdaoimpl中,更新Order信息时出现异常！");
			return false;
		}
	}

	@Override
	public boolean updateOrders(Order[] orders) {
		try {
			for (int i = 0; i < orders.length; i++) {
				updateOrder(orders[i]);
			}
			return true;
		} catch (DataAccessException e) {
			System.out.println("在OrderDaoImpl中,更新多个Order时出现错误！");
			return false;
		}
		
	}

	@Override
	public List<Order> getOrdersInUserId(final String userId, OrderStatus status, UserType userType) {
		// TODO Auto-generated method stub
		if(status.compareTo(OrderStatus.NONE_STATUS) == 0 && userType.compareTo(UserType.BOTH_TYPE) == 0) {
			final String hql = "from Order o where o.userId = :userId or o.sellerId = :sellerId";
			try {
				return hibernateTemplate.execute(new HibernateCallback<List<Order>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<Order> doInHibernate(Session session) throws HibernateException, SQLException {
						Query q = session.createQuery(hql);
						q.setString("userId", userId).setString("sellerId", userId);
						return (List<Order>)q.list();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else
			return null;
	}

	@Override
	public List<Order> getOrdersBeforeDate(String userId, Timestamp time, UserType userType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
