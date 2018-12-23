package cn.com.mvnbook.ssh.demo.service.impl;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.entity.Status;
import cn.com.mvnbook.ssh.demo.service.IUserService;
import junit.framework.Assert;

public class TestUserServiceImpl {
    private IUserService userService;
    private ApplicationContext ctx = null;
    @Before
    public void init(){
    	this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	this.userService = (IUserService)ctx.getBean("userService");
    }
    @Test
    public void testCreateUser(){
    	MvnUser user = new MvnUser();
    	user.setUrAge(11);
    	user.setUrPassword("11");
    	user.setUrStatus(Status.ACTIVE.getStatus());
    	user.setUrUserName("service1");
    	this.userService.createUser(user);
    	MvnUser u = this.userService.searchUser("service1");
    	boolean bool = u != null && u.getUrAge()==11 && u.getUrStatus().equals(Status.ACTIVE.getStatus());
    	Assert.assertTrue(bool);
    	// 删除用户
    	this.userService.deleteUser(u.getUrId());
    }
    @Test
    public void testEditUser(){
    	MvnUser user = new MvnUser();
    	user.setUrAge(11);
    	user.setUrPassword("11");
    	user.setUrStatus(Status.ACTIVE.getStatus());
    	user.setUrUserName("service1");
    	this.userService.createUser(user);
    	MvnUser u = this.userService.searchUser("service1");
    	this.userService.editUser(88, Status.INACTIVE.getStatus(), u.getUrId());
    	u = this.userService.searchUser("service1");
    	Assert.assertTrue(u.getUrAge()==88 && u.getUrStatus().equals(Status.INACTIVE.getStatus()));
    	this.userService.deleteUser(u.getUrId());
    }
    @Test
    public void testDeleteUser(){
    	MvnUser user = new MvnUser();
    	user.setUrAge(11);
    	user.setUrPassword("11");
    	user.setUrStatus(Status.ACTIVE.getStatus());
    	user.setUrUserName("service1");
    	this.userService.createUser(user);
    	MvnUser u = this.userService.searchUser("service1");
    	this.userService.deleteUser(u.getUrId());
    	MvnUser u2 = this.userService.searchUser(u.getUrId());
    	Assert.assertTrue(u != null && u2 == null);
    }
    @Test
    public void testSearchUserById(){
    	MvnUser user = this.userService.searchUser(1);
    	Assert.assertNotNull(user);
    }
    @Test
    public void testSearchUserByUserName(){
    	MvnUser user = this.userService.searchUser("zhangsan");
    	Assert.assertNotNull(user);
    }
    @Test
    public void testSearchUsers(){
    	List<MvnUser> userList = this.userService.searchUsers();
    	Assert.assertTrue(userList != null && userList.size()>0);
    }
    @After
    public void destory(){
    	this.userService = null;
    	this.ctx = null;
    }
}

