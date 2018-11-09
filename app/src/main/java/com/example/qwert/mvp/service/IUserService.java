package com.example.qwert.mvp.service;

import com.example.qwert.bean.User;
import com.example.qwert.mvp.contract.mode.GeoCoding;
import com.example.qwert.net.HttpResult;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface IUserService {
    @FormUrlEncoded
    @POST("geocoding")
    Observable<HttpResult<User>> getUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("appKey") String appKey,
            @Field("appSecret") String appSecret,
            @Field("serialNo") String serialNo);

    @GET("geocoding")
    Observable<GeoCoding> getCoding(
            @Field("username") String username,
            @Field("password") String password,
            @Field("appKey") String appKey,
            @Field("appSecret") String appSecret,
            @Field("serialNo") String serialNo);
}
