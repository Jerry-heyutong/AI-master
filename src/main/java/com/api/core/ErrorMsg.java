package com.api.core;

/**
 * @author ly
 * desc: ErrorMsg
 */
public interface ErrorMsg {
    /**
     * Object Pool: Empty Object
     */
    Object EMPTY_OBJECT = new Object();

    /**
     * 通用错误信息
     */
    String NORMAL_ERROR_MSG = "网络不稳定,请稍后重试";
}
