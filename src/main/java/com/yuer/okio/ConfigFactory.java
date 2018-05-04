package com.yuer.okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

class ConfigFactory {
    private final Properties properties = new Properties();

    ConfigFactory() {
        ClassLoader classLoader = getClass().getClassLoader();
        String CONFIG_PROPERTIES = "config.properties";
        File file = new File(Objects.requireNonNull(classLoader.getResource(CONFIG_PROPERTIES)).getFile());
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getPropertiesByKey(String key) {
        return properties.getProperty(key);
    }
}
