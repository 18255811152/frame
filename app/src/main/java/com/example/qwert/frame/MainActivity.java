package com.example.qwert.frame;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

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
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setMultiMode(true);
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(300);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(300);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
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

    @OnClick(R.id.upload)
    public void onViewClicked() {
        showToast("SSSSSSSSSSSSSSSSSS");
        mPresenter.upLoad();
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
}
