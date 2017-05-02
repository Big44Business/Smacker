import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.smacker.bean.User;
import com.smacker.bean.UserType;
import com.smacker.dao.UserDao;

@ContextConfiguration("classpath:beans.xml")
public class TestDaoImpl extends AbstractJUnit4SpringContextTests {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate ht;
	
	public void testSave() {
		User user = new User();
		user.setUserNickName("罗亚飞ssss");
		//user.setUserEmail("luoyaefiss@qq.com");
		
		user.setUserPassword("12345ss6");
		ht.save(user);
//		userDao.saveUser(user);
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		UserDao userDao = new ClassPathXmlApplicationContext("beans.xml").getBean("userDao",UserDao.class);
		User user = new User();
		user.setUserNickName("罗亚飞");
		//user.setUserEmail("luoyaefi@qq.com");
		user.setUserPassword("123456");
		userDao.saveUser(user);
	}
	@Test
	public void testEnum() {
		String str = String.valueOf(UserType.BOTH_TYPE);
		System.out.println(str);
	}
}
