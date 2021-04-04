package dao;

import bean.UserBean;
import java.util.List;

/**
 * 关于用户user的接口
 * @author ZHM
 *
 */
public interface UserDao {
	
	/**
	 * 博主登录
	 */
	public UserBean adminLogin(String email, String password);
	
	/**
	 * 普通用户登录
	 * @param email
	 * @return
	 */
	public UserBean userLogin(String email);
	
	/**
	 * 获取好友列表
	 * @return
	 */
	public List<UserBean> getFriendsList();
	
	/**
	 * 注册一个帐户
	 * @param user
	 * @return
	 */
	public boolean userRegister(UserBean user);
	
}
