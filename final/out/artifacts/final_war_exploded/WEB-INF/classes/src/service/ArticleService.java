package service;

import bean.ArticleBean;
import bean.LifeBean;
import dao.ArticleDao;
import dao.impl.ArticleImpl;
import utils.Util;
import com.google.gson.Gson;
import java.util.List;

/**
 * @author caijy@goktech.cn
 * @2019/6/2 10:06
 * describe
 */
public class ArticleService {
    //文章类dao
    private static ArticleDao articleDao = new ArticleImpl();
    private ArticleBean articleBean;
    private ArticleDao ArticleDao;
    private Gson gson = new Gson();
    /**
     * 获取所有文章
     * @return
     */
    public List<ArticleBean>  getAllArticle(){
        return articleDao.getAllArticle();
    }

    public Boolean DelArticle(ArticleBean ariBean){
        ArticleDao  = new ArticleImpl();
        return articleDao.DelArticle(ariBean);
    };
    public Boolean AddArticle(ArticleBean articleBean) {

        //创建ID
        articleBean.setId(Util.createID());

        //当前评论数
        articleBean.setNum(0);
        articleBean.setDate(Util.createNowDate());
        return articleDao.AddArticle(articleBean);

    }
    public List<ArticleBean> getNewArticle(ArticleBean articleBean){return articleDao.getNewArticle(articleBean);}

    public List<LifeBean> getLifeEssay() {
        return articleDao.getLifeEssay();
    }
    public Boolean AddLifeEssay(LifeBean lifeBean){
        lifeBean.setId(Util.createID());
        lifeBean.setDate(Util.createNowDate());
        return articleDao.AddLifeEssay(lifeBean);
    }
    public LifeBean getNewLifeEssay(LifeBean lifeBean){
        return  articleDao.getNewLifeEssay(lifeBean);
    }
   public boolean delEassy(LifeBean lifeBean){
        return articleDao.delEassy(lifeBean);
   }

    public List<ArticleBean>  getarticlebyid(String articleId){
        return articleDao.getarticlebyid( articleId);
    }
    public String getHotComment(){
        articleDao = new ArticleImpl();
        List<ArticleBean> articleBean = articleDao.getHotComment();
        return gson.toJson(articleBean);
    }
    public String getHotA(ArticleBean articleBean){
        articleDao = new ArticleImpl();
        articleBean.setDate(Util.createNowDate());
        List<ArticleBean> articleBeanList = articleDao.getNewArticle (articleBean);
        return gson.toJson(articleBeanList);
    }
}
