package control;

import bean.LifeBean;
import bean.MessageBean;
import dao.MessageDao;
import dao.impl.MessageImpl;
import service.CommentService;
import service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MessageController extends HttpServlet {
    MessageService messageService;

    private static final String getMessage = "getMessage";
    private static final String updateMessage="updateMessage";
    private static final String updateReply="updateReply";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if (getMessage.equals(action)) {
            getMessage(req, resp);
        }else if (updateMessage.equals(action)){
            updateMessage(req,resp);
        }else if(updateReply.equals (action)){
            updateReply(req,resp);
        }
    }
    public void getMessage(HttpServletRequest req,HttpServletResponse res){
        messageService = new MessageService();
        String message = messageService.getMessage();
        try {
            res.getWriter().print(message);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
    public void updateMessage(HttpServletRequest req,HttpServletResponse res){
        messageService = new MessageService();
        MessageBean messageBean=new MessageBean();
        messageBean.setUserId(req.getParameter ( "username" ));
        messageBean.setUserName(req.getParameter ( "username" ));
        messageBean.setMessage(req.getParameter ( "MessageText" ));
        String message = messageService.updateMessage(messageBean);
        try {
            res.getWriter().print(message);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
    public void updateReply(HttpServletRequest req,HttpServletResponse res) throws IOException {
        messageService = new MessageService();
        MessageBean messageBean=new MessageBean();
        messageBean.setReply (req.getParameter ( "replytext" ));
        messageBean.setId (req.getParameter ( "id" ));
        String message = messageService.updateReply (messageBean);
        try {
            res.getWriter().print(message);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
}
