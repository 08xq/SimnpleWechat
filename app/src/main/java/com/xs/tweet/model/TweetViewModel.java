package com.xs.tweet.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.xs.tweet.Repository.TweeterRespository;
import com.xs.tweet.bean.TweetBean;
import com.xs.tweet.bean.UserBean;

import java.util.List;

/**
 * view model
 * 作者：xq on 2018/8/29 18:53
 * 邮箱：08xq@163.com
 */
public class TweetViewModel extends ViewModel {
    private static Object LOCK = new Object();
    private static TweetViewModel INSTANCE;

    private TweeterRespository mTweeterResponsitory;
    public static TweetViewModel getInstance(){
        synchronized (LOCK){
            if(INSTANCE == null){
                INSTANCE = new TweetViewModel();
            }
            return INSTANCE;
        }
    }

    public TweetViewModel(){
        mTweeterResponsitory =  TweeterRespository.getInstance();
    }

    public MutableLiveData<UserBean> getUser(){
        return mTweeterResponsitory.getUser();
    }

    public MutableLiveData<List<TweetBean>> getTweetList(){
        return mTweeterResponsitory.getTweetList();
    }

    public MutableLiveData<List<TweetBean>> getTweetList(int page){
        return mTweeterResponsitory.getTweetList(page);
    }


}
