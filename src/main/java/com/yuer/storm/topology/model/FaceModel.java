package com.yuer.storm.topology.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FaceModel {
    //设备Id
    private String deviceId;
    //图片存储地址
    private String imgPath;
    //图片上传时间
    private Long imgTime;
}
