package com.xs.tweet.database.dao;

import android.arch.persistence.room.Dao;

import com.xs.tweet.bean.TweetBean;

import java.util.List;

/**
 * 作者：xq on 2018/8/29 17:51
 * 邮箱：08xq@163.com
 */
@Dao
public interface TweetDao {

    List<TweetBean> getAll();
    TweetBean getById(int id);
}
