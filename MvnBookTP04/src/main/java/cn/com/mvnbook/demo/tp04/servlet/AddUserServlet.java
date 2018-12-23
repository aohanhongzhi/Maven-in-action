package cn.com.mvnbook.demo.tp04.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.mvnbook.demo.tp04.entity.MvnUser;
import cn.com.mvnbook.demo.tp04.service.UserService;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取要添加用户的信息
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String ageStr = request.getParameter("age");
		String status = request.getParameter("status");
		int age = 0;
		try {
			// 将年龄字符串，转变成数字（数据库中需要数字类型）
			age = Integer.parseInt(ageStr);
		} catch (Exception e) {
		}
		// 封装成MvnUser对象
		MvnUser user = new MvnUser();
		user.setUrAge(age);
		user.setUrPassword(password);
		user.setUrStatus(status);
		user.setUrUserName(userName);

		UserService userService = new UserService();
		String msg = "添加成功";
		try {
			// 调用service，创建用户
			userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败：" + e.getMessage();
		}
		// 返回添加后的结果提示信息
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(msg);
	}

}

