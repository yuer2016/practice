package com.yuer.storm.topology;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PictureListener extends BaseRichSpout {
    private SpoutOutputCollector outputCollector;
    private List<String> lines;

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
        lines.forEach(line -> outputCollector.emit(new Values(line)));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("commit"));
    }
}
