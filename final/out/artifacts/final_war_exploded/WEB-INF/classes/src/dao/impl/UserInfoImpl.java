package dao.impl;

import bean.UserInfoBean;
import dao.UserInfoDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caijy@goktech.cn
 * @2019/5/31 18:53
 * describe
 */
public class UserInfoImpl implements UserInfoDao {
    //获取数据库连接
    private DBUtil dbUtil  = DBUtil.newInstance();
    //定义userBean
    private  UserInfoBean userInfoBean;
    //获取用户个人信息
    @Override
    public UserInfoBean getUserInfo(String userId) {
        userInfoBean = new UserInfoBean();
        String sql = "select * from myinfo  where userid = ? ";
        ResultSet rs = dbUtil.dataQuery(sql,userId);
        try {
            if(rs.next()){
                userInfoBean.setInfo(rs.getString("info"));
                userInfoBean.setHobby(rs.getString("hobby"));
                userInfoBean.setQq(rs.getString("qq"));
                userInfoBean.setEmail(rs.getString("email"));
                userInfoBean.setDescription(rs.getString("description"));
                userInfoBean.setInfo(rs.getString("info"));
            }
        } catch (SQLException e) {
            System.out.println("数据库查询异常"+e.getMessage());
        }
        return userInfoBean;
    }

    /**
     * 修改用户个人信息
     * @param infoBean
     * @return
     */
    @Override
    public boolean UpdateUserInfo(UserInfoBean infoBean) {
        String sql = "update myinfo set info=?,hobby=?,qq=?,email=?,description=?" +
                ",userid=? where id=?";
        String emailChangeSql="update user set email_address=? where id=?";
        boolean b1=dbUtil.dataUpdate(sql, infoBean.getInfo(),infoBean.getHobby(),infoBean.getQq(),infoBean.getEmail(),infoBean.getDescription(),
                infoBean.getUserId(),infoBean.getId());

        boolean b2=dbUtil.dataUpdate(emailChangeSql, infoBean.getEmail(),infoBean.getUserId());
        return b1&&b2;
    }
}
