package com.smacker.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.smacker.dao.UserDao;

public class UserDaoTest extends BaseJunitClass {
	private UserDao userDao;

	@Rollback(true) // 运行时并没有rollback！！
	@Test
	public void testSaveUser() {
		//保存空对象时应返回false
				
	}

	@Resource(name = "userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
