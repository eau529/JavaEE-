package control;

import javax.servlet.annotation.WebServlet;
import bean.CommentBean;
import bean.LifeBean;
import service.ArticleService;
import service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommentController extends HttpServlet{
    CommentService commentService;

    private static final String getComment = "getComment";
    private static final String NewComment = "NewComment";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if (getComment.equals(action)) {
            getComment(req, resp);
        }else if (NewComment.equals(action)){
            NewComment(req,resp);
        }
    }
    public void getComment(HttpServletRequest req,HttpServletResponse res){
        commentService = new CommentService();
        String id=req.getParameter("articleid");
        String Comments = commentService.getComment(id);
        try {
            res.getWriter().print(Comments);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
    public void NewComment(HttpServletRequest req,HttpServletResponse res){
        commentService = new CommentService();
        String id=req.getParameter("articleid");
        String body=req.getParameter("comment1");
        String userName=req.getParameter("username");
        CommentBean commentBean=new CommentBean();
        commentBean.setArticle_id(id);
        commentBean.setBody(body);
        commentBean.setUser_name(userName);
        String Comments=commentService.NewComment(commentBean);
        try {
            res.getWriter().print(Comments);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }

    }
}
