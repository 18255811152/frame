package com.example.qwert.app.utils;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.qwert.frame.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestManager glideRequest = Glide.with(activity);
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .error(R.drawable.ic_default_image)           //设置错误图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .placeholder(R.drawable.ic_default_image);
        glideRequest.load(Uri.fromFile(new File(path))).apply(options).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestManager glideRequest = Glide.with(activity);
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);//缓存全尺寸;
        glideRequest.load(Uri.fromFile(new File(path))).apply(options).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
