package com.example.qwert.frame;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.qwert.R;
import com.example.qwert.app.utils.DataCleanManager;
import com.example.qwert.contract.MainContract;
import com.example.qwert.presenter.MainPresenter;
import com.example.qwert.tools.Rxbus;
import com.example.qwert.tools.T;
import com.example.qwert.value.ContactValue;
import com.example.qwert.view.activity.base.BaseActivity;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.IPresenter> implements MainContract.IView {

    @BindView(R.id.upload_imagePicker)
    Button uploadImagePicker;

    @BindView(R.id.upload_imagePicker_weixin)
    Button uploadImagePickerWeiXin;

    @BindView(R.id.upload)
    Button upload;

    @BindView(R.id.clear_data)
    Button clearData;
    @BindView(R.id.btn_randomCode)
    Button btnRandomCode;
    @BindView(R.id.instance_dialog)
    Button instanceDialog;


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

    /*获取图片路径*/
    @Subscribe
            (thread = EventThread.MAIN_THREAD,
                    tags = {@Tag(ContactValue.UPLOAD_IMAGE_PATH)}
            )
    public void getImagePath(String ImagePath) {
        imageView.setImageURI(Uri.fromFile(new File(ImagePath)));
    }

    @OnClick({R.id.clear_data, R.id.upload, R.id.upload_imagePicker,
            R.id.upload_imagePicker_weixin, R.id.btn_randomCode,
            R.id.instance_dialog, R.id.btn_share, R.id.btn_sms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*清空缓存*/
            case R.id.clear_data:
                clearData.setText(mPresenter.clearData(clearData.getText().toString().trim()));
                break;
            /*单张上传图片*/
            case R.id.upload_imagePicker:
                mPresenter.upLoadImagePicker();
                break;
            /*仿照微信上传图片*/
            case R.id.upload_imagePicker_weixin:
                mPresenter.upLoadImagePickerWeiXin();
                break;
            /*上传图片*/
            case R.id.upload:
                mPresenter.upLoadImage();
                break;
            /*生成6位密码*/
            case R.id.btn_randomCode:
                btnRandomCode.setText(mPresenter.createRandomCode());
                break;
            /*对话框实例*/
            case R.id.instance_dialog:
                mPresenter.instanceDialog();
                break;
            /*系统自带分享*/
            case R.id.btn_share:
                mPresenter.SystemShare();
                break;
            /*通过短信分享*/
            case R.id.btn_sms:
                mPresenter.SystemSms();
                break;
        }
    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == 1002) {
            T.s(getString(R.string.SMS_MSG));
        }
    }
}
