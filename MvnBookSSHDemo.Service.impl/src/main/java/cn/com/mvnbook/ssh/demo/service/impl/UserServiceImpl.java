package cn.com.mvnbook.ssh.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mvnbook.ssh.demo.dao.IMvnUserDAO;
import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.service.IUserService;

@Service("userService") // 注册成服务组件
@Transactional // 要求启动事务
public class UserServiceImpl implements IUserService {
	@Autowired // 自动根据类型注入
	@Qualifier("userDAO") // 根据name注入
	private IMvnUserDAO userDAO;

	@Transactional(propagation=Propagation.REQUIRED)//需要進行事务管理
	public void createUser(MvnUser user) {
		// 验证输入的用户是否为null
		if (user == null) {
			throw new RuntimeException("创建的user不能为null");
		}
		// 验证用户名是否有存在
		MvnUser u = this.userDAO.findUserByUserName(user.getUrUserName());
		if (u != null) {
			throw new RuntimeException(u.getUrUserName() + " 已经存在");
		}
		this.userDAO.addUser(user);
	}
	@Transactional(propagation=Propagation.REQUIRED)//需要進行事务管理
	public void editUser(int age, String status, int id) {
		// 根据id找到以前的用户对象
		MvnUser user = this.userDAO.findUserById(id);
		// 判断用户是否存在，不存在抛异常，存在就更新
		if (user == null) {
			throw new RuntimeException("id为" + id + "用户不存在");
		} else {
			user.setUrAge(age);
			user.setUrStatus(status);
			this.userDAO.update(user);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED)//需要進行事务管理
	public void deleteUser(int id) {
		// 根据id找到以前的用户对象
		MvnUser user = this.userDAO.findUserById(id);
		// 判断用户是否存在，不存在抛异常，存在就删除
		if (user == null) {
			throw new RuntimeException("id为" + id + "用户不存在");
		} else {
			this.userDAO.deleteUser(user);
		}
	}
	@Transactional(readOnly=true)//只读，不需要進行事务管理
	public MvnUser searchUser(int id) {
		MvnUser user = null;
		user = this.userDAO.findUserById(id);
		return user;
	}
	@Transactional(readOnly=true)//只读，不需要進行事务管理
	public MvnUser searchUser(String userName) {
		MvnUser user = null;
		user = this.userDAO.findUserByUserName(userName);
		return user;
	}
	@Transactional(readOnly=true)//只读，不需要進行事务管理
	public List<MvnUser> searchUsers() {
		List<MvnUser> userList = null;
		userList = this.userDAO.findUsers();
		return userList;
	}

}

