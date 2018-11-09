package com.example.qwert.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.example.qwert.R;
import com.example.qwert.mvp.contract.SplashContract;
import com.example.qwert.mvp.presenter.SplashPresenter;
import com.example.qwert.tools.T;
import com.example.qwert.view.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity<SplashContract.IPresenter> implements SplashContract.IView {
    Handler mHandler = new Handler(Looper.getMainLooper());


    @Override
    protected void initPresenter() {
        mPresenter = new SplashPresenter(this, this);
    }

    @Override
    protected void initEven() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initDate() {
        mPresenter.CheckPermission();
    }

    @Override
    public void goMain(Class aClass) {
        mHandler.postDelayed(() -> {
            T.s("sssssss");
            startActivity(new Intent(this, aClass));
            finish();
        }, 1000);
    }
}
