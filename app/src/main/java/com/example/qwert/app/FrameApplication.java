package com.example.qwert.app;

import android.app.Application;

import com.example.qwert.tools.I;
import com.example.qwert.tools.L;
import com.example.qwert.tools.T;

public class FrameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化T
         */
        T.init(FrameApplication.this);
        L.setTag("FrameApplication");
        L.setDebug(false);
    }
}
