package com.yuer.storm.topology.pool;

public interface WorkCallBack<S> {
    void callback(S s);
}
