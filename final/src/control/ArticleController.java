package control;

import bean.ArticleBean;
import bean.CommentBean;
import bean.LifeBean;
import bean.UserBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ArticleService;
import service.UserInfoService;
import utils.DBUtil;
import utils.FileUpLoadUtil;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import java.io.PrintWriter;
/**
 * @author caijy@goktech.cn
 * @2019/6/2 9:51
 * describe
 */

public class ArticleController extends HttpServlet {
    //文章类服务
    private static       ArticleService articleService      = new ArticleService();
    //查询所有文章action
    private static final String         getAllArticleAction = "getAllArticle";
    private static final String         delArticle          = "delArticle";
    private static final String         AddNewArticle       = "AddNewArticle";
    private static final String         getNewArticle       = "getNewArticle";
    private static final String         getLifeEssay        = "getLifeEssay";
    private static final String         AddLifeEssay        = "AddLifeEssay";
    private static final String         delEassy            = "delEassy";
    private static final String         getarticlebyid      = "getarticlebyid";
    private static final String         getHotComment       = "getHotComment";
    private static final String         getHotA       = "getHotA";
    FileUpLoadUtil    file = new FileUpLoadUtil();
    ArticleController article;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getParameter("action");
        String lifeText = req.getParameter("lifetext");
        if (getAllArticleAction.equals(action)) {
            getAllArticle(req, resp);
        } else if (delArticle.equals(action)) {
            delArticle(req, resp);
        } else if (AddNewArticle.equals(action)) {
            writeArticle(req, resp);
        } else if (getNewArticle.equals(action)) {
            getNewArticle(req, resp);
        } else if (getLifeEssay.equals(action)) {
            getLifeEssay(req, resp);
        } else if (AddLifeEssay.equals(action)) {
            AddLifeEssay(req, resp);
        } else if (delEassy.equals(action)) {
            delEassy(req, resp);
        } else if (getarticlebyid.equals(action)) {
            getarticlebyid(req, resp);
        } else if (getHotComment.equals(action)) {
            getHotArticle(req, resp);
        }else if (getHotA.equals ( action )){
            getHotA(req,resp);
        }
    }

    /**
     * 查询所有文章
     */
    public void getAllArticle(HttpServletRequest req, HttpServletResponse res) {
        String edit = "0";
        List<ArticleBean> articleList = articleService.getAllArticle();
        req.setAttribute("articleList", articleList);
        req.setAttribute("edit", edit);
        try {
            req.getRequestDispatcher("writeArticle.jsp").forward(req, res);
        } catch (ServletException e) {
            System.out.println("转发界面失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delArticle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArticleBean articleBean = new ArticleBean();
        String id = req.getParameter("articleid");
        articleBean.setId(id);
        boolean value = articleService.DelArticle(articleBean);
        if (value) {
            resp.sendRedirect("ArticleController?action=getAllArticle");
        } else {
            resp.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }

    }

    public void writeArticle(HttpServletRequest req, HttpServletResponse resp) {
        ArticleBean bean = new ArticleBean();
        UserBean userBean = (UserBean) req.getSession().getAttribute("user"); //当前名字
        try {
            bean = file.fileUpLoad(req, getServletContext().getRealPath("/upload")); //存入数据
        } catch (Exception e) {
            e.printStackTrace();
        }

        bean.setAuthor(userBean.getName());
        boolean value = articleService.AddArticle(bean);
        if (value) {
            getAllArticle(req, resp);
        } else {
            try {
                resp.getWriter().print("<script>"
                        + "alert('修改失败！请重试');"
                        + "window.history.go(-1);"
                        + "</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void getNewArticle(HttpServletRequest req, HttpServletResponse res) {

        ArticleBean articleBean = new ArticleBean();
        articleBean.setDate(Util.createNowDate());
        List<ArticleBean> NewArticleList = articleService.getNewArticle(articleBean);
        req.setAttribute("NewArticleList", NewArticleList);
        try {
            req.getRequestDispatcher("writeArticle.jsp").forward(req, res);
        } catch (ServletException e) {
            System.out.println("转发界面失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLifeEssay(HttpServletRequest req, HttpServletResponse res) {
        List<LifeBean> lifeBeans = articleService.getLifeEssay();
        req.setAttribute("lifeBeans", lifeBeans);
        try {
            req.getRequestDispatcher("life.jsp").forward(req, res);
        } catch (ServletException e) {
            System.out.println("转发界面失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void AddLifeEssay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        articleService = new ArticleService();
        LifeBean lifeBean = new LifeBean();
        String body = req.getParameter("lifetext");
        lifeBean.setBody(body);
        boolean value = articleService.AddLifeEssay(lifeBean);
        LifeBean lifeBeans = articleService.getNewLifeEssay(lifeBean);
        if (value) {
            Gson gson = new Gson();
            String userLife = gson.toJson(lifeBeans);
            PrintWriter out;
            try {
                out = resp.getWriter();
                out.print(userLife);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                resp.getWriter().print("<script>"
                        + "alert('添加失败！请重试');"
                        + "window.history.go(-1);"
                        + "</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delEassy(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LifeBean lifeBean = new LifeBean();
        String id = req.getParameter("articleid");
        lifeBean.setId(id);
        boolean value = articleService.delEassy(lifeBean);
        if (value) {
            try {
                req.getRequestDispatcher("ArticleController?action=getLifeEssay").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } else {
            resp.getWriter().print("<script>"
                    + "alert('修改失败！请重试');"
                    + "window.history.go(-1);"
                    + "</script>");
        }

    }

    public void getarticlebyid(HttpServletRequest req, HttpServletResponse res) {
        ArticleBean articleBean = new ArticleBean();
        String articleId = req.getParameter("articleid");
        List<ArticleBean> articleList = articleService.getarticlebyid(articleId);
        req.setAttribute("articleList", articleList);
        try {
            req.getRequestDispatcher("article.jsp").forward(req, res);
        } catch (ServletException e) {
            System.out.println("转发界面失败" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getHotArticle(HttpServletRequest req, HttpServletResponse res) {
        articleService = new ArticleService();
        String commentList = articleService.getHotComment();
        try {
            res.getWriter().print(commentList);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
    public void getHotA(HttpServletRequest req, HttpServletResponse res) {
        articleService = new ArticleService();
        ArticleBean articleBean=new ArticleBean ();
        String aritcles = articleService.getHotA (articleBean);
        try {
            res.getWriter().print(aritcles);
        } catch (IOException e) {
            System.out.println("给与前端响应报错" + e.getMessage());
        }
    }
}
