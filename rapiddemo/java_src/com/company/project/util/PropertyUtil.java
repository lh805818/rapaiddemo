package com.company.project.util;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by qince on 2015/4/8.
 */
public class PropertyUtil {
    private static Properties properties = null;

    private final static void readProperty() {
        properties = new Properties();
        try {
            properties.load(new EncodedResource(new ClassPathResource("weather.properties"),"UTF-8").getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(Object key) {
        if (properties == null) {
            synchronized (PropertyUtil.class) {
                readProperty();
            }
        }
        return properties.get(key);
    }
}
