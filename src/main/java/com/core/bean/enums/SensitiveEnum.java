package com.core.bean.enums;

/**
 * 敏感信息脱敏字段
 */
public enum SensitiveEnum {
    ALL("."),
    DEPT("^[a-zA-Z+\\.?\\·?a-zA-Z+]{2,30}$"),
    NAME("^(([a-zA-Z+\\.?\\·?a-zA-Z+]{2,30}$)|([\\u4e00-\\u9fa5+\\·?\\u4e00-\\u9fa5+]{2,6}$))"),
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),
    MOBILE_PHONE("^(1[0-9])\\d{9}$"),
    FIXED_PHONE("^((\\d{3,4}-))?\\d{7,8}$"),
    PHONE("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)"),
    ID_CARD("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
    private String regx;
    SensitiveEnum(String regx){
        this.regx=regx;
    }

    public String getRegx() {
        return regx;
    }

}
