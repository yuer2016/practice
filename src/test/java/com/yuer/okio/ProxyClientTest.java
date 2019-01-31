package com.yuer.okio;

import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;


class ProxyClientTest {
    @Test
    void testProxyClient() {
        new Proxy(Proxy.Type.SOCKS,
                InetSocketAddress.createUnresolved("localhost", 0));

    }
}
