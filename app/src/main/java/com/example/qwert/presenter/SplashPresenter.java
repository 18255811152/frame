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
    //android.media.action.IMAGE_CAPTURE
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public SplashPresenter(SplashContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void goMain() {
        mHandler.postDelayed(() -> {
            I.goMain(mActivity);
            mActivity.finish();
            mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, 2000);
    }

    @Override
    public void init() {

    }

    @Override
    public void CheckPermission() {
        /*获取读取sd卡的权限*/
        AndPermission.with(mActivity).permission(permissions).onDenied(permissions -> {
        }).onGranted(permissions -> {
        }).start();
    }
}
