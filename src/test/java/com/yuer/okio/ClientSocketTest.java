package com.yuer.okio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;
import static org.assertj.core.api.Assertions.*;


class ClientSocketTest {

    private static final String CONFIG_PROPERTIES = "config.properties";
    private final Socket socket = new Socket();

    private final Properties properties = new Properties();

    @BeforeEach
    void init(){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(CONFIG_PROPERTIES)).getFile());
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnectSocket(){
        assertThat(socket.isConnected()).isFalse();
    }

    @Test
    void testGetPropertiesValueByIp(){
        final String ipKey = "ip";
        assertThat(properties.getProperty(ipKey)).isNotBlank();
    }

}