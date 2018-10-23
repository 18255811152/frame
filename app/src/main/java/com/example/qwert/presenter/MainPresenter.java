package com.example.qwert.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.example.qwert.app.utils.ImageUtil;
import com.example.qwert.contract.MainContract;
import com.example.qwert.presenter.base.BasePresenter;
import com.example.qwert.tools.Rxbus;
import com.example.qwert.value.ContactValue;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {
    public static final int REQUEST_CODE_CHOOSE = 100;

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


//    /**
//     * 编写上传图片功能
//     *
//     * @param path
//     */
//    private void uploadImgFile(final String path) {
//
//    }


}
