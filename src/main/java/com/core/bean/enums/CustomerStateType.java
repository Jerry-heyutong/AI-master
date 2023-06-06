package com.core.bean.enums;

/**
 * @PACKAGE_NAME: com.zdh.core.bean.enums
 * @NAME: PlatformType
 * @Author: HeYuTong
 * @DATE: 2021/07/27
 * 平台类型枚举
 **/
public enum CustomerStateType {

    /**
     * 不可用
     */
    NOT_USE("不可用",-1),

    /**
     * 试用
     */
    THE_TRIAL("试用",0),

    /**
     * 正式可用
     */
    FORMAL("正式",1),

    /**
     * 流失
     */
    LEAVE("流失",2),

    /**
     * 流失试用
     */
    LEAVE_THE_TRIAL("流失试用",3);


    private String type;
    private int number;

    CustomerStateType(String type, int number) {
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

    public static CustomerStateType getType(String type) {
        for (CustomerStateType p:
             CustomerStateType.values()) {
            if(p.getType().equals(type)){
                return p;
            }
        }
        return null;
    }
    public static CustomerStateType getType(int type) {
        for (CustomerStateType p:
             CustomerStateType.values()) {
            if(p.getNumber()==type){
                return p;
            }
        }
        return null;
    }

}
