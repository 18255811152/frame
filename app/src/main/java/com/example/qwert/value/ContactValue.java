package com.example.qwert.value;

import com.example.qwert.app.utils.SDCardUtils;

public interface ContactValue {

    String ROOT_PATH = String.format("%s%s", SDCardUtils.getESDString(), "/frame/");// 根目录

    String TEMP_FILE_PATH = String.format("%stemp/", ROOT_PATH);// 临时文件存放的目录


    /**
     * 通知消息
     */

    /**
     * 获取上传的路径
     */
    String UPLOAD_IMAGE_PATH = "UPLOAD_IMAGE_PATH";

}
