package com.yuer.storm.topology.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Location {
    private long timeGps;
    private long timeRcv;
    private int mileageGps;
    private int mileagePls;
    private int lng;
    private int lat;
    private int head;
    private int height;
    private int spdGps;
    private int spdPls;
    private int flag;
    private String status;
    private String extension;
}
