package com.xs.tweet;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 作者：xq on 2018/8/29 16:59
 * 邮箱：08xq@163.com
 */
public class TweetApplication extends Application {

    private Context instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }

    public Context getContext(){
        return instance;
    }
}
