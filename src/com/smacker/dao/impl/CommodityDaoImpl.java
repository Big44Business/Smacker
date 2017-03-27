package com.smacker.dao.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.smacker.bean.Commodity;
import com.smacker.dao.CommodityDao;

@Component("commodityDao")
public class CommodityDaoImpl implements CommodityDao {
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean saveCommodity(Commodity cmdty) {
		try {
			hibernateTemplate.save(cmdty);
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
		public boolean updateCommodity(Commodity cmdty){
				try{
						hibernateTemplate.update(cmdty);
						return true;
				}catch(DataAccessException e){
						System.out.println("在Commodityimpl中,更新commodity时出现异常");
						return false;
				}
		}

	@Override
	public boolean deleteCommodity(final String cmdtyId) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<Boolean>() {
				@Override
				public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "delete Commodity where commodityId=?";
					Query q = session.createQuery(hql);
					q.setParameter("commodityId", cmdtyId);
					q.executeUpdate();
					return false;
				}

			});
		} catch (Exception e) {
			System.out.println("在CommodityImpl中，删除用户信息时出现异常！");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Commodity getCommodityInId(String cmdtyId) {
		return hibernateTemplate.get(Commodity.class, cmdtyId);
	}

	@Override
		public Commodity getCommodity(Commodity cmty){
				//调用getCommodityInId(String);
				return getCommodityInId(cmty.getCommodityId());
		}

	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
