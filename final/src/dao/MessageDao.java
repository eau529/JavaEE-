package dao;

import bean.MessageBean;

import java.util.List;

public interface MessageDao {
    public List<MessageBean> getMessage();
    public boolean updateMessage(MessageBean messageBean);
    public boolean updateReply(MessageBean messageBean);
}
