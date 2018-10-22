package com.example.qwert.view.activity.base;

import android.app.Activity;
import android.os.Bundle;

public abstract class LowestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }




    protected abstract void initView();

}
