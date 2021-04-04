package dao;

import bean.CommentBean;

import java.util.List;

public interface CommentDao {
    public List<CommentBean> getComment(String id);
    public Boolean NewComment(CommentBean commentBean);
}
