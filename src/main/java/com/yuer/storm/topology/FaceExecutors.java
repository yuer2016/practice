package com.yuer.storm.topology;

import com.yuer.storm.topology.model.FaceModel;
import com.yuer.storm.topology.model.FaceResult;
import com.yuer.storm.topology.pool.FacePool;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;
@Slf4j
public class FaceExecutors extends BaseBasicBolt {

    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        FaceModel faceImage = (FaceModel)input.getValueByField("faceImage");
        String vehicleId = FacePool.execute(faceImage.getImgPath());
        FaceResult faceResult = new FaceResult()
                .setDriverImg(faceImage.getImgPath())
                .setImgTime(faceImage.getImgTime())
                .setDriverId(faceImage.getDeviceId())
                .setVehicleId(vehicleId);
        log.info(faceResult.toString());


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
