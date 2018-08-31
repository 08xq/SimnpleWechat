package com.xs.tweet.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xs.tweet.R;
import com.xs.tweet.adapter.TweetListAdapter;
import com.xs.tweet.bean.TweetBean;
import com.xs.tweet.bean.UserBean;
import com.xs.tweet.model.TweetViewModel;
import com.xs.tweet.utils.ImageLoaderUtil;

import java.util.List;

import agency.tango.android.avatarview.IImageLoader;
import agency.tango.android.avatarview.views.AvatarView;
import agency.tango.android.avatarviewglide.GlideLoader;

public class TweetListActivity extends AppCompatActivity {

    private TweetViewModel mTweetViewModel;
    private AvatarView userIconIv;
    private TweetListAdapter mAdapter;

    private RecyclerView mRecycleView;

    //refresh layout
    RefreshLayout mRefreshLayout;
    private int pageIndex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //init
        initModel();
        initUi();
        initFloatingBar();

        //get data
        getUser();
        getTweetList();

        //refresh
        bindRefreshListener();
        mRefreshLayout.autoRefresh();
    }

    private void initFloatingBar(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "add tweet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    private void initModel(){
        mTweetViewModel = ViewModelProviders.of(this).get(TweetViewModel.class);
    }

    private void initUi(){
        userIconIv = findViewById(R.id.user_icon);

        mRefreshLayout = findViewById(R.id.refreshLayout);

        mRecycleView = findViewById(R.id.recycleview);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(OrientationHelper.VERTICAL);
        mRecycleView.setLayoutManager(lm);
        mAdapter = new TweetListAdapter();
        mRecycleView.setAdapter(mAdapter);
    }

    private void bindRefreshListener(){
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                mTweetViewModel.getTweetList(pageIndex);
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex = pageIndex + 1;
                mTweetViewModel.getTweetList(pageIndex);
            }
        });
    }


    private void getTweetList(){
        mTweetViewModel.getTweetList(pageIndex).observe(this, new Observer<List<TweetBean>>() {
            @Override
            public void onChanged(final @Nullable List<TweetBean> tweetBeans) {
                if(pageIndex > 1){

                    if(tweetBeans == null || tweetBeans.size() == 0){
                        mRefreshLayout.finishLoadmoreWithNoMoreData();
                        mRefreshLayout.finishLoadmore();
                    }else{
                        mRefreshLayout.finishLoadmore(500);
                    }
                    //纯粹为了模拟加载耗时
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.add(tweetBeans);
                        }
                    },500);
                }else{
                    mRefreshLayout.resetNoMoreData();
                    mAdapter.setTweetBeanList(tweetBeans);
                    mRefreshLayout.finishRefresh(500);
                }

            }
        });
    }

    private void getUser(){
        mTweetViewModel.getUser().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                setUser2Ui(userBean);
            }
        });
    }

    private void setUser2Ui(UserBean user){
        if(user == null){
            return;
        }
        getSupportActionBar().setTitle(user.getNick());
        ImageLoaderUtil.loddAvatartWithGlid(userIconIv,user.getAvatar(),user.getNick());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tweet_list, menu);
        return true;
    }

    //TODO
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
