package cn.com.mvnbook.ssh.demo.service;

import java.util.List;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;


public interface IUserService {
	public void createUser(MvnUser user);
	public void editUser(int age, String status, int id);
	public void deleteUser(int id);
	public MvnUser searchUser(int id);
	public MvnUser searchUser(String userName);
	public List<MvnUser> searchUsers();
}
