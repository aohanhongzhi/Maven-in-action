package cn.com.mvnbook.ssm.demo.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;
import cn.com.mvnbook.ssh.demo.service.IUserService;
import cn.com.mvnbook.ssm.demo.web.vo.Message;

@Controller("userController")
@RequestMapping("/user")
public class UserController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping("/add")
	@ResponseBody
	public Message addUser(MvnUser user) {
		Message msg = new Message("添加成功");
		try {
			this.userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMessage("添加失败:" + e.getMessage());
		}
		return msg;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Message deleteUser(int id) {
		Message msg = new Message("删除成功");
		try {
			this.userService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMessage("删除失败:" + e.getMessage());
		}
		return msg;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Message editUser(@RequestParam("status") String status, @RequestParam("age") int age,@RequestParam("id")  int id) {
		Message msg = new Message("修改成功");
		try {
			this.userService.editUser(age, status, id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMessage("修改失败:" + e.getMessage());
		}
		return msg;
	}

	@RequestMapping("/searchUser")
	@ResponseBody
	public MvnUser searchUser(@RequestParam("id") int id) {
		MvnUser user = this.userService.searchUser(id);
		return user;
	}

	@RequestMapping("/searchUsers")
	public ModelAndView searchUsers() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userList");
		mv.addObject("userList", this.userService.searchUsers());
		return mv;
	}
}

