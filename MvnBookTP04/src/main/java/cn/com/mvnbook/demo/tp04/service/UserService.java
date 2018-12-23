package cn.com.mvnbook.demo.tp04.service;
import java.util.List;

import cn.com.mvnbook.demo.tp04.dao.MvnUserDAO;
import cn.com.mvnbook.demo.tp04.entity.MvnUser;
/**
 * 对用CRUD操作的服务层，封装了CRUD在持久化前的必要业务逻辑
 * 
 * @author Noble
 * @version 1.0
 * */
public class UserService {
	private MvnUserDAO userDAO = new MvnUserDAO();

	/**
	 * 创建新的用户
	 * @param user 要创建的用户对象
	 * @return void 
	 * @throws RuntimeException 当用户信息不全或用户名已经存在的时候，都会抛出异常
	 * */
	public void createUser(MvnUser user) {
		// 验证基本的用户信息
		if (user == null || user.getUrUserName() == null || user.getUrPassword() == null) {
			throw new RuntimeException("用户信息不合法");
		}
		// 根据用户名查询用户对象
		MvnUser u = userDAO.findUserByUserName(user.getUrUserName());
		// 如果能查询到用户对象，说明用户已经存在，抛异常
		if (u != null) {
			throw new RuntimeException(user.getUrUserName() + " 用户已存在");
		}
		// 调用dao代码，添加一个新用户
		userDAO.addUser(user);
	}

	/**
	 * 更新id对应用户的年龄和状态信息
	 * 
	 * @param age 要更新用户的新年龄
	 * @param status 要更新用户的新状态
	 * @param id 要更新用户的id，这是更新的条件
	 * 
	 * @return void
	 * */
	public void editUser(int age, String status, int id) {
		MvnUser user = this.userDAO.findUserById(id);
		user.setUrAge(age);
		user.setUrStatus(status);
		this.userDAO.update(user);
	}

	public void deleteUser(int id) {
		MvnUser user = this.userDAO.findUserById(id);
		this.userDAO.deleteUser(user);
	}

	public MvnUser searchUser(int id) {
		MvnUser user = this.userDAO.findUserById(id);
		return user;
	}
	public MvnUser searchUser(String userName) {
		MvnUser user = this.userDAO.findUserByUserName(userName);
		return user;
	}

	public List<MvnUser> searchUsers() {
		List<MvnUser> userList = this.userDAO.findUsers();
		return userList;
	}
}

