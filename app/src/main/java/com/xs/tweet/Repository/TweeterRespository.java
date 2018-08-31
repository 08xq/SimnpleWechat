package com.xs.tweet.Repository;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.xs.tweet.bean.TweetBean;
import com.xs.tweet.bean.UserBean;
import com.xs.tweet.net.TweetRetrofitUtil;
import com.xs.tweet.net.apiservices.TweetServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 数据提供者
 * 作者：xq on 2018/8/29 18:31
 * 邮箱：08xq@163.com
 */
public class TweeterRespository {

    private static TweeterRespository INSTANCE;

    private static Object LOCK = new Object();
    private MutableLiveData<UserBean> liveDataUserBean;
    private MutableLiveData<List<TweetBean>> liveDataTweetBean;
    private TweetServices mTweetServices;

    private List<TweetBean> cacheInMemory = null;

    private final int PAGE_SIZE = 5;

    public static TweeterRespository getInstance(){
        synchronized (LOCK){
            if(INSTANCE == null){
                INSTANCE = new TweeterRespository();
            }
            return INSTANCE;
        }
    }

    public TweeterRespository(){
        liveDataUserBean = new MutableLiveData<>();
        liveDataTweetBean = new MutableLiveData<>();
        mTweetServices = TweetRetrofitUtil.getInstance().getApiServices(TweetServices.class);
    }

    public MutableLiveData<UserBean> getUser(){

        mTweetServices.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserBean value) {
                        if(value != null){
                            liveDataUserBean.setValue(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return liveDataUserBean;
    }

    public MutableLiveData<List<TweetBean>> getTweetList(){
        if(cacheInMemory == null){
            getAllTweetListFromWeb();
        }else{
            liveDataTweetBean.setValue(cacheInMemory);
        }
        return liveDataTweetBean;
    }

    public MutableLiveData<List<TweetBean>> getTweetList(int page){
        if(cacheInMemory == null){
            getAllTweetListFromWeb();
        }else{
            liveDataTweetBean.setValue(getListByPage(page,cacheInMemory));
        }

        return liveDataTweetBean;
    }

    private void getAllTweetListFromWeb(){
        mTweetServices.getTweetList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TweetBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TweetBean> value) {
                        cacheInMemory = formatTweetList(value);
                        liveDataTweetBean.setValue(getListByPage(1,cacheInMemory));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //remove dirty data
    private List<TweetBean> formatTweetList(List<TweetBean> data){
        if(data == null || data.size() == 0){
            return null;
        }

        List<TweetBean> formatData = new ArrayList<>();

        for(TweetBean tweetBean : data){
            if(tweetBean == null ||
                    (TextUtils.isEmpty(tweetBean.getContent()) && (tweetBean.getImages() == null || tweetBean.getImages().size() == 0))){
                continue;
            }
            formatData.add(tweetBean);

        }
        return  formatData;
    }

    //paging
    private List<TweetBean> getListByPage(int page, List<TweetBean> data){
        if(data == null || data.size() == 0){
            return null;
        }

        int currentIndex = page > 1? (page -1) *PAGE_SIZE : 0;
        List<TweetBean> pageList = new ArrayList<>();
        for (int i = 0; i < PAGE_SIZE && i < data.size() - currentIndex; i++) {
            pageList.add(data.get(currentIndex + i));
        }

        return pageList;
    }

}
