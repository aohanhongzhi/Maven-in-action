package cn.com.mvnbook.demo.tp04.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.mvnbook.demo.tp04.db.DBConnection;
import cn.com.mvnbook.demo.tp04.entity.MvnUser;
/**
 * MvnUser实体对象的持久层代码，封装了对MvnUser实体对象的CRUD方法
 * 
 * @author Noble
 * @version 1.0
 * */
public class MvnUserDAO {
	/**
	 * 在数据库中，添加一个新的MvnUser对象
	 * 
	 * @param user 需要添加的用户实体对象，该对象需要有用户名、密码、年龄和状态属性
	 * 
	 * @return void
	 * @throws RuntimeException 添加失败或出现其它意外
	 * */
	public void addUser(MvnUser user) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 获取DBConnection实例
			dbConn = DBConnection.getInstance();
			String sql = "insert into mvn_user (ur_user_name,ur_password,ur_age,ur_status) values(?,?,?,?)";
			// 获取连接对象
			conn = dbConn.getConnection();
			// 基于连接和sql，获取一个预处理Statement对象
			pstmt = conn.prepareStatement(sql);
			// 设置sql中占位符的值
			pstmt.setString(1, user.getUrUserName());
			pstmt.setString(2, user.getUrPassword());
			pstmt.setInt(3, user.getUrAge());
			pstmt.setString(4, user.getUrStatus());
            // 执行预处理
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			if (dbConn != null)
				dbConn.close(conn, pstmt, null);
		}
	}

	/**
	 * 更新MvnUser对象。该对象中需要设置年龄、状态和id属性，属性和状态是要更新的新值，id为条件
	 * 
	 * @param user 需要更新的MvnUser对象
	 * 
	 * @return void
	 * @throws RuntimeException 更新失败或出现其它意外
	 * */
	public void update(MvnUser user) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			dbConn = DBConnection.getInstance();
			String sql = "update mvn_user set ur_age=?,ur_status=? where ur_id=?";
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUrAge());
			pstmt.setString(2, user.getUrStatus());
			pstmt.setInt(3, user.getUrId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (dbConn != null)
				dbConn.close(conn, pstmt, null);
		}
	}
    /**
     * 删除MvnUser对象，该对象中需要有要删除对象的id属性，id属性为删除条件
     * 
     * @param user 要删除的MvnUser对象
     * 
     * @return void
     * @throws RuntimeException 删除失败或出现其它意外
     * */
	public void deleteUser(MvnUser user) {
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			dbConn = DBConnection.getInstance();
			String sql = "delete from mvn_user where ur_id=?";
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUrId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (dbConn != null)
				dbConn.close(conn, pstmt, null);
		}
	}

	/**
	 * 根据id查询对应的MvnUser对象
	 * 
	 * @param id 要查询的MvnUser对象的id
	 * @return MvnUser id对应的MvnUser对象，如果没有对象，返回null
	 * @throws RuntimeException 出现意外情况
	 * */
	public MvnUser findUserById(int id) {
		MvnUser user = null;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbConn = DBConnection.getInstance();
			String sql = "select * from mvn_user where ur_id=?";
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new MvnUser();
				user.setUrAge(rs.getInt("ur_age"));
				user.setUrId(rs.getInt("ur_id"));
				user.setUrPassword(rs.getString("ur_password"));
				user.setUrStatus(rs.getString("ur_status"));
				user.setUrUserName(rs.getString("ur_user_name"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (dbConn != null)
				dbConn.close(conn, pstmt, rs);
		}
		return user;
	}
	/**
	 * 根据用户名查询对应的MvnUser对象
	 * 
	 * @param userName 要查询的MvnUser对象的用户名
	 * @return MvnUser 用户对应的MvnUser对象，如果没有对象，返回null
	 * @throws RuntimeException 出现意外情况
	 * */
	public MvnUser findUserByUserName(String userName) {
		MvnUser user = null;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbConn = DBConnection.getInstance();
			String sql = "select * from mvn_user where ur_user_name=?";
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new MvnUser();
				user.setUrAge(rs.getInt("ur_age"));
				user.setUrId(rs.getInt("ur_id"));
				user.setUrPassword(rs.getString("ur_password"));
				user.setUrStatus(rs.getString("ur_status"));
				user.setUrUserName(rs.getString("ur_user_name"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (dbConn != null)
				dbConn.close(conn, pstmt, rs);
		}
		return user;
	}

	/**
	 * 查找数据库中所有的用户对象，以List集合的形式返回
	 * 
	 * @return List<MvnUser> 所有用户对象的集合
	 * @throws RuntimeException 出现意外情况
	 * */
	public List<MvnUser> findUsers() {
		List<MvnUser> userList = null;
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbConn = DBConnection.getInstance();
			String sql = "select * from mvn_user order by ur_id";
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs != null) {
				userList = new ArrayList<MvnUser>();
				MvnUser user = null;
				while (rs.next()) {
					user = new MvnUser();
					user.setUrAge(rs.getInt("ur_age"));
					user.setUrId(rs.getInt("ur_id"));
					user.setUrPassword(rs.getString("ur_password"));
					user.setUrStatus(rs.getString("ur_status"));
					user.setUrUserName(rs.getString("ur_user_name"));

					userList.add(user);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (dbConn != null)
				dbConn.close(conn, pstmt, rs);
		}
		return userList;
	}
}

