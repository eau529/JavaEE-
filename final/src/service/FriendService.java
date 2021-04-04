package service;


import bean.ArticleBean;
import bean.UserBean;
import dao.FriendDao;
import dao.impl.ArticleImpl;
import dao.impl.FriendImpl;
import com.google.gson.Gson;

import java.util.List;

public class FriendService {
    //User类dao
    private  static FriendDao friendDao = new FriendImpl();
    private Gson gson = new Gson();

    public List<UserBean> getFriendsList(){
        return friendDao.getFriend();
    }
    public boolean markFriend(UserBean userBean){return friendDao.markFriend(userBean );}
    public boolean shieldfriend(UserBean userBean){return friendDao.shieldfriend(userBean);}
    public boolean delfriend(UserBean userBean){return friendDao.delfriend(userBean);}
    public String selectFriend(){
        friendDao = new FriendImpl();
        List<UserBean> articleBean = friendDao.selectFriend();
        return gson.toJson(articleBean);
    }
}
