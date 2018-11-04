package com.example.qwert.tools;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.qwert.frame.MainActivity;
import com.example.qwert.view.activity.RefreshActivity;
import com.example.qwert.view.activity.WeiXinActivity;

/**
 * 页面的封装类
 */
public class I {


    public I() {

    }


    public static void goMain(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
    }

    public static void goWeiXinActivity(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), WeiXinActivity.class));
    }

    public static void gotoRefresh(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), RefreshActivity.class));
    }
}
