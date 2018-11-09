package com.example.qwert.mvp.contract.base;

public interface IBasePresenter {
    void onCreaet();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestory();

    void finish();

    void Log(String t);

}
