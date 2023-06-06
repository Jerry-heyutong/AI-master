package com.core.bean.enums;

/**
 * @Author: pd
 * @Date: 2019/8/5 12:04
 * @Desc:
 */
public enum JumpPageEnum {

    /**
     * 出让文件
     */
    Transfer_file(1),

    /**
     * 成交文件
     */
    Bargain_file(2),

    /**
     *地块管理
     */
    Land_manage(3),

    /**
     *政策法规
     */
    Policies_regulations(4),

    /**
     * 城市规划
     */
    City_plan(5),

    /**
     * 宏观模块
     */
    Macro_Data(6),

    /**
     * 银行间市场债券
     */
    Bank_All(9),

    /**
     * 大额存单
     */
    Large_Amount(10),

    /**
     * 交易所债券
     */
    Exchange_All(11),

    /**
     * 债券市场
     */
    One_All(12),

    /**
     * 债券进度信息
     */
    Progress_All(13),

    /**
     * 非银行理财产品
     */
    Not_Money(18),


    //—————下面的不在使用-------------------//
    /**
     * 融资余额指标
     */
    Enterprise_Balance(7),

    /**
     * 债券融资明细年度表
     */
    Enterprise_Bond_Detail(8),

    /**
     * 融资到期情况
     */
    Enterprise_Expire(14),

    /**
     * 全部合并指标表
     */
    All_Merge(15),

    /**
     * 债券合并指标表
     */
    Bond_Merge(16),

    /**
     * 借贷及其他指标表
     */
    Debit_Other(17);




    private int index;

        JumpPageEnum(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
    }
}
