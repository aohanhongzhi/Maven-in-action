package cn.com.mvnbook.demo.tp04.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.mvnbook.demo.tp04.entity.MvnUser;
import cn.com.mvnbook.demo.tp04.service.UserService;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchUserServlet
 */
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取查询的方式（根据id查询还是根据用户名查询）
		String type = request.getParameter("type");
		UserService userService = new UserService();
		MvnUser user = null;
		// 根据id查询
		if ("byId".equals(type)) {
			// 获取id
			String idStr = request.getParameter("id");
			int id = 0;
			try {
				id = Integer.parseInt(idStr);
			} catch (Exception e) {
			}
			user = userService.searchUser(id);
		} else {
			// 根据用户名查询
			String userName = request.getParameter("userName");
			user = userService.searchUser(userName);
		}
		// 设置返回的响应为json响应
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 将查询的用户对象，转变成json格式的字符串，写入响应返回
		out.print(JSONObject.fromObject(user));
		out.flush();
		out.close();
	}

}

