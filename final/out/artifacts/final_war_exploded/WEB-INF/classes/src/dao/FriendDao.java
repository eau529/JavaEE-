package dao;

import bean.UserBean;

import java.util.List;

/**
 * @author caijy@goktech.cn
 * @2019/6/2 9:57
 * describe
 */
public interface FriendDao {
    //查询所有朋友
    public List<UserBean> getFriend();
    public boolean markFriend(UserBean userBean);
    public boolean shieldfriend(UserBean userBean);
    public boolean delfriend(UserBean userBean);
    public List<UserBean> selectFriend();
}
