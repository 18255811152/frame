package com.example.qwert.view.activity.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.qwert.app.utils.StatusBarUtils;
import com.example.qwert.contract.base.IBasePresenter;
import com.example.qwert.contract.base.IBaseView;
import com.example.qwert.tools.T;

import java.io.InterruptedIOException;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends IBasePresenter> extends LowestActivity implements IBaseView {

    protected P mPresenter;

    private Context context;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
//        mPresenter.onCreaet();
        initPresenter();
        checkPresenterIsNull();
        initDate();
        initEven();
        StatusBarUtils.initStatusBar(activity, false, true, true);
    }

    /**
     * presenter为null抛出异常
     * please init mPresenter in initPresenter() method
     */
    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            try {
                throw new InterruptedIOException("please init mPresenter in initPresenter() method");
            } catch (InterruptedIOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
        mPresenter = null;
    }

    /**
     * 初始化presenter
     */
    protected abstract void initPresenter();

    /**
     * @param t
     */
    @Override
    public void showToast(String t) {
        T.s(t);
    }

    @Override
    protected void initView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();


    protected abstract void initDate();

    protected abstract void initEven();
}
