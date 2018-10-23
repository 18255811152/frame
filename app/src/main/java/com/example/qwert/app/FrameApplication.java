package com.example.qwert.app;

import android.app.Application;

import com.example.qwert.app.utils.CrashHandler;
import com.example.qwert.tools.L;
import com.example.qwert.tools.T;
import com.lzy.imagepicker.ImagePicker;

public class FrameApplication extends Application {
    public static ImagePicker imagePicker;


    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler.getInstance().init(this);
        /**
         * 初始化T
         */
        T.init(FrameApplication.this);
        L.setTag("FrameApplication");
        L.setDebug(false);

    }
}
