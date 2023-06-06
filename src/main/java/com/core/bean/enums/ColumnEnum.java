package com.core.bean.enums;

/**
 * @Author fys
 * @Date 2022-06-2022/6/22
 */
public enum ColumnEnum {

    /**
     * 跟进人 集合
     */
    FOLLOW_USER("followUserIds"),

    /**
     * 转换后的userIds
     */
    CONVERSION_USER_ID("conversionUserIds");

    /**
     * 字段
     */
    private final String columnName;

    ColumnEnum(String columnName){
        this.columnName=columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
