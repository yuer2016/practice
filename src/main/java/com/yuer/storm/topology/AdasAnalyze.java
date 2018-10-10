package com.yuer.storm.topology;

import com.yuer.storm.topology.model.AdasModel;
import com.yuer.storm.topology.pool.FacePool;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class AdasAnalyze extends BaseBasicBolt {



    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        AdasModel adasAlarmImage = (AdasModel)input.getValueByField("adasAlarmImage");
        String adasExecute = FacePool.adasExecute(adasAlarmImage);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
