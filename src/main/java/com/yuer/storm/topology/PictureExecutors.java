package com.yuer.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class PictureExecutors extends BaseBasicBolt {

    /**
     * 配置周期性发送tick tuple
     * */
    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config conf = new Config();
        int tickFrequencyInSeconds = 10;
        conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, tickFrequencyInSeconds);
        return conf;
    }

    /**
     * 用来初始化
     * */
    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        if (isTickTuple(input)) {

        } else {
            String component = input.getStringByField("commit");
            System.out.println("this:-----:"+component);
        }

    }

    /**
     * 判断tuple是否是由系统发送过来的Tick
     */
    private boolean isTickTuple(Tuple tuple) {
        return tuple.getSourceComponent()
                .equals(Constants.SYSTEM_COMPONENT_ID) &&
                tuple.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID);
    }
}
