package dao.impl;

import bean.ArticleBean;
import bean.CommentBean;
import bean.LifeBean;
import dao.ArticleDao;
import utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author caijy@goktech.cn
 * @2019/6/2 9:58
 * describe
 */
public class ArticleImpl  implements ArticleDao {
    //数据库连接工具类
    private DBUtil dbUtil = DBUtil.newInstance();
    //文章类bean
    private ArticleBean articleBean;
    private LifeBean lifeBean;
    @Override
    public List<ArticleBean> getAllArticle() {
        String sql = "select * from article ORDER BY date DESC";
        ResultSet rs = dbUtil.dataQuery(sql);
        List<ArticleBean> beanList = new ArrayList<ArticleBean>();
         try {
            while(rs.next()){
                articleBean = new ArticleBean();
                articleBean.setId(rs.getString("id"));
                articleBean.setTitle(rs.getString("title"));
                articleBean.setBody(rs.getString("body"));
                articleBean.setNum(Integer.parseInt(rs.getString("num")));
                articleBean.setImage(rs.getString("image"));
                articleBean.setDate(rs.getDate("date"));
                articleBean.setAuthor(rs.getString("author"));
                beanList.add(articleBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }
    public boolean DelArticle(ArticleBean articleBean) {
        String sql = "DELETE FROM article WHERE id=?";
        boolean b1=dbUtil.dataUpdate(sql,articleBean.getId() );
        return b1;
    }
    public boolean AddArticle(ArticleBean articleBean) {
        String sql="insert into article (id,title,body,num,image,date,author) values(?,?,?,?,?,?,?)";
        boolean b1=dbUtil.dataUpdate(sql, articleBean.getId(),articleBean.getTitle(),articleBean.getBody(),
                articleBean.getNum(),articleBean.getImage(),articleBean.getDate(),articleBean.getAuthor());
        return b1;
    }
    public List<ArticleBean> getNewArticle(ArticleBean articleBean) {
        String sql = "SELECT * FROM article where DATE_SUB(?, INTERVAL 7 DAY) <= date(date) ORDER BY date DESC;";
        ResultSet rs = dbUtil.dataQuery(sql,articleBean.getDate());
        List<ArticleBean> beanList = new ArrayList<ArticleBean>();
        try {
            while(rs.next()){
                articleBean = new ArticleBean();
                articleBean.setId(rs.getString("id"));
                articleBean.setTitle(rs.getString("title"));
                articleBean.setBody(rs.getString("body"));
                articleBean.setNum(Integer.parseInt(rs.getString("num")));
                articleBean.setImage(rs.getString("image"));
                articleBean.setDate(rs.getDate("date"));
                articleBean.setAuthor(rs.getString("author"));
                beanList.add(articleBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    @Override
    public List<LifeBean> getLifeEssay() {
        String sql = "SELECT * FROM life ORDER BY date DESC";
        ResultSet rs = dbUtil.dataQuery(sql);

        List<LifeBean> liftList = new ArrayList<LifeBean>();
        try {
            while(rs.next()){
                lifeBean = new LifeBean();
                lifeBean.setId(rs.getString("id"));
                lifeBean.setBody(rs.getString("body"));
                lifeBean.setDate(rs.getDate("date"));
                liftList.add(lifeBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(liftList);
        return liftList;
    }

    @Override
    public boolean AddLifeEssay(LifeBean lifeBean) {
        String sql="insert into life (id,body,date) values(?,?,?)";
        boolean b1=dbUtil.dataUpdate(sql, lifeBean.getId(),lifeBean.getBody(),lifeBean.getDate());
        return b1;
    }

    @Override
    public LifeBean getNewLifeEssay(LifeBean lifeBean) {
        String sql = "SELECT * from life WHERE id=?";
        ResultSet rs = dbUtil.dataQuery(sql,lifeBean.getId());

        List<LifeBean> liftList = new ArrayList<LifeBean>();
        try {
            while(rs.next()){
                lifeBean = new LifeBean();
                lifeBean.setId(rs.getString("id"));
                lifeBean.setBody(rs.getString("body"));
                lifeBean.setDate(rs.getDate("date"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lifeBean;
    }

    @Override
    public boolean delEassy(LifeBean lifeBean) {
        String sql = "DELETE FROM life WHERE id=?";
        boolean b1=dbUtil.dataUpdate(sql,lifeBean.getId() );
        return b1;
    }


    public List<ArticleBean> getarticlebyid(String articleId) {
        ArticleBean articleBean;
        String sql = "select * from article WHERE  id=?";
        ResultSet rs = dbUtil.dataQuery(sql,articleId);
        List<ArticleBean> beanList = new ArrayList<ArticleBean>();
        try {
            while(rs.next()){
                articleBean = new ArticleBean();
                articleBean.setId(rs.getString("id"));
                articleBean.setTitle(rs.getString("title"));
                articleBean.setBody(rs.getString("body"));
                articleBean.setNum(Integer.parseInt(rs.getString("num")));
                articleBean.setImage(rs.getString("image"));
                articleBean.setDate(rs.getDate("date"));
                articleBean.setAuthor(rs.getString("author"));
                beanList.add(articleBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }

    @Override
    public List<ArticleBean> getHotComment() {
        String sql = "SELECT  *  FROM  article ORDER BY num DESC LIMIT  5 ";
        ResultSet rs = dbUtil.dataQuery(sql);
        List<ArticleBean> beanList = new ArrayList<ArticleBean>();
        try {
            while(rs.next()){
                articleBean = new ArticleBean();
                articleBean.setId(rs.getString("id"));
                articleBean.setTitle(rs.getString("title"));
                articleBean.setBody(rs.getString("body"));
                articleBean.setNum(Integer.parseInt(rs.getString("num")));
                articleBean.setImage(rs.getString("image"));
                articleBean.setDate(rs.getDate("date"));
                articleBean.setAuthor(rs.getString("author"));
                beanList.add(articleBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beanList;
    }


}

