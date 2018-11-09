package com.example.qwert.mvp.presenter;

import android.Manifest;
import android.app.Activity;

import com.example.qwert.mvp.contract.SplashContract;
import com.example.qwert.frame.MainActivity;
import com.example.qwert.mvp.presenter.base.BasePresenter;
import com.yanzhenjie.permission.AndPermission;


public class SplashPresenter extends BasePresenter<SplashContract.IView> implements SplashContract.IPresenter {

    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public SplashPresenter(SplashContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void CheckPermission() {
        /*获取读取sd卡的权限*/
        AndPermission.with(mActivity).permission(permissions).onDenied(permissions -> {

        }).onGranted(permissions -> mView.goMain(MainActivity.class)).start();
    }
}
