package com.core.bean.enums;

/**
 * @Author: pd
 * @Date: 2019/8/2 11:13
 * @Desc:板块枚举
 */
public enum ModuleType {
    /**
     * 宏观板块
     */
    Macro_module(1),

    /**
     * 土地板块
     */
    Land_module(2),

    /**
     *金融板块
     */
    Finance_module(3),

    /**
     *房企板块
     */
    Enterprise_module(4),
    /**
     * 其他板块(政策法规,城市规划等)
     */
    Other_module(5);

        private int index;

        ModuleType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
}
