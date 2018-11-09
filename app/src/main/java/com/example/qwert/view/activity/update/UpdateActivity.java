package com.example.qwert.view.activity.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.example.qwert.bean.DeviceUtils;
import com.example.qwert.bean.MainBean;
import com.example.qwert.bean.UpdateModel;
import com.example.qwert.frame.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdateActivity extends Activity {
    private DownloadBuilder builder;
    private UpdateModel updateModel;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler.postDelayed(() -> appUpData(), 1000);
    }


    private void appUpData() {
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl("http://jk8088.com/api/v1/init/61")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        updateModel = new Gson().fromJson(result, UpdateModel.class);
                        if (Integer.valueOf(updateModel.version_code) > DeviceUtils.getVersionCode(getApplicationContext())) {
                            UIData uiData = UIData.create();
                            uiData.setTitle(updateModel.title);
                            uiData.setDownloadUrl(updateModel.apk_url);
                            uiData.setContent(updateModel.upgrade_point.replace("\\n", "\n"));
                            if (updateModel.is_force.equals("1")) {
                                builder.setForceUpdateListener(() -> forceUpdate());
                                Toast.makeText(UpdateActivity.this, "您必须更新当前APP才能继续使用相关功能", Toast.LENGTH_SHORT).show();
                            }

                            builder.setOnCancelListener(() -> gotoMainUI());
                            return uiData;
                        } else {
                            gotoMainUI();
                            return null;
                        }
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {

                    }
                });
        builder.excuteMission(this);
    }

    private void gotoMainUI() {
        String url = "http://jk8088.com/api/v1/newshow/86";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.code() == 200) {
                    MainBean mainBean = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), MainBean.class);
                    if (null != mainBean.url && null != mainBean.is_show) {
                        if (mainBean.is_show.equals("1")) {
                            //设置打开的网页
                            String werUrl = mainBean.url;
                            Intent startIntent = new Intent(getApplicationContext(),
                                    MainWebActivity.class);
                            startIntent.putExtra("werUrl", werUrl);
                            startActivity(startIntent);
                            finish();
                        } else {
                            startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                }
            }
        });
    }

    /**
     * 强制更新操作
     */
    private void forceUpdate() {
        this.finish();
    }
}
