package com.xs.tweet;

import com.xs.tweet.bean.UserBean;
import com.xs.tweet.net.TweetRetrofitUtil;
import com.xs.tweet.net.apiservices.TweetServices;

import org.junit.Test;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    //test api
    @Test
    public void getUserInfo(){
        TweetServices tweetServices = TweetRetrofitUtil.getInstance().getApiServices(TweetServices.class);
        tweetServices.getUser().observeOn(Schedulers.io())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean value) {
                        System.out.println("success:" + value.getNick());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}