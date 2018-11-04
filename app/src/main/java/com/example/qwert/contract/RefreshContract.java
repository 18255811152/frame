package com.example.qwert.contract;

import com.example.qwert.contract.base.IBasePresenter;
import com.example.qwert.contract.base.IBaseView;

public interface RefreshContract {


    interface IView extends IBaseView, IBasePresenter {

    }

    interface IPresenter extends IBasePresenter {
        void LoadMoreDate();

        void Refresh();
    }

}

