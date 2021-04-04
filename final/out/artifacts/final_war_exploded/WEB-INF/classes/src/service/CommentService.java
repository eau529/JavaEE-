package service;

import bean.CommentBean;
import dao.CommentDao;
import dao.impl.CommentImpl;
import com.google.gson.Gson;
import utils.Util;

import java.util.List;


public class CommentService {
    private CommentDao  commentdao;
    private Gson gson = new Gson();

    public String getComment(String id){
        commentdao= new CommentImpl();
        List<CommentBean> commentBeans = commentdao.getComment(id);
        return gson.toJson(commentBeans);
    }
    public String NewComment(CommentBean commentBean){
        commentdao= new CommentImpl();
        commentBean.setId(Util.createID());
        commentBean.setDate(Util.createNowDate());
        commentdao.NewComment(commentBean);
        return gson.toJson(commentBean);
    }
}
