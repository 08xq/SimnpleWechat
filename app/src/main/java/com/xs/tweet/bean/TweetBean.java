package com.xs.tweet.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xq on 2018/8/29 17:13
 * 邮箱：08xq@163.com
 */
public class TweetBean {
    private String content;
    private List<ImageBean> images;
    private UserBean sender;
    private List<CommentBean> comments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImageBean> getImages() {
        return images;
    }

    public void setImages(List<ImageBean> images) {
        this.images = images;
    }

    public UserBean getSender() {
        return sender;
    }

    public void setSender(UserBean sender) {
        this.sender = sender;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    public List<String> getImagesUrl(){
        List<String> imageList = null;
        if(images != null){
            imageList = new ArrayList<>();
            for(ImageBean imageBean : images){
                if(imageBean == null || TextUtils.isEmpty(imageBean.getUrl())){
                    continue;
                }
                imageList.add(imageBean.getUrl());
            }
        }

        return imageList;
    }
}
