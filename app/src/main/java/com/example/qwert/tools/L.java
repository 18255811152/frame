package com.example.qwert.tools;

import android.util.Log;

/**
 * log输出
 * Debug 模式下开启log打印日志
 */
public class L {
    private static String Tag = "frame";
    /**
     * 是否是debug模式
     */
    private static Boolean Debug = true;

    public L() {

    }

    public static void setTag(String TAG) {
        L.Tag = TAG;
    }

    public static void setDebug(boolean Debug) {
        L.Debug = Debug;
    }

    public static void v(String msg) {
        if (Debug) Log.v(Tag, msg);
    }

    public static void w(String msg) {
        if (Debug) Log.w(Tag, msg);
    }

    public static void i(String msg) {
        if (Debug) Log.i(Tag, msg);
    }

    public static void e(String msg) {
        if (Debug) Log.e(Tag, msg);
    }

    public static void d(String msg) {
        if (Debug) Log.d(Tag, msg);
    }
}
