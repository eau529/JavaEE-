package service;


import bean.UserBean;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import utils.Util;

import java.util.List;


public class LoginService {

	private UserDao userDao=new UserDaoImpl();
	
	public UserBean adminLogin(String email, String password) {
		return userDao.adminLogin(email, password);
	}

	public UserBean userLogin(String email) {

		return userDao.userLogin(email);
	}
	public List<UserBean> getFriendsList() {
		return userDao.getFriendsList();
	}

	public boolean userRegister(UserBean user) {
		user.setId(Util.createID());
		user.setType(0);
		user.setStranger(8);
		return userDao.userRegister(user);
	}
}
