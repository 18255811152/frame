package com.example.qwert.mvp.contract;

import android.content.Intent;

import com.example.qwert.mvp.contract.base.IBasePresenter;
import com.example.qwert.mvp.contract.base.IBaseView;

import java.io.File;

public interface MainContract {

    interface IView extends IBaseView, IBasePresenter {
        void setRandom(String random);

        void setImagePath(String imagePath);
    }

    interface IPresenter extends IBasePresenter {
        /*上传图片*/
        void upLoadImagePicker();

        /*仿微信上传*/
        void upLoadImagePickerWeiXin();

        /*自己上传图片*/
        void upLoadImage();


        void onUpLoadResult(int requestCode, int resultCode, Intent data);

        /*清楚缓存*/
        String clearData(String dataSize);

        /*初始化Imagepicker*/
        void initImagePicker();

        /* 获取缓存大小*/
        File getDataMangerSize();

        /*生成随机密码*/
        void createRandomCode();

        /*dialog实例*/
        void instanceDialog();

        /*系统分享功能*/
        void SystemShare();

        /* 系统短信发送功能*/
        void SystemSms();

        /*刷新*/
        void Refresh();

    }

}
