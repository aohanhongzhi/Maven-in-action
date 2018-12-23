package cn.com.mvnbook.demo.tp04.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 获取连接的一个工具类，继承Properties，实现如下封装：<br/>
 * 1 读取db.properties文件<br/>
 * 2 获取Connection连接的方法<br/>
 * 3 关闭资源的方法
 * 
 * @author Noble
 * @version 1.0
 * */
public class DBConnection extends Properties {
	private static DBConnection DB_CONN = null;
    /**
     * 构造器方法，被私有化，封装读取db.properties逻辑
     * 
     * */
	private DBConnection() throws Exception {
		InputStream in = DBConnection.class.getClassLoader()
				.getResourceAsStream("db.properties");
		this.load(in);
		// 加载驱动类
		Class.forName(this.getProperty("driverName"));
	}

	/**
	 * 单例模式实现，获取DBConnection实例的静态方法
	 * 
	 * @return DBConnection DBConnection实例
	 * @throws Exception 初始化db.properties出现问题时，会抛异常
	 * */
	public static DBConnection getInstance() throws Exception {
		if (DB_CONN == null) {
			DB_CONN = new DBConnection();
		}
		return DB_CONN;
	}

	/**
	 * 基于驱动和db.properties中配置的连接数据库的信息，创建一个新连接返回
	 * 
	 * @return Connection 创建的新连接对象
	 * 
	 * */
	public Connection getConnection() {
		Connection conn = null;
		String url = this.getProperty("url");
		String userName = this.getProperty("userName");
		String password = this.getProperty("password");
		//
		try {
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			throw new RuntimeException("数据库连接错误，请与管理员联系");
		}
		return conn;
	}

	/**
	 * 关闭操作数据库后的资源
	 * 
	 * @param conn Connection对象
	 * @param stmt Statement或Statement的子类对象
	 * @param rs ResultSet对象
	 * */
	public void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
