package com.xs.tweet.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xs.tweet.R;
import com.xs.tweet.bean.CommentBean;
import com.xs.tweet.bean.TweetBean;
import com.xs.tweet.utils.ImageLoaderUtil;

import org.w3c.dom.Text;

import java.util.List;

import agency.tango.android.avatarview.IImageLoader;
import agency.tango.android.avatarviewglide.GlideLoader;

/**
 * 作者：xq on 2018/8/30 16:30
 * 邮箱：08xq@163.com
 */
public class TweetListAdapter extends RecyclerView.Adapter<TweetListHolder> {

    private final int MAX_SIZE_SHOW = 9;
    private List<TweetBean> tweetBeanList;
    private IImageLoader imageLoader;



    public void add(List<TweetBean> tweetBeans){
        if(tweetBeans == null){
            return;
        }
        tweetBeanList.addAll(tweetBeans);
        notifyDataSetChanged();
    }

    public void setTweetBeanList(List<TweetBean> tweetBeans){
        tweetBeanList = tweetBeans;
        notifyDataSetChanged();
    }

    @Override
    public TweetListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_tweetlist_nine_grid,parent,false);
        return new TweetListHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetListHolder holder, int position) {
        TweetBean tweetBean = getItem(position);
        if(getItem(position).getImages() != null){
            holder.getNineGridLayout().setIsShowAll(getItem(position).getImages().size() <= MAX_SIZE_SHOW);
            holder.getNineGridLayout().setUrlList(getItem(position).getImagesUrl());
            holder.getNineGridLayout().setVisibility(View.VISIBLE);
        }else{
            holder.getNineGridLayout().setVisibility(View.GONE);
        }

        // show content
        if(tweetBean.getContent() != null){
            holder.getContextTv().setText(tweetBean.getContent());
            holder.getContextTv().setVisibility(View.VISIBLE);
        }else{
            holder.getContextTv().setVisibility(View.GONE);
        }


        //show images
        if(tweetBean.getSender() != null){
            holder.getUserNameTv().setText(tweetBean.getSender().getNick());
//            ImageLoaderUtil.loddAvatartWithGlid();
//            if(imageLoader == null){
//                imageLoader = new GlideLoader();
//            }
            ImageLoaderUtil.loddAvatartWithGlid(holder.getIconIv(),tweetBean.getSender().getAvatar(),tweetBean.getSender().getNick());
        }

        //show comments
        if(tweetBean.getComments() != null && tweetBean.getComments().size() > 0){
            holder.getCommtentTv().setText(getComments(tweetBean.getComments()));
            holder.getCommtentTv().setVisibility(View.VISIBLE);
        }else{
            holder.getCommtentTv().setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return tweetBeanList == null ? 0 : tweetBeanList.size();
    }

    public TweetBean getItem(int position){
        return tweetBeanList == null ? null : tweetBeanList.get(position);
    }

    private Spanned getComments(List<CommentBean> commentBeans){
        StringBuilder stringBuilder = new StringBuilder();

        int i=0;
        for(CommentBean commentBean : commentBeans){

            if(commentBean == null || TextUtils.isEmpty(commentBean.getContent()) || commentBean.getSender() == null || TextUtils.isEmpty(commentBean.getSender().getNick())){
                continue;
            }
            if(i<(commentBeans.size() - 1)){
                stringBuilder.append(String.format("<font color=\"#0099cc\">%s:</font><font color=\"#666666\">%s</font><br/>",commentBean.getSender().getNick(),commentBean.getContent()));
            }else{
                stringBuilder.append(String.format("<font color=\"#0099cc\">%s:</font><font color=\"#666666\">%s</font>",commentBean.getSender().getNick(),commentBean.getContent()));
            }
            i++;

        }

        return Html.fromHtml(stringBuilder.toString());
    }
}
