package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2019/4/2/002.
 */
public class ZKUtils {

    public static String getIp() throws IOException {
        InputStream resourceAsStream = ZKUtils.class.getClassLoader().getResourceAsStream("node.properties");

        Properties properties = new Properties();

        properties.load(resourceAsStream);

        return (String) properties.get("ip");
    }

}
