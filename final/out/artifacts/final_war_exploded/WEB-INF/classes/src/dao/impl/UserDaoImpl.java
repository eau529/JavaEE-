package dao.impl;


import bean.UserBean;
import dao.UserDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	private DBUtil dbUtil=DBUtil.newInstance();

	@Override
	public UserBean adminLogin(String email, String password) {
		String sql="select * from user where email_address =? and password =? ";
		ResultSet rs=dbUtil.dataQuery(sql, email,password);
		UserBean admin=new UserBean();
		try {
			if(rs.next()){
				admin.setId(rs.getString("id"));
				admin.setName(rs.getString("name"));
				admin.setBlogAddress(rs.getString("blog_address"));
				admin.setEmailAddress(rs.getString("email_address"));
				admin.setPassword(rs.getString("password"));
				admin.setType(Integer.parseInt(rs.getString("type")));
				admin.setStranger(Integer.parseInt(rs.getString("stranger")));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public UserBean userLogin(String email) {
		String sql="select * from user where email_address =? ";
		ResultSet rs=dbUtil.dataQuery(sql, email);

		UserBean user=new UserBean();
		try {
			if(rs.next()){
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setBlogAddress(rs.getString("blog_address"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setType(Integer.parseInt(rs.getString("type")));
				user.setStranger(Integer.parseInt(rs.getString("stranger")));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<UserBean> getFriendsList() {
		String sql="select * from user where stranger =7;";
		ResultSet rs=dbUtil.dataQuery(sql);
		
		List<UserBean> userList=new ArrayList<UserBean>();
		try {
			while(rs.next()){
				UserBean user=new UserBean();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setBlogAddress(rs.getString("blog_address"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setType(Integer.parseInt(rs.getString("type")));
				user.setStranger(Integer.parseInt(rs.getString("stranger")));
				
				userList.add(user);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return userList;
	}

	public boolean userRegister(UserBean user) {
		String findSql="select * from user where name=? or email_address=?";
		ResultSet rs=null;
		rs=dbUtil.dataQuery(findSql, user.getName(),user.getEmailAddress());
		
		try {
			if(rs.next()){
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String insertsql="insert into user(id,name,blog_address,email_address,type,stranger)"
				+ "values(?,?,?,?,?,?)";
		return dbUtil.dataUpdate(insertsql, user.getId(),user.getName(),user.getBlogAddress(),
				user.getEmailAddress(),user.getType(),user.getStranger());
	}

}
