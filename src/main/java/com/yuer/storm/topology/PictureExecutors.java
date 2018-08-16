package com.yuer.storm.topology;

import com.yuer.storm.topology.model.Location;
import com.yuer.storm.topology.model.PhotoResult;
import com.yuer.storm.topology.model.Setting;
import lombok.extern.slf4j.Slf4j;
import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class PictureExecutors extends BaseBasicBolt {
    //private HelloWorldClient client ;
    private List<Location> locations;
    private List<PhotoResult> photoResults;
    private Setting setting;

    /**
     * 配置周期性发送tick tuple
     */
    @Override
    public Map<String, Object> getComponentConfiguration() {
        Config conf = new Config();
        int tickFrequencyInSeconds = 2;
        conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, tickFrequencyInSeconds);
        return conf;
    }

    /**
     * 用来初始化
     */
    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        //client = new HelloWorldClient("localhost", 50051);
        locations = new ArrayList<>();
        photoResults = new ArrayList<>();
        setting = new Setting()
                .setStartTime("00:00:00")
                .setEndTime("23:59:59")
                .setLocationCache(1000)
                .setPhotoResultCache(1000);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        if (isTickTuple(input)) {
            log.info("tick tuple");
        } else {
            Location component = (Location) input.getValueByField("commit");
            Long time = input.getLongByField("location");
            if (component != null) {
                /*过滤轨迹缓存数据*/
                List<Location> list = locations.stream().filter(location ->
                        location.getTimeGps() + setting.getLocationCache() < component.getTimeGps())
                        .collect(Collectors.toList());
                locations.removeAll(list);
                if (list.size() > 0) {
                    log.info("hello {},{},{}", list.get(0).getTimeGps() + setting.getLocationCache(),
                            component.getTimeGps(), list.size());
                }

                locations.add(component);
                log.info("Location is ----: {}", component.toString());
            }

            if (time != null && isAlarmTime(time)) {
                /*本次图片轨迹*/
                List<Integer> photoLocation = locations.stream()
                        .filter(location ->
                                location.getTimeGps() <= time)
                        .map(Location::getSpdGps)
                        .collect(Collectors.toList());

                int speed = photoLocation.stream()
                        .reduce((i, j) -> i + j)
                        .orElse(0) / photoLocation.size() == 0 ? 1 : photoLocation.size();
                //本次图片轨迹速度是否大于最小分析速度
                boolean isSpeed = speed >= setting.getSpeed();

                /*过滤图片结果缓存*/
                List<PhotoResult> result = photoResults.stream()
                        .filter(photoResult ->
                                photoResult.getTime() + setting.getPhotoResultCache() < System.currentTimeMillis())
                        .collect(Collectors.toList());
                photoResults.removeAll(result);
                /*计算图片结果缓存次数*/
                Integer count = photoResults.stream()
                        .map(PhotoResult::getResult)
                        .reduce((i, j) -> i + j).orElse(0);

                log.info("{}", count >= setting.getAlarmNumber());

                log.info("this:--{}---:{}-,-{}", time, photoLocation.size(), locations.size());
            }

            //client.greet(component);
        }

    }

    /**
     * 图片上传时间是否在分析时间内
     */
    private boolean isAlarmTime(Long time) {
        LocalDate now = LocalDate.now();
        LocalDateTime start = LocalDateTime.of(now, LocalTime.parse(setting.getStartTime()));
        LocalDateTime end = LocalDateTime.of(now, LocalTime.parse(setting.getEndTime()));

        return Date.from(start.atZone(ZoneId.systemDefault()).toInstant()).getTime() <= time &&
                time <= Date.from(end.atZone(ZoneId.systemDefault()).toInstant()).getTime();
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
