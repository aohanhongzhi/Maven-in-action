package cn.com.mvnbook.ssh.demo.dao.hibernate.impl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mvnbook.ssh.demo.dao.IMvnUserDAO;
import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.entity.Status;
import junit.framework.Assert;

public class TestMvnUserDAOImpl {
	private IMvnUserDAO userDAO;
	private ApplicationContext ctx = null;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDAO = (IMvnUserDAO)this.ctx.getBean("userDAO");
		//userDAO = (IMvnUserDAO) this.ctx.getBean(MvnUserDAOImpl.class);
	}
	@Test
	public void testAddUser(){
		MvnUser user= new MvnUser();
		user.setUrAge(11);
		user.setUrPassword("11");
		user.setUrStatus(Status.ACTIVE.getStatus());
		user.setUrUserName("userName11");
		this.userDAO.addUser(user);
		
		MvnUser u = this.userDAO.findUserByUserName("userName11");
		Assert.assertTrue(u != null && u.getUrAge()==11);
		this.userDAO.deleteUser(u);
	}
	@Test
	public void testFindUserById(){
		MvnUser user = this.userDAO.findUserById(1);
		Assert.assertEquals("zhangsan", user.getUrUserName());
	}
	@Test
	public void testUpdate(){
		MvnUser user = this.userDAO.findUserById(1);
		user.setUrAge(99);
		this.userDAO.update(user);
		user = this.userDAO.findUserById(1);
		Assert.assertEquals(99, user.getUrAge());
	}
	@After
	public void destory(){
		this.userDAO = null;
		this.ctx = null;
	}
}

