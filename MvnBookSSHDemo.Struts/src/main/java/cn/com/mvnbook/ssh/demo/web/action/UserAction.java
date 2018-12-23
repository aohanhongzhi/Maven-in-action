package cn.com.mvnbook.ssh.demo.web.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.service.IUserService;
import cn.com.mvnbook.ssh.demo.web.vo.Message;

@Controller("userAction")
public class UserAction {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	private MvnUser user;
	private int id;
	private String userName;
	private String type;

	private String status;
	private int age;
	private Message message;

	// 新增用户的执行方法
	public String addUser() {
		String result = "addSuccess";
		message = new Message("添加成功");
		try {
			// 调用service，创建用户
			userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("添加失败：" + e.getMessage());
		}
		return result;
	}

	// 删除用户的执行方法
	public String deleteUser() {
		String result = "deleteSuccess";
		message = new Message("删除成功");
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("删除失败:" + e.getMessage());
		}
		return result;
	}

	// 修改用户的执行方法
	public String editUser() {
		String result = "editSuccess";
		this.message = new Message("修改成功!");
		try {
			userService.editUser(age, status, id);
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("修改失败:" + e.getMessage());
		}
		return result;
	}

	// 根据id或用户名查找单个用户的执行方法
	public String searchUser() {
		String result = "searchUserSeuccess";
		if ("byId".equals(type)) {
			user = this.userService.searchUser(id);
		} else {
			user = this.userService.searchUser(userName);
		}
		return result;
	}

	private List<MvnUser> userList;

	// 查询所有用户列表的执行方法
	public String searchUsers() {
		String result = "searchUsersSuccess";
		this.userList = this.userService.searchUsers();
		return result;
	}

	public List<MvnUser> getUserList() {
		return userList;
	}

	public void setUserList(List<MvnUser> userList) {
		this.userList = userList;
	}

	public MvnUser getUser() {
		return user;
	}

	public void setUser(MvnUser user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}

