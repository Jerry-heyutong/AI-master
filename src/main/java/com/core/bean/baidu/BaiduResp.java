package com.core.bean.baidu;

import lombok.Data;

/**
 * @Author fys
 * @Date 2022-02-2022/2/22
 */
@Data
public class BaiduResp {


    /**
     * 普通ip定位
     */
    @Data
    public static class IpResp{

        /**
         * 详细地址信息
         */
        private String address;

        /**
         * 结果状态码
         */
        private Integer status;

        /**
         * 结构信息
         */
        private Content content;

        @Data
        public static class Content{

            /**
             * 简要地址信息
             */
            private String address;

            /**
             * 结构化地址信息
             */
            private AddressDetail address_detail;

            /**
             * 当前城市中心点
             */
            private Point point;

            @Data
            public static class AddressDetail{

                /**
                 * 省份
                 */
                private String province;

                /**
                 * 城市
                 */
                private String city;

                /**
                 * 行政区
                 */
                private String district;

                /**
                 * 街道号
                 */
                private String street_number;


                private String adcode;

                /**
                 * 街道
                 */
                private String street;

                /**
                 * 百度城市代码
                 */
                private Integer city_code;
            }

            @Data
            public static class Point{

                /**
                 * X轴坐标
                 */
                private String x;

                /**
                 * Y轴坐标
                 */
                private String y;
            }

        }
    }
}
