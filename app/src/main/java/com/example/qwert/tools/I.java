package com.example.qwert.tools;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.example.qwert.frame.MainActivity;

/**
 * 页面的封装类
 */
public class I {


    public I() {

    }

    public static void goMain(Context context) {
        context.startActivity(new Intent(context.getApplicationContext(), MainActivity.class));
    }

}
