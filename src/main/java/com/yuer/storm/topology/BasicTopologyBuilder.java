package com.yuer.storm.topology;

import org.apache.storm.generated.StormTopology;

public interface BasicTopologyBuilder {
    StormTopology builder();
}
