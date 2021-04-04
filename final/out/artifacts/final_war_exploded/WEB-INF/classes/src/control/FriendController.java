package control;


import bean.UserBean;
import service.ArticleService;
import service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class FriendController extends HttpServlet {
    private static final String getFriend = "getFriend";
    private static final String markfriend = "markfriend";
    private static final String shieldfriend = "shieldfriend";
    private static final String delfriend = "delfriend";
    private static final String selectFriend = "selectFriend";
    //User类服务
    private static FriendService friendService = new FriendService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if (getFriend.equals(action)) {
            getFriend(req, resp);
        }else if (markfriend.equals(action)){
            markfriend(req,resp);
        }else if (shieldfriend.equals(action)){
            shieldfriend(req, resp);
        }else if (delfriend.equals(action)){
            delfriend(req, resp);
        }else if (selectFriend.equals(action)){
            selectFriend(req,resp);
        }

    }

    /**
     * 查询所有文章
     */
    public void getFriend(HttpServletRequest req, HttpServletResponse res) {
        List<UserBean> friendList = friendService.getFriendsList();
        req.setAttribute("friendList", friendList);
        try {
            req.getRequestDispatcher("friends.jsp").forward(req, res);
        } catch (ServletException e) {
            System.out.println("转发界面失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void markfriend(HttpServletRequest req, HttpServletResponse res) throws IOException{
        UserBean userBean=new UserBean();
        String userid=req.getParameter("userid");
        int stranger = Integer.parseInt(req.getParameter("flag"));
        userBean.setId(userid);
        userBean.setStranger(stranger);
        boolean flag=friendService.markFriend(userBean);
        if (flag){
            res.sendRedirect("FriendController?action=getFriend");
        }else {
            res.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }
    }
    public void shieldfriend(HttpServletRequest req, HttpServletResponse res) throws IOException{
        UserBean userBean=new UserBean();
        String userid=req.getParameter("userid");
        int stranger = Integer.parseInt(req.getParameter("flag"));
        userBean.setId(userid);
        userBean.setStranger(stranger);
        boolean flag=friendService.shieldfriend(userBean);
        if (flag){
            res.sendRedirect("FriendController?action=getFriend");
        }else {
            res.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }
    }
    public void delfriend(HttpServletRequest req, HttpServletResponse res) throws IOException{
        UserBean userBean=new UserBean();
        String userid=req.getParameter("userid");
        userBean.setId(userid);
        boolean flag=friendService.delfriend(userBean);
        if (flag){
            res.sendRedirect("FriendController?action=getFriend");
        }else {
            res.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }
    }
    public void selectFriend(HttpServletRequest req, HttpServletResponse res) {
        friendService = new FriendService();
        String FriendList = friendService.selectFriend();
        try {
            res.getWriter().print(FriendList);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
}