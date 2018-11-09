package com.example.qwert;

public interface ConstantValue {
    /**
     * 接口返回成功但是服务器校验失败处理
     */
    String EVENT_TYPE_NETWORK_RETURN = "String EVENT_TYPE_NETWORK_EXCEPTION";
    /**
     * 接口失败，抛出异常
     * 页面有刷新或者加载更多，或者页面要在抛出异常处理一些事物
     */
    String EVENT_TYPE_NETWORK_EXCEPTION = "EVENT_TYPE_NETWORK_EXCEPTION";
}
