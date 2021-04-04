package control;

import bean.UserInfoBean;
import service.UserInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author caijy@goktech.cn
 * @2019/5/31 19:13
 * describe
 */
public class UserInfoController extends HttpServlet {
    //获取用户信息action
    private String getUserInfoAction = "getUserInfo";
    //修改用户信息action
    private String updateUserInfoAction = "updateUserInfo";
    //获取用户信息服务
    private UserInfoService userInfoService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");
        if (getUserInfoAction.equals(action)) {
            getUserInfo(resp, userId);
        }else if (updateUserInfoAction.equals(action)){
            updateUserInfoAciton(req,resp);
        }
    }

    //获取用户信息
    public void getUserInfo(HttpServletResponse res, String userId) {
        userInfoService = new UserInfoService();
        String userInfo = userInfoService.getUserInfo(userId);
        try {
            res.getWriter().print(userInfo);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }

    //
    public void updateUserInfoAciton(HttpServletRequest req, HttpServletResponse res) throws IOException {
        UserInfoBean me = new UserInfoBean();
        HttpSession se = req.getSession();
        //获取修改的信息
        String info = req.getParameter("info");
        String hobby = req.getParameter("hobby");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");
        String description = req.getParameter("description");
        String id = "11";
        String userId = se.getAttribute("id").toString();

        me.setId(id);
        me.setUserId(userId);
        me.setInfo(info);
        me.setHobby(hobby);
        me.setQq(qq);
        me.setEmail(email);
        me.setDescription(description);
        boolean value = userInfoService.UpdateUserInfo(me);
        if (value) {
            res.sendRedirect("aboutMe.jsp");
        } else {
            res.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }

    }
}
