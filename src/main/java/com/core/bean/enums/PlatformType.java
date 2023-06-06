package com.core.bean.enums;

/**
 * @PACKAGE_NAME: com.zdh.core.bean.enums
 * @NAME: PlatformType
 * @Author: HeYuTong
 * @DATE: 2021/07/27
 * 平台类型枚举
 **/
public enum PlatformType {
    /**
     * 内部治理
     */
    INSIDE("0",0),


    /**
     * 数据平台
     */
    DATA_PLATFORM("1",1),
    /**
     * H5
     */
    H5("2",2),

    /**
     * 小程序
     */
    MINI_PROGRAM("3",3),

    /**
     * 官网
     */
    WEBSITE("4",4),

    /**
     * 拓展平台
     */
    EXCHANGE("5",5);

    private String type;
    private int number;

    PlatformType(String type,int number) {
        this.type = type;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static PlatformType getType(String type) {
        for (PlatformType p:
             PlatformType.values()) {
            if(p.getType().equals(type)){
                return p;
            }
        }
        return null;
    }
    public static PlatformType getType(int type) {
        for (PlatformType p:
             PlatformType.values()) {
            if(p.getNumber()==type){
                return p;
            }
        }
        return null;
    }
}
