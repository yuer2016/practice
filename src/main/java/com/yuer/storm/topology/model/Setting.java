package com.yuer.storm.topology.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Setting {
    //分析组ID
    private Integer analyseGroupsID;
    //报警开始时间
    private String startTime;
    //报警结束时间
    private String endTime;
    //报警速度
    private Integer speed;
    //照片报警最大张数
    private Integer alarmNumber;
    //缓存轨迹时间(单位:分钟)
    private Integer locationCache;
    //缓存分析照片结果(单位:分钟)
    private Integer photoResultCache;
    //报警方式
    private Integer alarmMode;

}
