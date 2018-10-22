package com.example.qwert.app.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtils {
    /**
     * 状态栏设置
     */
    public static void initStatusBar(Activity context, boolean isTint) {
        initStatusBar(context, false, false);
    }

    public static void initStatusBar(Activity context, boolean isTint, boolean isDark) {
        initStatusBar(context, isTint, isDark, false);
    }

    public static void initStatusBar(Activity context, boolean isTint, boolean isDark, boolean isTransparent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;
        Window window = context.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int flag = window.getDecorView().getWindowSystemUiVisibility();
        // 沉浸式
        if (isTint) {
            flag |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        } else {
            flag |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        if (isDark) {
            flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            flag |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }
        window.getDecorView().setSystemUiVisibility(flag);
        // 背景颜色
        if (isTransparent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

    public static int StatusBarHeight;

    public static void initStatusBarHeight(Context context) {
        StatusBarHeight = setStatusBarHeight(context);

    }

    /**
     * 状态栏高度
     *
     * @param context
     * @return
     */
    public static int setStatusBarHeight(Context context) {
        int result = 0;
        int resultId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (result < resultId) {
            result = context.getResources().getDimensionPixelOffset(resultId);
        }
        return result;
    }

    /**
     * 全屏設置狀態欄高度0
     *
     * @param views
     */
    public static void fitsStatusBarHeight(View... views) {
        for (int i = 0; i < views.length; i++) {
            ViewGroup.LayoutParams layoutParams = views[i].getLayoutParams();
            layoutParams.height = StatusBarHeight;
        }
    }

//    public static void FullScreen(Activity context) {
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
//
//    public static void NotFullScreen(Activity context) {
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//    }
}
