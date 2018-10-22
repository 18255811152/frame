package com.example.qwert.presenter.base;

import android.app.Activity;

import com.example.qwert.contract.base.IBasePresenter;
import com.example.qwert.contract.base.IBaseView;
import com.example.qwert.tools.L;

public class BasePresenter<V extends IBaseView> implements IBasePresenter {

    protected V mView;
    protected Activity mActivity;

    public BasePresenter(V mView, Activity mActivity) {
        this.mView = mView;
        this.mActivity = mActivity;
    }

    @Override
    public void onCreaet() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void finish() {
        mActivity.finish();
    }

    @Override
    public void Log(String t) {
        L.e(t);
    }


}
