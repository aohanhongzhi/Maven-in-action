package cn.com.mvnbook.demo.tp04.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.mvnbook.demo.tp04.entity.MvnUser;
import cn.com.mvnbook.demo.tp04.service.UserService;

/**
 * Servlet implementation class SearchUsersServlet
 */
public class SearchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		List<MvnUser> userList = userService.searchUsers();
		// 将查询出的用户集合，写于request属性中
		request.setAttribute("userList", userList);
		// 转向 userList.jsp页面
		request.getRequestDispatcher("/userList.jsp").forward(request, response);
	}

}

