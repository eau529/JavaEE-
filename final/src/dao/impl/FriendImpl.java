package dao.impl;

import bean.ArticleBean;
import bean.UserBean;
import dao.FriendDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendImpl implements FriendDao{
    //数据库连接工具类
    private DBUtil dbUtil = DBUtil.newInstance();
    //文章类bean
    private UserBean userBean;
    public List<UserBean> getFriend() {
        String sql="select * from user WHERE type=0 ORDER BY stranger DESC";
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
    public boolean markFriend(UserBean userBean){
        boolean flag=false;
        boolean flag1=false;
        if (userBean.getStranger()==7){
            String sql="update `user` set stranger=8 where id=?";
             flag=dbUtil.dataUpdate(sql,userBean.getId());

        }
        if (userBean.getStranger()==8) {
            String sql1 = "update `user` set stranger=7 where id=?";
             flag1 = dbUtil.dataUpdate(sql1, userBean.getId());

        }
        return flag||flag1;
    }
    public boolean shieldfriend(UserBean userBean){
        boolean flag=false;
        boolean flag1=false;
        boolean flag2=false;
        boolean flag3=false;
        if (userBean.getStranger()==7){
            String sql="update `user` set stranger=4 where id=?";
            flag=dbUtil.dataUpdate(sql,userBean.getId());

        }
        if (userBean.getStranger()==8) {
            String sql1 = "update `user` set stranger=5 where id=?";
            flag1 = dbUtil.dataUpdate(sql1, userBean.getId());

        }
        if (userBean.getStranger()==4){
            String sql="update `user` set stranger=7 where id=?";
            flag=dbUtil.dataUpdate(sql,userBean.getId());

        }
        if (userBean.getStranger()==5) {
            String sql1 = "update `user` set stranger=8 where id=?";
            flag1 = dbUtil.dataUpdate(sql1, userBean.getId());

        }
        return flag||flag1||flag2||flag3;
    }
    public boolean delfriend(UserBean userBean){
        boolean flag=false;

        String sql="update `user` set stranger=2 where id=?";
        flag=dbUtil.dataUpdate(sql,userBean.getId());
        return flag;
    }

    @Override
    public List<UserBean> selectFriend() {
        String sql="SELECT * FROM `user` WHERE stranger=7";
        ResultSet rs=dbUtil.dataQuery(sql);
        List<UserBean> userList=new ArrayList<UserBean>();
        try {
            while(rs.next()){
                 userBean=new UserBean();
                userBean.setId(rs.getString("id"));
                userBean.setName(rs.getString("name"));
                userBean.setBlogAddress(rs.getString("blog_address"));
                userBean.setEmailAddress(rs.getString("email_address"));
                userBean.setPassword(rs.getString("password"));
                userBean.setType(Integer.parseInt(rs.getString("type")));
                userBean.setStranger(Integer.parseInt(rs.getString("stranger")));
                userList.add(userBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
