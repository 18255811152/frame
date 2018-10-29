package com.example.qwert.view.activity;

import com.example.qwert.R;
import com.example.qwert.contract.SplashContract;
import com.example.qwert.presenter.SplashPresenter;
import com.example.qwert.view.activity.base.BaseActivity;

public class SplashActivity extends BaseActivity<SplashContract.IPresenter> implements SplashContract.IView {

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
        mPresenter.goMain();
        mPresenter.CheckPermission();

    }
}
