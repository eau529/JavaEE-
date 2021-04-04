package bean;

import java.util.Date;

public class CommentBean {
    private String id;
    private String articleId;
    private String body;
    private Date date;
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticle_id() {
        return articleId;
    }

    public void setArticle_id(String articleId) {
        this.articleId = articleId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser_name() {
        return userName;
    }

    public void setUser_name(String user_name) {
        this.userName = user_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
