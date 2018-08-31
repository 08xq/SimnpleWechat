package com.xs.tweet.net;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：xq on 2018/8/29 19:48
 * 邮箱：08xq@163.com
 */
public class TweetRetrofitUtil {

    protected static TweetRetrofitUtil mRetrofitUtil;
    private Retrofit mRetrofit;

    public static TweetRetrofitUtil getInstance(){
        synchronized (TweetRetrofitUtil.class){
            if(mRetrofitUtil == null){
                mRetrofitUtil = new TweetRetrofitUtil();
            }

            return mRetrofitUtil;
        }
    }

    public TweetRetrofitUtil(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676,TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }

    public <T> T getApiServices(Class<T> services){
        return mRetrofit.create(services);
    }

    private String getBaseUrl(){
        return "http://thoughtworks-ios.herokuapp.com/";
    }

}
