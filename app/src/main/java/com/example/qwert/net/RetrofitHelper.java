package com.example.qwert.net;


import android.os.Build;


import com.example.qwert.BoxApplication;
import com.example.qwert.BuildConfig;
import com.example.qwert.app.utils.NullOnEmptyConverterFactory;
import com.example.qwert.app.utils.PackageInfoUtil;
import com.example.qwert.bean.DataCenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 设置网络请求的配置
 */
public class RetrofitHelper {
//    private static final String baseUrl = DataCenter.getInstance().getDomain();

    private static final int DEFAULT_TIMEOUT_SECONDS = 7;
    private static final int DEFAULT_READ_TIMEOUT_SECONDS = 20;
    private static final int DEFAULT_WRITE_TIMEOUT_SECONDS = 20;
    private Retrofit mRetrofit;

    private RetrofitHelper() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor());


        // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //   日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        builder.addInterceptor(interceptor);  // 添加httplog

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    /**
                     * 配置请求域名
                     * 直接配置在build文件中
                     */
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new Gson())) //添加Gson支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加RxJava支持
                    .client(builder.build()) //关联okhttp
                    .build();
        }

    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 获取服务对象   Rxjava+Retrofit建立在接口对象的基础上的
     * 泛型避免强制转换
     */
    public static <T> T getService(Class<T> classz) {
        return RetrofitHelper.getInstance().mRetrofit.create(classz);
    }


    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            setHeaderToken(builder);
            setHeaderBaseParams(builder);
            request = builder.build();
            return processResponse(chain.proceed(request));
        }

        //访问网络之后，处理Response(这里没有做特别处理)
        private Response processResponse(Response response) {
            doHttpCode(response);
            refreshToken(response);
            return response;
        }

        /**
         * 设置token
         *
         * @param builder
         */
        private void setHeaderToken(Request.Builder builder) {
            String token = DataCenter.getInstance().getUser().getToken();
            if (token != null) {
                builder.addHeader("token", DataCenter.getInstance().getUser().getToken());
            }
        }

        /**
         * 设置基本参数
         *
         * @param builder
         */
        private void setHeaderBaseParams(Request.Builder builder) {
            builder.addHeader("User-Agent", "app_android");
            builder.addHeader("VersionName", PackageInfoUtil.getVersionName(BoxApplication.getContext()));//app版本号
            builder.addHeader("SysCode", Build.VERSION.RELEASE);// 系统版本号
            builder.addHeader("Brand", Build.BRAND);//手机品牌
            builder.addHeader("Model", Build.MODEL);//手机型号
//            builder.addHeader("Connection", "close"); //关闭链接
        }

        /**
         * 处理http code
         *
         * @param response
         */
        private void doHttpCode(Response response) {
            // 处理http code
            int statusCode = response.code();
            if (statusCode != 200) {
                throw new CustomHttpException(statusCode);
            }
        }


        /**
         * 刷新token
         *
         * @param response
         */
        private void refreshToken(Response response) {
            // 处理token
            Headers headers = response.headers();
            String token = headers.get("token");
            if (token != null && !token.equals(DataCenter.getInstance().getUser().getToken())) {
                DataCenter.getInstance().getUser().setToken(token);
            }
        }
    }

}
