package com.example.qwert.contract;

import android.content.Intent;

import com.example.qwert.contract.base.IBasePresenter;
import com.example.qwert.contract.base.IBaseView;

public interface MainContract {

    interface IView extends IBaseView, IBasePresenter {

    }

    interface IPresenter extends IBasePresenter {

        void upLoad();

        void onUpLoadResult(int requestCode, int resultCode, Intent data);
    }

}
