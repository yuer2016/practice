package com.yuer.storm.topology;

import org.apache.storm.LocalCluster;
import org.apache.storm.utils.Utils;

public class LocalTopologyRunner {
    private static final int TEN_MINUTES = 10000;

    public static void main(String[] args) {
        BasicTopologyBuilder topologyBuilder = new PictureTopologyBuilder();
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("photo-executor",((PictureTopologyBuilder) topologyBuilder).initTopologyConfig(),
                topologyBuilder.builder());
        Utils.sleep(TEN_MINUTES);
        cluster.killTopology("photo-executor");
        cluster.shutdown();
    }
}
