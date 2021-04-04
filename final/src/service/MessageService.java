package service;

import bean.CommentBean;
import bean.MessageBean;
import com.google.gson.Gson;
import dao.MessageDao;
import dao.impl.CommentImpl;
import dao.impl.MessageImpl;
import utils.Util;

import java.util.List;

public class MessageService {
    private MessageDao messageDao;
    private Gson       gson = new Gson();

    public String getMessage(){
        messageDao= new MessageImpl();
        List<MessageBean> message = messageDao.getMessage();
        return gson.toJson(message);
    }
    public String updateMessage(MessageBean messageBean){
        messageDao= new MessageImpl();
        messageBean.setId ( Util.createID () );
        messageBean.setDate(Util.createNowDate());
        messageDao.updateMessage(messageBean);
        return gson.toJson(messageBean);
    }
    public String updateReply(MessageBean messageBean){
        messageDao= new MessageImpl();
        messageDao.updateReply (messageBean);
        return gson.toJson(messageBean);
    }

}
