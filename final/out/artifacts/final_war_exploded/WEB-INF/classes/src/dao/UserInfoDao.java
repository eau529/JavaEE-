package dao;

import bean.UserInfoBean;

/**
 * @author caijy@goktech.cn
 * @2019/5/31 18:52
 * describe
 */
public interface UserInfoDao {
    //查询用户个人信息
    public UserInfoBean getUserInfo(String userId);
    //修改个人资料
    public boolean UpdateUserInfo(UserInfoBean infoBean);
}
