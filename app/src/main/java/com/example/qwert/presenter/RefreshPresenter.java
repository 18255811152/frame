package com.example.qwert.presenter;

import android.app.Activity;

import com.example.qwert.contract.RefreshContract;
import com.example.qwert.presenter.base.BasePresenter;

public class RefreshPresenter extends BasePresenter<RefreshContract.IView> implements RefreshContract.IPresenter {

    public RefreshPresenter(RefreshContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void LoadMoreDate() {

    }

    @Override
    public void Refresh() {

    }
}
