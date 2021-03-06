package com.yuer.storm.topology;

import com.yuer.storm.topology.model.FaceModel;
import com.yuer.storm.topology.model.Location;
import org.apache.storm.Config;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class PictureTopologyBuilder implements BasicTopologyBuilder, TopologyConfig {
    @Override
    public StormTopology builder() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("picture-lister", new PictureListener());
        builder.setBolt("picture-executor", new PictureExecutors())
                .shuffleGrouping("picture-lister");
        return builder.createTopology();
    }

    @Override
    public Config initTopologyConfig() {
        Config config = new Config();
        //config.setDebug(true);
        config.registerSerialization(Location.class);
        config.registerSerialization(FaceModel.class);
        //禁止使用java原生态序列化
        config.setFallBackOnJavaSerialization(false);
        return config;
    }
}
