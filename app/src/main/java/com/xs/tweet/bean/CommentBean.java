package com.xs.tweet.bean;

/**
 * 作者：xq on 2018/8/29 17:11
 * 邮箱：08xq@163.com
 */
public class CommentBean {
    private String content;
    private UserBean sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getSender() {
        return sender;
    }

    public void setSender(UserBean sender) {
        this.sender = sender;
    }
}
