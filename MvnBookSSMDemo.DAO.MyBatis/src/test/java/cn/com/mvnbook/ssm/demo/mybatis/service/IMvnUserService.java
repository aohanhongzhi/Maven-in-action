package cn.com.mvnbook.ssm.demo.mybatis.service;
import java.util.List;

import cn.com.mvnbook.ssh.demo.entity.MvnUser;

public interface IMvnUserService {
	/**
	 * 在数据库中，添加一个新的MvnUser对象
	 * 
	 * @param user
	 *            需要添加的用户实体对象，该对象需要有用户名、密码、年龄和状态属性
	 * 
	 * @return void
	 * @throws RuntimeException
	 *             添加失败或出现其它意外
	 */
	public void addUser(MvnUser user);

	/**
	 * 更新MvnUser对象。该对象中需要设置年龄、状态和id属性，属性和状态是要更新的新值，id为条件
	 * 
	 * @param user
	 *            需要更新的MvnUser对象
	 * 
	 * @return void
	 * @throws RuntimeException
	 *             更新失败或出现其它意外
	 */
	public void update(MvnUser user);

	/**
	 * 删除MvnUser对象，该对象中需要有要删除对象的id属性，id属性为删除条件
	 * 
	 * @param user
	 *            要删除的MvnUser对象
	 * 
	 * @return void
	 * @throws RuntimeException
	 *             删除失败或出现其它意外
	 */
	public void deleteUser(MvnUser user);

	/**
	 * 根据id查询对应的MvnUser对象
	 * 
	 * @param id
	 *            要查询的MvnUser对象的id
	 * @return MvnUser id对应的MvnUser对象，如果没有对象，返回null
	 * @throws RuntimeException
	 *             出现意外情况
	 */
	public MvnUser findUserById(int id);

	/**
	 * 根据用户名查询对应的MvnUser对象
	 * 
	 * @param userName
	 *            要查询的MvnUser对象的用户名
	 * @return MvnUser 用户对应的MvnUser对象，如果没有对象，返回null
	 * @throws RuntimeException
	 *             出现意外情况
	 */
	public MvnUser findUserByUserName(String userName);

	/**
	 * 查找数据库中所有的用户对象，以List集合的形式返回
	 * 
	 * @return List<MvnUser> 所有用户对象的集合
	 * @throws RuntimeException
	 *             出现意外情况
	 */
	public List<MvnUser> findUsers();
}

