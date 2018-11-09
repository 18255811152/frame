package com.example.qwert.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.qwert.bean.TestBean;
import com.example.qwert.mvp.contract.RefreshContract;
import com.example.qwert.mvp.presenter.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class RefreshPresenter extends BasePresenter<RefreshContract.IView> implements RefreshContract.IPresenter {
    List<TestBean> testBeans = new ArrayList<>();


    public RefreshPresenter(RefreshContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void LoadMoreDate() {
        for (int i = 0; i < 10; i++) {
            TestBean testBean = new TestBean();
            testBean.setName("name" + i);
            testBean.setAge(i);
            testBeans.add(testBean);
        }
        mView.onLoadDate(testBeans);
    }

    @Override
    public void Refresh() {

    }
}
