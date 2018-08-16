package com.yuer.storm.topology.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class FaceClientFactory extends BasePooledObjectFactory<SingleClient> {

    @Override
    public SingleClient create() {
        int port = 50051;
        String host = "192.168.101.79";
        return new SingleClient(host, port);
    }

    @Override
    public PooledObject<SingleClient> wrap(SingleClient singleClient) {
        return new DefaultPooledObject<>(singleClient);
    }

    @Override
    public void destroyObject(PooledObject<SingleClient> p) throws Exception {
        p.getObject().shutdown();
        super.destroyObject(p);
    }
}
