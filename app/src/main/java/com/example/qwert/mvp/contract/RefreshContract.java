package com.example.qwert.mvp.contract;

import com.example.qwert.mvp.contract.base.IBasePresenter;
import com.example.qwert.mvp.contract.base.IBaseView;

public interface RefreshContract {


    interface IView extends IBaseView, IBasePresenter {

    }

    interface IPresenter extends IBasePresenter {
        void LoadMoreDate();

        void Refresh();
    }

}

