package com.example.qwert.contract;

import com.example.qwert.contract.base.IBasePresenter;
import com.example.qwert.contract.base.IBaseView;

public interface SplashContract {

    interface IView extends IBaseView {

    }

    interface IPresenter extends IBasePresenter {

        void goMain();

        void init();

        void CheckPermission();
    }
}
