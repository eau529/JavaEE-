package dao;

import bean.ArticleBean;
import bean.CommentBean;
import bean.LifeBean;
import java.util.Date;
import java.util.List;

/**
 * @author caijy@goktech.cn
 * @2019/6/2 9:57
 * describe
 */
public interface ArticleDao {
    //查询所有文章
    public List<ArticleBean> getAllArticle();

    public boolean DelArticle(ArticleBean articleBean);

    //文章添加
    public boolean AddArticle(ArticleBean articleBean);

    //查询最新文章
    public List<ArticleBean> getNewArticle(ArticleBean articleBean);
    //获取所有随笔
    public List<LifeBean> getLifeEssay();
    //添加随笔
    public boolean AddLifeEssay(LifeBean lifeBean);
    //查询随笔
    public LifeBean getNewLifeEssay(LifeBean lifeBean);
   //删除随笔
    public boolean delEassy(LifeBean lifeBean);

    public List<ArticleBean> getarticlebyid(String articleId);

    public List<ArticleBean> getHotComment();


}
