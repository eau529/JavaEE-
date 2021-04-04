package dao.impl;

import bean.CommentBean;
import bean.UserInfoBean;
import dao.CommentDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentImpl implements CommentDao {

    private DBUtil       dbUtil = DBUtil.newInstance();

    @Override
    public List<CommentBean> getComment(String id) {
        String sql = "select * from article_comment  where article_id = ? ORDER BY date DESC";
        ResultSet rs = dbUtil.dataQuery(sql,id);
        CommentBean commentBean;
        List<CommentBean> commentBeanList=new ArrayList<CommentBean>();
        try {
            while(rs.next()){
                commentBean = new CommentBean();
                commentBean.setUser_name(rs.getString("user_name"));
                commentBean.setArticle_id(rs.getString("article_id"));
                commentBean.setBody(rs.getString("body"));
                commentBean.setDate(rs.getDate("date"));
                commentBean.setId(rs.getString("id"));
                commentBeanList.add(commentBean);
            }
        } catch (SQLException e) {
            System.out.println("数据库查询异常"+e.getMessage());
        }
        return commentBeanList;
    }

    @Override
    public Boolean NewComment(CommentBean commentBean) {
        String sql = "insert into article_comment (id,article_id,body,date,user_name) values(?,?,?,?,?)";
        boolean rs = dbUtil.dataUpdate(sql,commentBean.getId(),commentBean.getArticle_id(),commentBean.getBody()
        ,commentBean.getDate(),commentBean.getUser_name());
        return rs;
    }
}
