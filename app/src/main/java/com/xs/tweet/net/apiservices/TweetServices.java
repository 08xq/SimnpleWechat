package com.xs.tweet.net.apiservices;

import com.xs.tweet.bean.TweetBean;
import com.xs.tweet.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：xq on 2018/8/29 20:05
 * 邮箱：08xq@163.com
 */
public interface TweetServices {


    @GET("user/jsmith")
    Observable<UserBean> getUser();

    @GET("user/jsmith/tweets")
    Observable<List<TweetBean>> getTweetList();
}
