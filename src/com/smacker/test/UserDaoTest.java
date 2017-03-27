package com.smacker.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.smacker.bean.User;
import com.smacker.dao.UserDao;

import junit.framework.Assert;

public class UserDaoTest extends BaseJunitClass {
	private UserDao userDao;

	@Rollback(true) // 运行时并没有rollback！！
	@Test
	public void testSaveUser() {
		User u = new User();
		u.setUserRealName("aaaa");
		u.setUserId("1111");
		u.setUserNickName("aaaaa");
		u.setUserPassword("asdf");
		Assert.assertEquals(true, userDao.saveUser(u));
		
		//中文名字
		User u1 = new User();
		u.setUserRealName("崔世兵");
		u.setUserId("1234");
		u.setUserNickName("速度");
		u.setUserPassword("asdf");
		Assert.assertEquals(true, userDao.saveUser(u1));
		
		//保存空对象时应返回false
		Assert.assertFalse(userDao.saveUser(null));
				
	}

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
