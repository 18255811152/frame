package com.example.qwert.presenter;

import android.Manifest;
import android.app.Activity;
import android.os.Handler;

import com.example.qwert.contract.SplashContract;
import com.example.qwert.presenter.base.BasePresenter;
import com.example.qwert.tools.I;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;


public class SplashPresenter extends BasePresenter<SplashContract.IView> implements SplashContract.IPresenter {

    Handler mHandler = new Handler();

    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public SplashPresenter(SplashContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void goMain() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                I.goMain(mActivity);
                mActivity.finish();
                mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 5000);
    }

    @Override
    public void init() {

    }

    @Override
    public void CheckPermission() {
        /**
         * 获取读取sd卡的权限
         */
        AndPermission.with(mActivity).runtime().permission(permissions).onGranted(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
            }
        }).onDenied(new Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
            }
        }).start();
    }
}
