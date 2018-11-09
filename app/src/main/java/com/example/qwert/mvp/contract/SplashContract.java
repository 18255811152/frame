package com.example.qwert.mvp.contract;

import com.example.qwert.mvp.contract.base.IBasePresenter;
import com.example.qwert.mvp.contract.base.IBaseView;

public interface SplashContract {

    interface IView extends IBaseView {
        void goMain(Class<?> aClass);
    }

    interface IPresenter extends IBasePresenter {
        void CheckPermission();
    }
}
