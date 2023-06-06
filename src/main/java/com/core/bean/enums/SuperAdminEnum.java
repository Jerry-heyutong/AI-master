package com.core.bean.enums;

/**
 * @Author fys
 * @Date 2022-05-2022/5/17
 * 超管 123
 */
public enum SuperAdminEnum {

    ONE("超管1",1),

    TWO("超管2",2),

    THREE("超管3",3);

    private String type;
    private int number;

    SuperAdminEnum(String type, int number) {
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

    public static SuperAdminEnum getType(String type) {
        for (SuperAdminEnum p:
                SuperAdminEnum.values()) {
            if(p.getType().equals(type)){
                return p;
            }
        }
        return null;
    }
    public static SuperAdminEnum getType(int type) {
        for (SuperAdminEnum p:
                SuperAdminEnum.values()) {
            if(p.getNumber()==type){
                return p;
            }
        }
        return null;
    }

}
