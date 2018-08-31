package com.xs.tweet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xs.tweet.R;
import com.xs.tweet.ui.widget.NineGridLayout;

import agency.tango.android.avatarview.views.AvatarView;

/**
 * 作者：xq on 2018/8/30 16:36
 * 邮箱：08xq@163.com
 */
public class TweetListHolder extends RecyclerView.ViewHolder {
    private NineGridLayout mNineGridLayout;
    private TextView userNameTv;
    private AvatarView iconIv;
    private TextView contextTv;
    private TextView commtentTv;
    public TweetListHolder(View itemView) {
        super(itemView);

        mNineGridLayout = itemView.findViewById(R.id.layout_nine_grid);
        userNameTv = itemView.findViewById(R.id.user_name);
        iconIv = itemView.findViewById(R.id.user_icon);
        contextTv = itemView.findViewById(R.id.user_content);
        commtentTv = itemView.findViewById(R.id.user_comment);
    }

    public NineGridLayout getNineGridLayout(){
        return  mNineGridLayout;
    }

    public AvatarView getIconIv(){
        return iconIv;
    }

    public TextView getUserNameTv(){
        return userNameTv;
    }

    public TextView getContextTv(){
        return contextTv;
    }

    public TextView getCommtentTv(){
        return commtentTv;
    }

}
