package com.example.qwert.mvp.contract;

import com.example.qwert.bean.TestBean;
import com.example.qwert.mvp.contract.base.IBasePresenter;
import com.example.qwert.mvp.contract.base.IBaseView;

import java.util.List;

public interface RefreshContract {


    interface IView extends IBaseView, IBasePresenter {
        void onLoadDate(List<TestBean> testBean);
    }

    interface IPresenter extends IBasePresenter {
        void LoadMoreDate();

        void Refresh();
    }

}

