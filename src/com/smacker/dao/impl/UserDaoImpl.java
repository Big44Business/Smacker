package com.smacker.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.smacker.bean.User;
import com.smacker.dao.UserDao;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean saveUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			hibernateTemplate.update(user);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(String userId) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<Boolean>() {

				@Override
				public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "delete User where userId=?";
					Query q = session.createQuery(hql);
					q.setString("userId", userId);
					q.executeUpdate();
					return false;
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUserInId(String userId) {
		try {
			List<User> result = hibernateTemplate.executeFind(new HibernateCallback<User>() {

				@Override
				public User doInHibernate(Session session) throws HibernateException, SQLException {
					
					return null;
				}
				
			});
			return result.get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Resource(name = "hibernateTemplate")
	public void setHihernateTemplate(HibernateTemplate hihernateTemplate) {
		this.hihernateTemplate = hihernateTemplate;
	}

}
