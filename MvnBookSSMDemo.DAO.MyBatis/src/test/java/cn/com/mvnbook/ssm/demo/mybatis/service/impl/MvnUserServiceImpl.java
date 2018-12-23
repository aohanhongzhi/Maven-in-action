package cn.com.mvnbook.ssm.demo.mybatis.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mvnbook.ssh.demo.dao.IMvnUserDAO;
import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssm.demo.mybatis.service.IMvnUserService;
@Service("userService")
@Transactional
public class MvnUserServiceImpl implements IMvnUserService {
	@Autowired
    private IMvnUserDAO userDAO;
	public void addUser(MvnUser user) {
		this.userDAO.addUser(user);
	}

	public void update(MvnUser user) {
		this.userDAO.update(user);
	}

	public void deleteUser(MvnUser user) {
		this.userDAO.deleteUser(user);
	}

	public MvnUser findUserById(int id) {
		return this.userDAO.findUserById(id);
	}

	public MvnUser findUserByUserName(String userName) {
		return this.userDAO.findUserByUserName(userName);
	}

	public List<MvnUser> findUsers() {
		return this.userDAO.findUsers();
	}
}

