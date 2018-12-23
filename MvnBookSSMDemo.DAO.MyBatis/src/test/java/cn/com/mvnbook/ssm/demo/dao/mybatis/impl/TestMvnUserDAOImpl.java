package cn.com.mvnbook.ssm.demo.dao.mybatis.impl;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.entity.Status;
import cn.com.mvnbook.ssm.demo.mybatis.service.IMvnUserService;
import junit.framework.Assert;

public class TestMvnUserDAOImpl {
    private ApplicationContext ctx;
    private IMvnUserService userService;
    @Before
    public void init(){
    	this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	this.userService = (IMvnUserService)ctx.getBean("userService");
    }
    @Test
    public void testAddUser(){
    	MvnUser user = new MvnUser();
    	user.setUrAge(11);
    	user.setUrPassword("11");
    	user.setUrStatus(Status.ACTIVE.getStatus());
    	user.setUrUserName("myBatisUserName");
    	this.userService.addUser(user);
    	user = this.userService.findUserByUserName("myBatisUserName");
    	Assert.assertTrue(user != null && user.getUrAge()==11);
    	this.userService.deleteUser(user);
    }
    @Test
    public void testDeleteUser(){
    	MvnUser user = new MvnUser();
    	user.setUrAge(11);
    	user.setUrPassword("11");
    	user.setUrStatus(Status.ACTIVE.getStatus());
    	user.setUrUserName("myBatisUserName");
    	this.userService.addUser(user);
    	user = this.userService.findUserByUserName("myBatisUserName");
    	this.userService.deleteUser(user);
    	MvnUser u = this.userService.findUserByUserName("myBatisUserName");
    	Assert.assertTrue(user != null && u==null);
    	
    }
    @Test
    public void testFindUserById(){
    	MvnUser user = this.userService.findUserById(1);
    	Assert.assertEquals(user.getUrUserName(),"zhangsan");
    }
    @Test
    public void testFindByUserName(){
    	MvnUser user = this.userService.findUserByUserName("zhangsan");
    	Assert.assertEquals("123", user.getUrPassword());
    }
    @Test
    public void testFindUsers(){
    	List<MvnUser> userList = this.userService.findUsers();
    	Assert.assertTrue(userList.size()>0);
    }
    @After
    public void destory(){
    	this.userService = null;
    	this.ctx = null;
    }
}

