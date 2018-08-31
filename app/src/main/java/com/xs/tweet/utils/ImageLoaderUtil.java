package com.xs.tweet.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.xs.tweet.R;

import agency.tango.android.avatarview.views.AvatarView;
import agency.tango.android.avatarviewglide.GlideLoader;

/**
 * 作者：xq on 2018/8/30 16:22
 * 邮箱：08xq@163.com
 */
public class ImageLoaderUtil {
    private static GlideLoader imageLoader;

    public static ImageLoader getImageLoader(Context context) {
        if(!ImageLoader.getInstance().isInited()){

            ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(context);
            ImageLoader.getInstance().init(configuration);
        }
        return ImageLoader.getInstance();
    }

    public static DisplayImageOptions getPhotoImageOption() {
        Integer extra = 1;
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .showImageForEmptyUri(R.mipmap.banner_default).showImageOnFail(R.mipmap.banner_default)
                .showImageOnLoading(R.mipmap.banner_default)
                .extraForDownloader(extra)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    public static void displayImage(Context context, ImageView imageView, String url, DisplayImageOptions options) {
        getImageLoader(context).displayImage(url, imageView, options);
    }

    public static void displayImage(Context context, ImageView imageView, String url, DisplayImageOptions options, ImageLoadingListener listener) {
        getImageLoader(context).displayImage(url, imageView, options, listener);
    }

    public static void loadImageWithGlid(Context context, ImageView imageView, String url){
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    public static void loddAvatartWithGlid(AvatarView avatarView, String avatarUrl, String name){
        if(imageLoader == null){
            imageLoader = new GlideLoader();
        }

        imageLoader.loadImage(avatarView,avatarUrl,name);
    }
}
