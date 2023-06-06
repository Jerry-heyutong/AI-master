package com.core.bean.dto;

import com.core.bean.ImABean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


public class PythonTask {
    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Query implements ImABean {
        @ApiModelProperty("页码")
        @Builder.Default
        Integer page=1;
        @Builder.Default
        Integer size=20;
        @ApiModelProperty("任务名称")
        String name;

        @Builder.Default
        @ApiModelProperty("调度类型 1 爬虫 2 数据生产")
        Integer type=1;
        @ApiModelProperty("开始时间")
        String startTime;
        @ApiModelProperty("结束时间")
        String endTime;
        @ApiModelProperty("是否开启分页(默认分页)")
        @Builder.Default
        Boolean pageEnabled=true;
    }
    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Task implements ImABean {
         Integer id;
         @ApiModelProperty("任务简介")
         String name;
         @ApiModelProperty("任务的名称")
         String task;
         @ApiModelProperty("JSON编码的位置参数（例如：[“arg1”，“arg2”]）")
         String args;
         @ApiModelProperty("JSON编码的关键字参数（示例：“argument”：“value”）")
         String kwargs;
         @ApiModelProperty("队列")
         String queue;
         @ApiModelProperty("交换机")
         String exchange;
         @ApiModelProperty("路由")
         String routing_key;
         @ApiModelProperty("优先级介于0和255之间。支持：rabbitmq，redis（优先级反转，0最高）")
         Integer priority;
         @ApiModelProperty("如果为真，计划将只运行一次任务")
         Boolean one_off=false;
         @ApiModelProperty("设置为false以禁用计划")
         Boolean enabled=true;
         @ApiModelProperty("此定期任务的详细说明")
         String description;
         @ApiModelProperty("间断任务")
         String interval;
         @ApiModelProperty("crontab")
         String crontab;
        @Builder.Default
         String method="post";
         @ApiModelProperty("调度业务信息")
         SchTask scheduleTask;

        String date_changed;

        String last_run_at;

        Integer total_run_count;
    }
    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class SchTask implements ImABean {
        private Integer id;
        /**
         * 任务名称
         */
        private String taskName;

        /**
         * 任务类型 1 数据爬虫 2 数据生产
         */
        private Integer taskType;

        /**
         * 任务描述
         */
        private String taskDesc;


        /**
         * 对应的python任务ID
         */
        private Integer taskId;
        private String schTaskName;

        private String md5;
        private String dataSql;
        private String dataBase;
        private String dataSource;
        private String fullTaskName;

    }

    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class SchTask2 implements ImABean {
        private Integer id;
        /**
         * 任务名称
         */
        private String taskName;

        /**
         * 任务类型 1 数据爬虫 2 数据生产
         */
        private Integer taskType;

        /**
         * 任务描述
         */
        private String taskDesc;


        /**
         * 对应的python任务ID
         */
        private Integer taskId;
        private String schTaskName;

        private String md5;
        private String dataSql;
        private String dataBase;
        private String dataSource;
        private String fullTaskName;

        private Task task;

    }


    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Interval implements ImABean {
        Integer id;
        Integer every;
        String period;
    }
    @ToString
    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Cron implements ImABean {
        Integer id;
        String timezone;
        String minute;
        String hour;
        String day_of_week;
        String day_of_month;
        String month_of_year;
    }


}
