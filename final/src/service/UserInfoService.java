package service;

import bean.UserInfoBean;
import com.google.gson.Gson;
import dao.UserInfoDao;
import dao.impl.UserInfoImpl;

/**
 * @author caijy@goktech.cn
 * @2019/5/31 19:03
 * 用户信息管理服务
 */
public class UserInfoService {
    //DAO对象
    private UserInfoDao userInfoDao;
    //javaBean
    private UserInfoBean userInfoBean;
    //数据类型转换工具类对象
    private Gson gson = new Gson();
    //获取用户信息
    public String getUserInfo(String userId){
        userInfoDao = new UserInfoImpl();
        userInfoBean = userInfoDao.getUserInfo(userId);
        return gson.toJson(userInfoBean);
    }
    //修改个人资料
    public Boolean UpdateUserInfo(UserInfoBean infoBean){
        userInfoDao = new UserInfoImpl();
        return userInfoDao.UpdateUserInfo(infoBean);
    };
}
