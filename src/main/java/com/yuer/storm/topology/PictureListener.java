package com.yuer.storm.topology;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.yuer.storm.topology.model.Location;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PictureListener extends BaseRichSpout {
    private SpoutOutputCollector outputCollector;
    private List<String> lines;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("commit", "location"));
    }

    @Override
    public void open(Map map,
                     TopologyContext topologyContext,
                     SpoutOutputCollector spoutOutputCollector) {
        this.outputCollector = spoutOutputCollector;
        String filePath = ClassLoader
                .getSystemResource("changelog.txt").getFile();
        CharSource byteSource = Files.asCharSource(new File(filePath), Charsets.UTF_8);
        try {
            lines = byteSource.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextTuple() {
        Location location = new Location()
                .setTimeGps(new Date().getTime())
                .setSpdGps(20);
        Utils.sleep(100);
        outputCollector.emit(new Values(location, null));
        outputCollector.emit(new Values(null, new Date().getTime() - 200));


        /*lines.forEach(line -> {
                    String image = null;
                    ByteSource byteSource = Files.asByteSource(new File(line));
                    try {
                        byte[] read = byteSource.read();
                        image = Utils.bytesToHex(read);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    outputCollector.emit(new Values(image));
                }
        );*/
    }


}
