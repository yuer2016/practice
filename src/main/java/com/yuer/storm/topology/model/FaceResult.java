package com.yuer.storm.topology.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FaceResult {
    //车辆id
    private String vehicleId;
    //关联司机表id
    private String driverId;
    //图片时间
    private Long imgTime;
    //司机图片
    private String driverImg;
}
