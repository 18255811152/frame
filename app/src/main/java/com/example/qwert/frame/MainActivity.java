package com.example.qwert.frame;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qwert.app.utils.DataCleanManager;
import com.example.qwert.app.utils.GlideImageLoader;
import com.example.qwert.contract.MainContract;
import com.example.qwert.presenter.MainPresenter;
import com.example.qwert.tools.Rxbus;
import com.example.qwert.value.ContactValue;
import com.example.qwert.view.activity.base.BaseActivity;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.IPresenter> implements MainContract.IView {

    @BindView(R.id.upload)
    Button upload;
    @BindView(R.id.clear_data)
    Button clearData;

    @Override
    public void onCreaet() {
        mPresenter.onCreaet();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDate() {
        Rxbus.get().register(this);
        mPresenter.initImagePicker();
        mPresenter.getDataMangerSize();
        try {
            clearData.setText(DataCleanManager.getCacheSize(mPresenter.getDataMangerSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEven() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestory() {
        Rxbus.get().unregister(this);
        mPresenter.onDestory();
        mPresenter = null;
    }

    @Override
    public void Log(String t) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onUpLoadResult(requestCode, resultCode, data);
    }

    @BindView(R.id.loadImage)
    ImageView imageView;


    @Subscribe
            (thread = EventThread.MAIN_THREAD,
                    tags = {@Tag(ContactValue.UPLOAD_IMAGE_PATH)}
            )
    public void getImagePath(String ImagePath) {
        imageView.setImageURI(Uri.fromFile(new File(ImagePath)));
    }

    @OnClick({R.id.clear_data, R.id.upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clear_data:
                clearData.setText(mPresenter.clearData(clearData.getText().toString().trim()));


                break;
            case R.id.upload:
                mPresenter.upLoad();
                break;
        }
    }
}
