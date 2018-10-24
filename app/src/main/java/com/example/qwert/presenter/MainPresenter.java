package com.example.qwert.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.example.qwert.app.utils.DataCleanManager;
import com.example.qwert.app.utils.GlideImageLoader;
import com.example.qwert.app.utils.ImageUtil;
import com.example.qwert.contract.MainContract;
import com.example.qwert.presenter.base.BasePresenter;
import com.example.qwert.tools.Rxbus;
import com.example.qwert.value.ContactValue;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {
    public static final int REQUEST_CODE_CHOOSE = 100;
    private String cacheSize = "0.0KB";

//    Handler handler = new Handler(Looper.getMainLooper());


    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0x00:

                    break;

                case 0x01:

                    break;
            }
            return true;
        }
    });


    public MainPresenter(MainContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void upLoad() {
        Intent intent = new Intent(mActivity, ImageGridActivity.class);
        mActivity.startActivityForResult(intent, REQUEST_CODE_CHOOSE);
    }

    @Override
    public void onUpLoadResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_CODE_CHOOSE) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && images.size() > 0) {
                    String imgPath = images.get(0).path;
                    if (!TextUtils.isEmpty(imgPath)) {
                        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
                        if (null != bitmap) {
                            /**
                             * 组件加载图片
                             */
//                            ivHeader.setImageBitmap(ImageUtil.toRoundBitmap(bitmap));
                        }
                        try {
                            File dir = new File(ContactValue.TEMP_FILE_PATH);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }

                            /**
                             * 获取图片路径
                             * 后拼接上时间
                             */
                            String dstFilePath = ContactValue.TEMP_FILE_PATH + "avatar_" + System.currentTimeMillis() + ".png";
                            File avatarFile = ImageUtil.resizeBitmapAndSave(bitmap, dstFilePath, 0.3f);
                            if (avatarFile != null && avatarFile.exists()) {
                                /**
                                 * 上传图片路径
                                 */
                                Rxbus.get().post(ContactValue.UPLOAD_IMAGE_PATH, avatarFile.getPath());
//                                uploadImgFile(avatarFile.getPath());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public String clearData(String dataSize) {
        if (TextUtils.equals("cacheSize", dataSize)) return cacheSize;
        try {
            DataCleanManager.cleanInternalCache(mActivity);
            handler.sendEmptyMessageDelayed(0x00, REQUEST_CODE_CHOOSE);
        } catch (Exception e) {
            handler.sendEmptyMessageDelayed(0x01, REQUEST_CODE_CHOOSE);
        }
        return cacheSize;
    }

    @Override
    public void initImagePicker() {
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
    public File getDataMangerSize() {
        File file = new File(mActivity.getCacheDir().getPath());
        return file;
    }


}
