package com.example.qwert.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.qwert.R;
import com.example.qwert.app.utils.DataCleanManager;
import com.example.qwert.app.utils.GeneratePassWordUtils;
import com.example.qwert.app.utils.GlideImageLoader;
import com.example.qwert.app.utils.ImageUtil;
import com.example.qwert.contract.MainContract;
import com.example.qwert.presenter.base.BasePresenter;
import com.example.qwert.tools.I;
import com.example.qwert.tools.Rxbus;
import com.example.qwert.tools.T;
import com.example.qwert.value.ContactValue;
import com.example.qwert.view.weight.DialogListener;
import com.example.qwert.view.weight.DialogUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {
    public static final int REQUEST_CODE_CHOOSE = 100;
    @BindView(R.id.random)
    TextView random;
    @BindView(R.id.create_psw)
    TextView createPsw;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.big)
    CheckBox big;
    @BindView(R.id.small)
    CheckBox small;
    @BindView(R.id.special)
    CheckBox special;
    @BindView(R.id.btn_cancle)
    Button btnCancle;
    @BindView(R.id.btn_ref)
    Button btnRef;
    @BindView(R.id.btn_copy)
    Button btnCopy;
    private String cacheSize = "0.0KB";

    private Handler mHandler = new Handler(Looper.getMainLooper());


    private PopupWindow mPopwindow;


    Handler handler = new Handler(message -> {
        switch (message.what) {
            case 0x00:

                break;

            case 0x01:

                break;
        }
        return true;
    });


    public MainPresenter(MainContract.IView mView, Activity mActivity) {
        super(mView, mActivity);
    }

    @Override
    public void upLoadImagePicker() {
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
                            /*组件加载图片*/
//                            ivHeader.setImageBitmap(ImageUtil.toRoundBitmap(bitmap));
                        }
                        try {
                            File dir = new File(ContactValue.TEMP_FILE_PATH);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }

                            /*  获取图片路径
                             * 后拼接上时间*/
                            String dstFilePath = ContactValue.TEMP_FILE_PATH + "avatar_" + System.currentTimeMillis() + ".png";
                            File avatarFile = ImageUtil.resizeBitmapAndSave(bitmap, dstFilePath, 0.3f);
                            if (avatarFile != null && avatarFile.exists()) {
                                /*上传图片路径*/
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

    /*仿照微信上传*/
    @Override
    public void upLoadImagePickerWeiXin() {
        mHandler.postDelayed(() -> {
            I.goWeiXinActivity(mActivity);
            mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, 1000);
    }

    /*普通上传*/
    @Override
    public void upLoadImage() {

    }

    /*生成随机密码*/
    @Override
    public String createRandomCode() {
        /*生成6位密码*/
        String Psw = GeneratePassWordUtils.getRandomPwd(6, true, true, true);
        return Psw;
    }

    /*dialog实例*/
    @Override
    public void instanceDialog() {
        DialogUtils.showMsgDialog(mActivity, "sssssssss", "ssssssssss", new DialogListener() {
            @Override
            public void onClick() {
                T.s("sssssssssssss");
            }
        });
    }

    /*系统分享*/
    @Override
    public void SystemShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "sssssssssssssssssss");
        intent.setType("text/plain");
        mActivity.startActivity(intent.createChooser(intent, "分享"));
    }

    /*系统短信发送*/
    @Override
    public void SystemSms() {
        Uri uri = Uri.parse("smsto:");
        mActivity.startActivityForResult(new Intent(Intent.ACTION_VIEW, uri).putExtra("sms_body", "sms_body").setType("vnd.android-dir/mms-sms"), 1002);
    }

    /*刷新界面*/
    @Override
    public void Refresh() {
        I.gotoRefresh(mActivity);
    }


}
