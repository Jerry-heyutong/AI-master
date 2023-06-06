package com.core.enums;

/**
 * @Author fys
 * @Date 2022-07-2022/7/27
 */
public enum EsQueryTemplateEnum {
    CITY("keyWord.city"),
    PROVINCE("keyWord.province"),
    COUNTRY("keyWord.country"),
    COUNTY("keyWord.county"),
    DATA_STANDARD("keyWord.dataStandard"),
    DATE("keyWord.date"),
    TIME_PERIOD("keyWord.timePeriod"),
    NAME("targets.name"),
    TARGET_VALUE("targets.targetValue")
    ;
    private String key;

    EsQueryTemplateEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
