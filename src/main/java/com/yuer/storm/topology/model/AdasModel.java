package com.yuer.storm.topology.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AdasModel {
    //报警Id
    private Integer alarmId;
    //图片地址
    private String imagePath;
    //报警类型
    private Integer alarmType;
}
