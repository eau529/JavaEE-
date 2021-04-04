package dao.impl;
import bean.CommentBean;
import bean.MessageBean;
import dao.MessageDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageImpl implements MessageDao {
    private DBUtil      dbUtil = DBUtil.newInstance();
    //定义userBean

    public List<MessageBean> getMessage() {
        MessageBean messageBean;
        String sql = "select * from messagelist  ORDER BY date DESC";
        ResultSet rs = dbUtil.dataQuery(sql);
        List<MessageBean> messageBeanList=new ArrayList<MessageBean>();
        try {
            while (rs.next()){
                messageBean = new MessageBean();
                messageBean.setDate(rs.getDate("date"));
                messageBean.setId(rs.getString("id"));
                messageBean.setMessage(rs.getString("message"));
                messageBean.setReply(rs.getString("reply"));
                messageBean.setUserId(rs.getString("userid"));
                messageBean.setUserName(rs.getString("username"));
                messageBeanList.add(messageBean);
            }
        } catch (SQLException e) {
            System.out.println("数据库查询异常"+e.getMessage());
        }
        return messageBeanList;
    }
    @Override
    public boolean updateMessage(MessageBean messageBean) {
        String sql="insert into messagelist (id,USERID,USERNAME,MESSAGE,DATE,REPLY) values(?,?,?,?,?,?)" ;
        boolean flag=dbUtil.dataUpdate(sql,messageBean.getId(),messageBean.getUserId(),messageBean.getUserName()
        ,messageBean.getMessage(),messageBean.getDate(),messageBean.getReply());
        return flag;
    }
    @Override
    public boolean updateReply(MessageBean messageBean) {
        String sql="update messagelist set reply=? where id=?" ;
        boolean flag=dbUtil.dataUpdate(sql,messageBean.getReply(),messageBean.getId());
        return flag;
    }


}
