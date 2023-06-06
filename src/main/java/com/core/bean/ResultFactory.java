package com.core.bean;


import com.core.Constant;


/**
 * @author ly
 * @date 2019/5/30 15:26
 * desc: ResultEntity的工厂方法
 */
public class ResultFactory {

    /**
     * 成功（无返回值）
     * @return  ResultEntity<T>
     */
    
    public static ResultEntity success() {
        return success(Constant.EMPTY_OBJECT);
    }

    /**
     * 成功（带返回值）
     * @param data 数据
     * @param <T>  类型
     * @return  ResultEntity<T>
     */
    
    public static <T> ResultEntity<T> success( T data) {
        return new ResultEntity<T>()
                .setFlag(true)
                .setCode(ErrorCode.SUCCESS.getCode())
                .setData(data);
    }


    /**
     * 失败（带错误原因）
     * @param errorCode ErrorCode
     * @return ResultEntity
     */
    
    public static ResultEntity fail( ErrorCode errorCode) {
        return new ResultEntity<>()
                .setFlag(false)
                .setCode(errorCode.getCode())
                .setData(Constant.NORMAL_ERROR_MSG);
    }
    /**
     * 失败（带错误原因）
     */
    
    public static ResultEntity fail(ErrorCode errorCode, String msg) {
        return new ResultEntity<>()
                .setFlag(false)
                .setCode(errorCode.getCode())
                .setData(msg);
    }

    /**
     * 失败（默认错误原因）
     * @return ResultEntity
     */
    
    public static ResultEntity fail() {
        return fail(ErrorCode.FAIL);
    }
}

