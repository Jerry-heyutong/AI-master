package com.core;

/**
 * @author ly
 * @date 2019/5/30 11:59
 * desc: Constant变量
 */
public interface Constant {
    /**
     * Object Pool: Empty Object
     */
    Object EMPTY_OBJECT = new Object();

    /**
     * Current user在request中的key
     */
    String CURRENT_USER_KEY = "current_user";

    /**
     * 通用错误信息
     */
    String NORMAL_ERROR_MSG = "网络不稳定,请稍后重试";
}
